package com.nullbyte.covid_19support.api;

import android.os.AsyncTask;
import android.util.Log;

import com.nullbyte.covid_19support.callback.APICallback;
import com.nullbyte.covid_19support.constants.Constant;
import com.nullbyte.covid_19support.utility.APIUtility;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WorldDataAPI extends AsyncTask<Void, Void, String> {

    private APICallback apiCallback;
    private String responseString;

    public WorldDataAPI(APICallback apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void...voids) {
        String url = Constant.BASE_URL+"worldstat.php";
        responseString = APIUtility.fetchDataFromAPI(url);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        apiCallback.getData(responseString);
    }
}
