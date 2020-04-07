package com.nullbyte.covid_19support;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.nullbyte.covid_19support.databinding.FragmentTrackerBinding;

public class LoaderDialog extends DialogFragment {

    //private LoaderDialogBinding mLoaderBinding;
    public LoaderDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //mLoaderBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_loader_dialog, container, false);

        View view =  inflater.inflate(R.layout.fragment_loader_dialog, container, false);


        LottieAnimationView mLottieAnimationView;
        mLottieAnimationView = view.findViewById(R.id.stayhome);
        mLottieAnimationView.setAnimation("staySafe.json");
        mLottieAnimationView.playAnimation();
        mLottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);

        return view;
    }
}
