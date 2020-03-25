package com.nullbyte.covid_19support.ui.info;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.databinding.FragmentInfoBinding;

public class InfoFragment extends Fragment {

    private InfoViewModel homeViewModel;
    private FragmentInfoBinding infoBinding;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(InfoViewModel.class);
        infoBinding = FragmentInfoBinding.inflate(getLayoutInflater());

        return inflater.inflate(R.layout.fragment_info, container, false);
    }
}
