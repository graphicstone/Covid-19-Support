package com.nullbyte.covid_19support.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.ui.country_stat.CountryStatFragment;

public class CountryStatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_stat);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        String countryName = getIntent().getStringExtra("Country");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_container, new CountryStatFragment(), countryName).commit();
    }
}
