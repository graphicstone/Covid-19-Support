package com.nullbyte.covid_19support.ui.info;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.databinding.FragmentInfoBinding;

public class InfoFragment extends Fragment {

    private InfoViewModel infoViewModel;
    private FragmentInfoBinding mInfoBinding;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        infoViewModel =
                ViewModelProviders.of(this).get(InfoViewModel.class);
        mInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);
        mInfoBinding.setInfoViewModel(infoViewModel);

        return mInfoBinding.getRoot();
    }
}
