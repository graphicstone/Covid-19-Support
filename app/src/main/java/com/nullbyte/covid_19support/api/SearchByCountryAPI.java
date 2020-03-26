package com.nullbyte.covid_19support.api;

import android.os.AsyncTask;

import com.nullbyte.covid_19support.callback.APICallback;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchByCountryAPI extends AsyncTask<Void, Void, String> {

    private APICallback apiCallback;
    private String country;
    private String responseString;

    public SearchByCountryAPI(String country, APICallback apiCallback) {
        this.country = country;
        this.apiCallback = apiCallback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/latest_stat_by_country.php?country="+country;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "e62053511cmshf9ef1c3940f1009p184555jsn64ec6dcb7692")
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
