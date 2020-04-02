package com.nullbyte.covid_19support.api;

import android.os.AsyncTask;

import com.nullbyte.covid_19support.callbacks.APICallback;
import com.nullbyte.covid_19support.constants.Constant;
import com.nullbyte.covid_19support.utilities.APIUtility;

public class CasesByCountryAPI extends AsyncTask<Void, Void, String> {

    private APICallback apiCallback;
    private String responseString;

    public CasesByCountryAPI(APICallback apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String url = Constant.BASE_URL+"cases_by_country.php";
        responseString = APIUtility.fetchDataFromAPI(url);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        apiCallback.getData(responseString);
    }
}
