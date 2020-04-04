package com.nullbyte.covid_19support.callbacks;

import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public interface ViewCallback {
    void onSuccess(View view, AlertDialog dialog);

    void onSuccessBottomSheet(View view, BottomSheetDialog dialog);
}
