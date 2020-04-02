package com.nullbyte.covid_19support.utilities;

import android.util.Log;

import com.nullbyte.covid_19support.constants.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIUtility {
    public static String fetchDataFromAPI(String url) {
        String responseString = "";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(Constant.HEADER_HOST_NAME, Constant.HEADER_HOST_VALUE)
                .addHeader(Constant.HEADER_KEY_NAME, Constant.HEADER_KEY_VALUE)
                .build();

        try {
            Response response = client.newCall(request).execute();
            responseString = Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public static String fetchLatestUpdates() {
        String responseString = "";
        try {
            URL url = new URL("https://newsapi.org/v2/top-headlines?q=corona&apiKey=afd755aef710424099dc62e554e64410");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            Log.i("br", String.valueOf(br));
            String output;
            while ((output = br.readLine()) != null) {
                responseString = output;
                Log.i("Data", output);
            }
            conn.disconnect();

        } catch (Exception e) {
            Log.i("Data", "hdfg");
            e.printStackTrace();
        }
        return responseString;
    }

}
