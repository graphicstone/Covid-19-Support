package com.nullbyte.covid_19support.ui.safety_measures;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.databinding.FragmentSafetyMeasuresBinding;

public class SafetyMeasuresFragment extends Fragment {

    private SafetyMeasuresViewModel safetyMeasuresViewModel;
    private FragmentSafetyMeasuresBinding mSafetyMeasuresBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        safetyMeasuresViewModel =
                ViewModelProviders.of(this).get(SafetyMeasuresViewModel.class);
        mSafetyMeasuresBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_safety_measures, container, false);
        mSafetyMeasuresBinding.setSafetyMeasuresViewModel(safetyMeasuresViewModel);



        return mSafetyMeasuresBinding.getRoot();
    }
}
