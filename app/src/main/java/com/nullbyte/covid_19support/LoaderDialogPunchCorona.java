package com.nullbyte.covid_19support;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;

public class LoaderDialogPunchCorona extends DialogFragment {
    //private LoaderDialogBinding mLoaderBinding;
    public LoaderDialogPunchCorona() {
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

        View view =  inflater.inflate(R.layout.fragment_loader_dialog_punch_corona, container, false);

        LottieAnimationView mLottieAnimationView;
        mLottieAnimationView = view.findViewById(R.id.punch_corona);
        mLottieAnimationView.setAnimation("punchCorona.json");
        mLottieAnimationView.playAnimation();
        mLottieAnimationView.loop(true);

        return view;
    }

}
