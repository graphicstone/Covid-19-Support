package com.nullbyte.covid_19support;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class LoaderDialogPunchCorona extends DialogFragment {
    //private LoaderDialogBinding mLoaderBinding;
    public LoaderDialogPunchCorona() {
        // Required empty public constructor
    }

    LottieAnimationView mLottieAnimationView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //mLoaderBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_loader_dialog, container, false);

        View view = inflater.inflate(R.layout.fragment_loader_dialog_punch_corona, container, false);

        mLottieAnimationView = view.findViewById(R.id.punch_corona);
        initLotties(view);

//       view mLottieAnimationView.setAnimation("punchCorona.json");
//        mLottieAnimationView.playAnimation();
//        mLottieAnimationView.loop(true);

        return view;
    }

    public void initLotties(View view) {
        Random rand = new Random();
        int ranNum = rand.nextInt(9);

        switch (ranNum) {
            case 0:
                mLottieAnimationView.setAnimation("corona.json");
                mLottieAnimationView.playAnimation();
                mLottieAnimationView.loop(true);
                break;
            case 1:
                mLottieAnimationView.setAnimation("punchCorona.json");
                mLottieAnimationView.playAnimation();
                mLottieAnimationView.loop(true);
                break;
            case 2:
                mLottieAnimationView.setAnimation("docRunning.json");
                mLottieAnimationView.playAnimation();
                mLottieAnimationView.loop(true);
                break;
            case 3:
                mLottieAnimationView.setAnimation("doctors.json");
                mLottieAnimationView.playAnimation();
                mLottieAnimationView.loop(true);
                break;
            case 4:
                mLottieAnimationView.setAnimation("hand-sanitizer.json");
                mLottieAnimationView.playAnimation();
                mLottieAnimationView.loop(true);
                break;
            case 5:
                mLottieAnimationView.setAnimation("staySafe.json");
                mLottieAnimationView.playAnimation();
                mLottieAnimationView.loop(true);
                break;
            case 6:
                mLottieAnimationView.setAnimation("wash-hand.json");
                mLottieAnimationView.playAnimation();
                mLottieAnimationView.loop(true);
                break;
            case 7:
                mLottieAnimationView.setAnimation("wfh.json");
                mLottieAnimationView.playAnimation();
                mLottieAnimationView.loop(true);
                break;
            case 8:
                mLottieAnimationView.setAnimation("mask.json");
                mLottieAnimationView.playAnimation();
                mLottieAnimationView.loop(true);
                break;


        }
    }
}
