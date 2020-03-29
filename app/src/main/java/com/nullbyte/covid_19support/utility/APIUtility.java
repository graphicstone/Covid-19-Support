package com.nullbyte.covid_19support.utility;

import com.nullbyte.covid_19support.constants.Constant;

import java.io.IOException;
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



}
