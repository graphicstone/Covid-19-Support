package com.nullbyte.covid_19support.ui.tracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.snackbar.Snackbar;
import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.api.GlobalDateWiseAPI;
import com.nullbyte.covid_19support.api.WorldDataAPI;
import com.nullbyte.covid_19support.databinding.FragmentTrackerBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class TrackerFragment extends Fragment {

    private TrackerViewModel mTrackerViewModel;
    private FragmentTrackerBinding mTrackerBinding;
    private ArrayList<String> mDateList;
    private ArrayList<String> mCasesList;
    private ArrayList<String> mDeceasedList;
    private ArrayList<String> mRecoveredList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mTrackerViewModel =
                ViewModelProviders.of(this).get(TrackerViewModel.class);
        mTrackerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tracker, container, false);
        mTrackerBinding.setTrackerViewModel(mTrackerViewModel);
        mTrackerViewModel.getText().observe(getViewLifecycleOwner(), s -> {

        });

        init();
        mTrackerBinding.refreshWorldDataLayout.setOnRefreshListener(() -> getWorldData(mTrackerBinding.getRoot()));

        getDateWiseData(mTrackerBinding.getRoot());
        getWorldData(mTrackerBinding.getRoot());
        drawGraph();

        return mTrackerBinding.getRoot();
    }

    private void init() {
        mDateList = new ArrayList<>();
        mCasesList = new ArrayList<>();
        mDeceasedList = new ArrayList<>();
        mRecoveredList = new ArrayList<>();
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
                data = data.substring(splitPoint, data.length() - 1);
                try {
                    JSONObject response = new JSONObject(data);
                    Iterator<String> keys = response.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        mDateList.add(key);
                        JSONObject value = response.getJSONObject(key);
                        mCasesList.add(value.getString("confirmed"));
                        mRecoveredList.add(value.getString("recovered"));
                        mDeceasedList.add(value.getString("deaths"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            Log.i("Dates", mDateList.toString());
            Log.i("Cases", mCasesList.toString());
            Log.i("Deceased", mDeceasedList.toString());
            Log.i("Recovered", mRecoveredList.toString());
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
                    mTrackerBinding.tvTotalCases.setText(dataObject.getString("total_cases"));
                    mTrackerBinding.tvTotalDeaths.setText(dataObject.getString("total_deaths"));
                    mTrackerBinding.tvTotalRecovered.setText(dataObject.getString("total_recovered"));
                    mTrackerBinding.tvNewCases.setText(dataObject.getString("new_cases"));
                    mTrackerBinding.tvNewDeceased.setText(dataObject.getString("new_deaths"));
                    mTrackerBinding.tvLastUpdatedDateTime.setText(dataObject.getString("statistic_taken_at"));
                    mTrackerBinding.refreshWorldDataLayout.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        worldDataAPI.execute();
    }

    private void drawGraph() {
        LineChart lineChart = mTrackerBinding.lineChart;
        LineDataSet lineDataSet = new LineDataSet(getData(), "Inclination");
        lineDataSet.setColor(ContextCompat.getColor(requireActivity(), R.color.primary));
        lineDataSet.setValueTextColor(ContextCompat.getColor(requireActivity(), R.color.design_default_color_primary_dark));
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "Jan1", "Feb1", "Mar1", "Apr1", "Jan1", "Feb1", "Mar1", "Apr1", "Jan1", "Feb1", "Mar1", "Apr1"};


        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return months[(int) value];
            }
        };
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);

        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);

        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);

        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
        lineChart.animateX(2500);
        lineChart.invalidate();

    }

    private ArrayList<Entry> getData() {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 60f));
        entries.add(new Entry(1f, 10f));
        entries.add(new Entry(2f, 20f));
        entries.add(new Entry(3f, 40f));
        entries.add(new Entry(4f, 60f));
        entries.add(new Entry(5f, 10f));
        entries.add(new Entry(6f, 20f));
        entries.add(new Entry(7f, 40f));
        entries.add(new Entry(8f, 60f));
        entries.add(new Entry(9f, 10f));
        entries.add(new Entry(10f, 20f));
        entries.add(new Entry(11f, 40f));
        entries.add(new Entry(12f, 60f));
        entries.add(new Entry(13f, 10f));
        entries.add(new Entry(14f, 20f));
        entries.add(new Entry(15f, 40f));
        return entries;
    }
}
