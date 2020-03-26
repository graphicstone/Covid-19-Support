package com.nullbyte.covid_19support.ui.tracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.api.WorldDataAPI;
import com.nullbyte.covid_19support.databinding.FragmentTrackerBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class TrackerFragment extends Fragment {

    private TrackerViewModel mTrackerViewModel;
    private FragmentTrackerBinding mTrackerBinding;
    private static String mWorldData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mTrackerViewModel =
                ViewModelProviders.of(this).get(TrackerViewModel.class);
        mTrackerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tracker, container, false);
        mTrackerBinding.setTrackerViewModel(mTrackerViewModel);
        mTrackerViewModel.getText().observe(getViewLifecycleOwner(), s -> {

        });

        getWorldData();

        return mTrackerBinding.getRoot();
    }

    private void getWorldData() {
        WorldDataAPI worldDataAPI = new WorldDataAPI(data -> {
            mWorldData = data;
            Log.i("Data", data);
            String totalCases, totalDeaths, totalRecovered;
            try {
                JSONObject dataObject = new JSONObject(data);
                totalCases = dataObject.getString("total_cases");
                totalDeaths = dataObject.getString("total_deaths");
                totalRecovered = dataObject.getString("total_recovered");
                mTrackerBinding.tvTotalCases.setText(totalCases);
                mTrackerBinding.tvTotalDeaths.setText(totalDeaths);
                mTrackerBinding.tvTotalRecovered.setText(totalRecovered);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        worldDataAPI.execute();
    }
}
