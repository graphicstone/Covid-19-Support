package com.nullbyte.covid_19support.ui.fragments.tracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.snackbar.Snackbar;
import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.api.GlobalDateWiseAPI;
import com.nullbyte.covid_19support.api.WorldDataAPI;
import com.nullbyte.covid_19support.databinding.FragmentTrackerBinding;
import com.nullbyte.covid_19support.utilities.DialogHelperUtility;
import com.nullbyte.covid_19support.utilities.GraphUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class TrackerFragment extends Fragment {

    private TrackerViewModel mTrackerViewModel;
    private FragmentTrackerBinding mTrackerBinding;
    private ArrayList<String> mDateListTotal;
    private ArrayList<String> mCasesListTotal;
    private ArrayList<String> mDeceasedListTotal;
    private ArrayList<String> mRecoveredListTotal;
    private AlertDialog mAlertDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mTrackerViewModel =
                ViewModelProviders.of(this).get(TrackerViewModel.class);
        mTrackerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tracker, container, false);
        mTrackerBinding.setTrackerViewModel(mTrackerViewModel);

        init();
        mTrackerBinding.refreshWorldDataLayout.setOnRefreshListener(() -> getWorldData(mTrackerBinding.getRoot()));

        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        Fragment prev = getParentFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
            ft.remove(prev);

        ft.addToBackStack(null);
        mAlertDialog.show();

        getDateWiseData(mTrackerBinding.getRoot());
        getWorldData(mTrackerBinding.getRoot());

        return mTrackerBinding.getRoot();
    }

    private void init() {
        mDateListTotal = new ArrayList<>();
        mCasesListTotal = new ArrayList<>();
        mDeceasedListTotal = new ArrayList<>();
        mRecoveredListTotal = new ArrayList<>();

        DialogHelperUtility.customDialog(getActivity(), R.layout.loader_layout, (view, dialog) -> {
            LottieAnimationView mLottieAnimationView = view.findViewById(R.id.lottie_loader);

            mLottieAnimationView.setAnimation("staySafe.json");
            mLottieAnimationView.playAnimation();
            mLottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
            mAlertDialog = dialog;
        });
    }


    private void getDateWiseData(View view) {
        GlobalDateWiseAPI globalDateWiseAPI = new GlobalDateWiseAPI(data -> {
            if (data == null) {
                Snackbar snackbar = Snackbar.make(view, "Please check your network connection", Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundTintList(ContextCompat.getColorStateList(Objects.requireNonNull(getActivity()), R.color.red));
                snackbar.show();
                mTrackerBinding.refreshWorldDataLayout.setRefreshing(false);
            } else {
                int splitPoint = 0;
                for (int i = 1; i < data.length(); i++) {
                    if (data.charAt(i) == '{') {
                        splitPoint = i;
                        break;
                    }
                }
                if(data.length() > 2) {
                    data = data.substring(splitPoint, data.length() - 1);
                } else {
                    Snackbar snackbar = Snackbar.make(view, "Cannot fetch data from the server", Snackbar.LENGTH_LONG);
                    snackbar.getView().setBackgroundTintList(ContextCompat.getColorStateList(Objects.requireNonNull(getActivity()), R.color.red));
                    snackbar.show();
                }
                try {
                    JSONObject response = new JSONObject(data);
                    Iterator<String> keys = response.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        mDateListTotal.add(key);
                        JSONObject value = response.getJSONObject(key);
                        mCasesListTotal.add(value.getString("confirmed"));
                        mRecoveredListTotal.add(value.getString("recovered"));
                        mDeceasedListTotal.add(value.getString("deaths"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
        globalDateWiseAPI.execute();
    }

    private void getWorldData(View view) {
        WorldDataAPI worldDataAPI = new WorldDataAPI(data -> {
            if (data == null) {
                Snackbar snackbar = Snackbar.make(view, "Please check your network connection", Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundTintList(ContextCompat.getColorStateList(Objects.requireNonNull(getActivity()), R.color.red));
                snackbar.show();
                mTrackerBinding.refreshWorldDataLayout.setRefreshing(false);
            } else {
                try {
                    JSONObject dataObject = new JSONObject(data);
                    Log.i("AllData", data);
                    mTrackerBinding.tvTotalCases.setText(dataObject.getString("total_cases"));
                    mTrackerBinding.tvTotalDeaths.setText(dataObject.getString("total_deaths"));
                    mTrackerBinding.tvTotalRecovered.setText(dataObject.getString("total_recovered"));
                    mTrackerBinding.tvNewCases.setText(dataObject.getString("new_cases"));
                    mTrackerBinding.tvNewDeceased.setText(dataObject.getString("new_deaths"));
                    mTrackerBinding.tvLastUpdatedDateTime.setText(dataObject.getString("statistic_taken_at"));
                    mTrackerBinding.refreshWorldDataLayout.setRefreshing(false);
                    mAlertDialog.dismiss();

                    drawGraphs();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        worldDataAPI.execute();


    }

    private void drawGraphs() {
        drawPie();
        drawGraphForTotalCases();
        drawGraphForDeath();
        drawGraphForRecovered();
    }

    private void drawPie() {
        long mDeceased = Long.parseLong(mDeceasedListTotal.get(mDeceasedListTotal.size() - 1));
        long mRecovered = Long.parseLong(mRecoveredListTotal.get(mRecoveredListTotal.size() - 1));
        PieChart mPieChart = mTrackerBinding.piechartoverall;
        ArrayList<PieEntry> sessDataPie1 = new ArrayList<>();
        sessDataPie1.add(new PieEntry(mRecovered, "Recovered"));
        sessDataPie1.add(new PieEntry(mDeceased, "Deceased"));
        GraphUtility.piechart(mPieChart, sessDataPie1);
        mPieChart.setVisibility(View.VISIBLE);


    }

    private void drawGraphForTotalCases() {
        LineChart lineChart = mTrackerBinding.lineChart1;
        LineDataSet lineDataSet = new LineDataSet(getDataForTotalCases(), "Total Cases");
        if(isAdded()) {
            lineDataSet.setColor(ContextCompat.getColor(requireActivity(), R.color.primary_text));
            lineDataSet.setValueTextColor(ContextCompat.getColor(requireActivity(), R.color.design_default_color_primary_dark));
        }
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        String[] months = new String[mDateListTotal.size()];
        months = mDateListTotal.toArray(months);
        String[] xAxisValue = months;

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return xAxisValue[(int) value];
            }
        };
        xAxis.setGranularity(0f);
        xAxis.setValueFormatter(formatter);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);
        xAxis.setLabelRotationAngle(-90);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);

        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
        data.setDrawValues(false);
        lineChart.animateX(2500);
        lineChart.invalidate();

    }

    private ArrayList<Entry> getDataForTotalCases() {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < mDateListTotal.size(); i++)
            entries.add(new Entry((float) i, Float.parseFloat(mCasesListTotal.get(i))));

        return entries;
    }

    private void drawGraphForDeath() {
        LineChart lineChart = mTrackerBinding.lineChart2;
        LineDataSet lineDataSet = new LineDataSet(getDataForTotalDeath(), "Deceased");
        lineDataSet.setColor(ContextCompat.getColor(requireActivity(), R.color.red));
        lineDataSet.setValueTextColor(ContextCompat.getColor(requireActivity(), R.color.design_default_color_primary_dark));
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        String[] months = new String[mDateListTotal.size()];
        months = mDateListTotal.toArray(months);
        String[] xAxisValue = months;

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return xAxisValue[(int) value];
            }
        };
        xAxis.setGranularity(0f);
        xAxis.setValueFormatter(formatter);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);
        xAxis.setLabelRotationAngle(-90);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);

        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
        data.setDrawValues(false);
        lineChart.animateX(2500);
        lineChart.invalidate();
    }

    private ArrayList<Entry> getDataForTotalDeath() {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < mDateListTotal.size(); i++)
            entries.add(new Entry((float) i, Float.parseFloat(mDeceasedListTotal.get(i))));
        return entries;
    }

    private void drawGraphForRecovered() {
        LineChart lineChart = mTrackerBinding.lineChart3;
        LineDataSet lineDataSet = new LineDataSet(getDataForTotalRecovered(), "Recovered");
        lineDataSet.setColor(ContextCompat.getColor(requireActivity(), R.color.green));
        lineDataSet.setValueTextColor(ContextCompat.getColor(requireActivity(), R.color.design_default_color_primary_dark));
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        String[] months = new String[mDateListTotal.size()];
        months = mDateListTotal.toArray(months);
        String[] xAxisValue = months;

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return xAxisValue[(int) value];
            }
        };
        xAxis.setGranularity(0f);
        xAxis.setValueFormatter(formatter);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);
        xAxis.setLabelRotationAngle(-90);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);

        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
        data.setDrawValues(false);
        lineChart.animateX(2500);
        lineChart.invalidate();
    }

    private ArrayList<Entry> getDataForTotalRecovered() {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < mDateListTotal.size(); i++)
            entries.add(new Entry((float) i, Float.parseFloat(mRecoveredListTotal.get(i))));
        return entries;
    }
}
