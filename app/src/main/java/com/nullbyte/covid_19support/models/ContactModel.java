package com.nullbyte.covid_19support.models;

public class ContactModel {
    private String mStateName, mNumber;

    public ContactModel(String mStateName, String mNumber) {
        this.mStateName = mStateName;
        this.mNumber = mNumber;
    }

    public String getmStateName() {
        return mStateName;
    }

    public void setmStateName(String mStateName) {
        this.mStateName = mStateName;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }
}

