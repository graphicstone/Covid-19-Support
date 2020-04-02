package com.nullbyte.covid_19support.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.api.CasesByCountryDateAPI;
import com.nullbyte.covid_19support.api.GlobalDateWiseAPI;
import com.nullbyte.covid_19support.api.SearchByCountryAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class CountryStatActivity extends AppCompatActivity {

    private TextView mCountryName, mTotalCases, mTotalDiseased, mTotalRecovered, mNewCases, mNewDiseased, mActiveCases, mCasesPer1M;
    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<String> mDateListTotal;
    private ArrayList<String> mCasesListTotal;
    private ArrayList<String> mDeceasedListTotal;
    private ArrayList<String> mRecoveredListTotal;
    private Map<String,String> countryCode = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_stat);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        initViews();
        initCountryCodes();


        String countryName = getIntent().getStringExtra("Country");
        mCountryName.setText(countryName);

        mToolbar.setOnClickListener(view -> onBackPressed());
        mSwipeRefreshLayout.setOnRefreshListener(() -> getCountryStat(countryName));
        getCountryStat(countryName);
        getCountryDateWiseData();
    }

    private void getCountryStat(String countryName) {
        SearchByCountryAPI searchByCountryAPI = new SearchByCountryAPI(countryName, data -> {
            if (data == null) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Please check your network connection", Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.red));
                snackbar.show();
                mSwipeRefreshLayout.setRefreshing(false);
            } else {
                int splitPoint = 0;
                for (int i = 0; i < data.length(); i++) {
                    if (data.charAt(i) == '[')
                        splitPoint = i;
                }
                data = data.substring(splitPoint, data.length() - 1);
                try {
                    JSONArray jsonArr = new JSONArray(data);
                    for (int i = 0; i < data.length(); ++i) {
                        JSONObject dataObject = jsonArr.getJSONObject(i);
                        mTotalCases.setText(dataObject.getString("total_cases"));
                        mTotalDiseased.setText(dataObject.getString("total_deaths"));
                        mTotalRecovered.setText(dataObject.getString("total_recovered"));
                        mNewCases.setText(dataObject.getString("new_cases"));
                        mNewDiseased.setText(dataObject.getString("new_deaths"));
                        mActiveCases.setText(dataObject.getString("active_cases"));
                        mCasesPer1M.setText(dataObject.getString("total_cases_per1m"));
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        searchByCountryAPI.execute();
    }

    private void getCountryDateWiseData() {
        CasesByCountryDateAPI casesByCountryDateAPI = new CasesByCountryDateAPI(data -> {
            if (data == null) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Please check your network connection", Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.red));
                snackbar.show();
                mSwipeRefreshLayout.setRefreshing(false);
            } else {
                int splitPoint = 0;
                for (int i = 1; i < data.length(); i++) {
                    if (data.charAt(i) == '{') {
                        splitPoint = i;
                        break;
                    }
                }
                data = data.substring(splitPoint, data.length() - 1);
                try {
                    Log.i("DataTry",data);
                    JSONObject response = new JSONObject(data);
                    Iterator<String> keys = response.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        mDateListTotal.add(key);
                        JSONObject value = response.getJSONObject(key);
                        mCasesListTotal.add(value.getString("confirmed"));
                        mRecoveredListTotal.add(value.getString("recovered"));
                        mDeceasedListTotal.add(value.getString("deaths"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
//            Log.i("Dates", mDateListTotal.toString());
//            Log.i("Cases", mCasesListTotal.toString());
//            Log.i("Deceased", mDeceasedListTotal.toString());
             Log.i("Recovered", mRecoveredListTotal.get(0).toString());


        },countryCode.get(mCountryName));
    //    Log.i("Desh from act",countryCode.get(mCountryName));
        casesByCountryDateAPI.execute();
    }

    private void initViews() {
        mToolbar = findViewById(R.id.toolbar_country_stat);
        mCountryName = findViewById(R.id.tv_country_name);
        mTotalCases = findViewById(R.id.tv_total_cases_count);
        mTotalDiseased = findViewById(R.id.tv_total_deceased_count);
        mTotalRecovered = findViewById(R.id.tv_total_recovered_count);
        mNewCases = findViewById(R.id.tv_new_cases_count);
        mNewDiseased = findViewById(R.id.tv_new_deceased_count);
        mActiveCases = findViewById(R.id.tv_active_count);
        mCasesPer1M = findViewById(R.id.tv_cases_per_million_count);
        mSwipeRefreshLayout = findViewById(R.id.country_refresh_layout);

        mCasesListTotal = new ArrayList<>();
        mDateListTotal = new ArrayList<>();
        mDeceasedListTotal = new ArrayList<>();
        mRecoveredListTotal = new ArrayList<>();
    }

    private void initCountryCodes()
    {
        countryCode.put("India","IND");

    }
}
