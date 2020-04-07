package com.nullbyte.covid_19support.ui.activities;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.ui.fragments.country_stat.CountryStatFragment;


public class CountryStatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_stat);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Toolbar mToolbar = findViewById(R.id.toolbar_country_stat);
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());

        String countryName = getIntent().getStringExtra("Country");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_container, new CountryStatFragment(), countryName).commit();
    }
}
