package com.nullbyte.covid_19support.api;

import android.os.AsyncTask;
import android.util.Log;

import com.nullbyte.covid_19support.callbacks.APICallback;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CasesByCountryDateAPI extends AsyncTask<Void, Void, String> {

    private APICallback apiCallback;
    private String responseString;
    private String mCountyName;

    public CasesByCountryDateAPI(String mCountyName, APICallback apiCallback) {
        this.mCountyName = mCountyName;
        this.apiCallback = apiCallback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.i("Desh", mCountyName);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://covidapi.info/api/v1/country/" + mCountyName)
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            responseString = Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("zxcv", responseString);
        apiCallback.getData(responseString);
    }
}