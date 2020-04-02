package com.nullbyte.covid_19support.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.databinding.FragmentSafetyMeasuresBinding;

public class InfoFragment extends Fragment {

    private InfoViewModel infoViewModel;
    private FragmentSafetyMeasuresBinding mSafetyMeasuresBinding;
    private int[] sampleImages = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_6, R.drawable.img_7, R.drawable.img_8, R.drawable.img_9, R.drawable.img_10};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        infoViewModel =
                ViewModelProviders.of(this).get(InfoViewModel.class);
        mSafetyMeasuresBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_safety_measures, container, false);
        mSafetyMeasuresBinding.setSafetyMeasuresViewModel(infoViewModel);

        mSafetyMeasuresBinding.carouselView.setPageCount(sampleImages.length);
        mSafetyMeasuresBinding.carouselView.setImageListener((position, imageView) -> imageView.setImageResource(sampleImages[position]));

        return mSafetyMeasuresBinding.getRoot();
    }
}
