package com.nullbyte.covid_19support.ui.updates;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.databinding.FragmentUpdatesBinding;
import com.nullbyte.covid_19support.utility.APIUtility;

import java.io.IOException;

public class UpdatesFragment extends Fragment {

    private UpdatesViewModel updatesViewModel;
    private FragmentUpdatesBinding mUpdatesBinding;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        updatesViewModel =
                ViewModelProviders.of(this).get(UpdatesViewModel.class);
        mUpdatesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_updates, container, false);
        mUpdatesBinding.setInfoViewModel(updatesViewModel);

        String result = APIUtility.fetchLatestUpdates();
        Log.i("Result", result);

        return mUpdatesBinding.getRoot();
    }
}
