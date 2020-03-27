package com.nullbyte.covid_19support.ui.tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.api.WorldDataAPI;
import com.nullbyte.covid_19support.databinding.FragmentTrackerBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class TrackerFragment extends Fragment {

    private TrackerViewModel mTrackerViewModel;
    private FragmentTrackerBinding mTrackerBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mTrackerViewModel =
                ViewModelProviders.of(this).get(TrackerViewModel.class);
        mTrackerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tracker, container, false);
        mTrackerBinding.setTrackerViewModel(mTrackerViewModel);
        mTrackerViewModel.getText().observe(getViewLifecycleOwner(), s -> {

        });

        mTrackerBinding.refreshWorldDataLayout.setOnRefreshListener(() -> getWorldData(mTrackerBinding.getRoot()));

        getWorldData(mTrackerBinding.getRoot());

        return mTrackerBinding.getRoot();
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
}
