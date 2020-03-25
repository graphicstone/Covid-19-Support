package com.nullbyte.covid_19support.ui.safety_measures;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SafetyMeasuresViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SafetyMeasuresViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}