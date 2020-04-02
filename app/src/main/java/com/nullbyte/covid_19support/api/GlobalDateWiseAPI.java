package com.nullbyte.covid_19support.api;

import android.os.AsyncTask;

import com.nullbyte.covid_19support.callbacks.APICallback;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GlobalDateWiseAPI extends AsyncTask<Void, Void, String> {

    private APICallback apiCallback;
    private String responseString;

    public GlobalDateWiseAPI(APICallback apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://covidapi.info/api/v1/global/count")
               //DONE
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
        apiCallback.getData(responseString);
    }
}
