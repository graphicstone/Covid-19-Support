package com.nullbyte.covid_19support.ui.updates;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UpdatesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UpdatesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}