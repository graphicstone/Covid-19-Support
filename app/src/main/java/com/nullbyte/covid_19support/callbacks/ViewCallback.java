package com.nullbyte.covid_19support.callbacks;

import android.view.View;

import androidx.appcompat.app.AlertDialog;

public interface ViewCallback {
    void onSuccess(View view, AlertDialog dialog);
}
