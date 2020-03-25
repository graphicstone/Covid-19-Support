package com.nullbyte.covid_19support.api;

import android.os.AsyncTask;
import android.util.Log;

import com.nullbyte.covid_19support.callback.APICallback;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WorldDataAPI extends AsyncTask<Void, Void, Void> {

    private APICallback apiCallback;

    public WorldDataAPI(APICallback apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/worldstat.php")
                .get()
                .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "e62053511cmshf9ef1c3940f1009p184555jsn64ec6dcb7692")
                .build();

        try {
            final Response response = client.newCall(request).execute();
            apiCallback.getData(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
