package com.nullbyte.covid_19support.ui.country_stat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.api.CasesByCountryDateAPI;
import com.nullbyte.covid_19support.api.SearchByCountryAPI;
import com.nullbyte.covid_19support.databinding.FragmentCountryStatBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class CountryStatFragment extends Fragment {

    private CountryStatViewModel mViewModel;
    private FragmentCountryStatBinding mCountryStatBinding;
    private ArrayList<String> mDateListTotal;
    private ArrayList<String> mCasesListTotal;
    private ArrayList<String> mDeceasedListTotal;
    private ArrayList<String> mRecoveredListTotal;
    private String countryName;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(CountryStatViewModel.class);
        mCountryStatBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_stat, container, false);
        mCountryStatBinding.setCountryStatViewModel(mViewModel);

        initViews();

        mCountryStatBinding.tvCountryName.setText(countryName);
        mCountryStatBinding.toolbarCountryStat.setNavigationOnClickListener(view -> Objects.requireNonNull(getActivity()).onBackPressed());
        mCountryStatBinding.countryRefreshLayout.setOnRefreshListener(() -> getCountryStat(countryName));
        getCountryStat(countryName);
        getCountryDateWiseData();

        return mCountryStatBinding.getRoot();
    }

    private void getCountryStat(String countryName) {
        SearchByCountryAPI searchByCountryAPI = new SearchByCountryAPI(countryName, data -> {
            if (data == null) {
                Snackbar snackbar = Snackbar.make(mCountryStatBinding.getRoot(), "Please check your network connection", Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundTintList(ContextCompat.getColorStateList(Objects.requireNonNull(getActivity()), R.color.red));
                snackbar.show();
                mCountryStatBinding.countryRefreshLayout.setRefreshing(false);
            } else {
                int splitPoint = 0;
                for (int i = 0; i < data.length(); i++) {
                    if (data.charAt(i) == '[')
                        splitPoint = i;
                }
                data = data.substring(splitPoint, data.length() - 1);
                try {
                    JSONArray jsonArr = new JSONArray(data);
                    for (int i = 0; i < data.length(); ++i) {
                        JSONObject dataObject = jsonArr.getJSONObject(i);
                        mCountryStatBinding.tvTotalCasesCount.setText(dataObject.getString("total_cases"));
                        mCountryStatBinding.tvTotalDeceasedCount.setText(dataObject.getString("total_deaths"));
                        mCountryStatBinding.tvTotalRecoveredCount.setText(dataObject.getString("total_recovered"));
                        mCountryStatBinding.tvNewCasesCount.setText(dataObject.getString("new_cases"));
                        mCountryStatBinding.tvNewDeceasedCount.setText(dataObject.getString("new_deaths"));
                        mCountryStatBinding.tvActiveCount.setText(dataObject.getString("active_cases"));
                        mCountryStatBinding.tvCasesPerMillionCount.setText(dataObject.getString("total_cases_per1m"));
                        mCountryStatBinding.countryRefreshLayout.setRefreshing(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        searchByCountryAPI.execute();
    }

    private void getCountryDateWiseData() {
        CasesByCountryDateAPI casesByCountryDateAPI = new CasesByCountryDateAPI("IND", data -> {
            if (data == null) {
                Snackbar snackbar = Snackbar.make(mCountryStatBinding.getRoot(), "Please check your network connection", Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundTintList(ContextCompat.getColorStateList(Objects.requireNonNull(getActivity()), R.color.red));
                snackbar.show();
                mCountryStatBinding.countryRefreshLayout.setRefreshing(false);
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
                    Log.i("DataTry", data);
                    JSONObject response = new JSONObject(data);
                    Iterator<String> keys = response.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        mDateListTotal.add(key);
                        JSONObject value = response.getJSONObject(key);
                        mCasesListTotal.add(value.getString("confirmed"));
                        mRecoveredListTotal.add(value.getString("recovered"));
                        mDeceasedListTotal.add(value.getString("deaths"));
                        Log.i("Cases", value.getString("confirmed"));
                        Log.i("Recovered", value.getString("recovered"));
                        Log.i("Deceased", value.getString("deaths"));
                    }
                } catch (JSONException e) {
                    Log.i("Catch", String.valueOf(e));
                    e.printStackTrace();
                }

            }

            Log.i("Date", mDateListTotal.toString());
            Log.i("Cases", mCasesListTotal.toString());
            Log.i("Recovered", mRecoveredListTotal.toString());
            Log.i("Deceased", mDeceasedListTotal.toString());

        });
        casesByCountryDateAPI.execute();
    }

    private void initViews() {
        countryName = getTag();
        mCasesListTotal = new ArrayList<>();
        mRecoveredListTotal = new ArrayList<>();
        mDeceasedListTotal = new ArrayList<>();
        mDateListTotal = new ArrayList<>();
    }

}
