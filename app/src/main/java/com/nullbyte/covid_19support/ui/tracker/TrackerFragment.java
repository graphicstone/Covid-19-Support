package com.nullbyte.covid_19support.ui.tracker;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.callback.APICallback;
import com.nullbyte.covid_19support.databinding.FragmentTrackerBinding;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TrackerFragment extends Fragment {

    private TrackerViewModel mTrackerViewModel;
    private FragmentTrackerBinding mTrackerBinding;
    private static String mWorldData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mTrackerViewModel =
                ViewModelProviders.of(this).get(TrackerViewModel.class);
        mTrackerBinding = FragmentTrackerBinding.inflate(getLayoutInflater());


        fetchData();

//        int SDK_INT = android.os.Build.VERSION.SDK_INT;
//        if (SDK_INT > 8) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                    .permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//
//        }

//        mWorldData = mWorldData.substring(1, mWorldData.length()-1);
//        Log.i("Response", mWorldData);
//
//        mTrackerBinding.tvTotalCases.setText(mWorldData);
//        trackerBinding.tvTotalDeaths.setText(worldStats.get(0).getTotalDeaths());
//        trackerBinding.tvTotalRecovered.setText(worldStats.get(0).getTotalRecovered());

        return inflater.inflate(R.layout.fragment_tracker, container, false);
    }

    private void fetchData() {
        Log.i("Response", "Entered");
        APICallback apiCallback;
        apiCallback = new APICallback() {
            @Override
            public void getData(String data) {
                mWorldData = data;
                Log.i("Response", data);
            }
        };
//        new AsyncCaller().execute();
    }

    private static class AsyncCaller extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


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
                Response response = client.newCall(request).execute();
                mWorldData = Objects.requireNonNull(response.body()).string();
                Log.i("Response", mWorldData);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
