package com.nullbyte.covid_19support.api;

import android.os.AsyncTask;

import com.nullbyte.covid_19support.callbacks.APICallback;
import com.nullbyte.covid_19support.constants.Constant;
import com.nullbyte.covid_19support.utilities.APIUtility;

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
        String url = Constant.BASE_URL+ "latest_stat_by_country.php?country="+country;
        responseString = APIUtility.fetchDataFromAPI(url);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        apiCallback.getData(responseString);
    }
}
