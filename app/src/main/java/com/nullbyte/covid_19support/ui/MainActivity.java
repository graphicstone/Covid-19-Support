package com.nullbyte.covid_19support.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.ui.search.SearchFragment;
import com.nullbyte.covid_19support.ui.safety_measures.SafetyMeasuresFragment;
import com.nullbyte.covid_19support.ui.tracker.TrackerFragment;
import com.nullbyte.covid_19support.ui.updates.UpdatesFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment trackerFragment = new TrackerFragment();
    final Fragment countryFragment = new SearchFragment();
    final Fragment infoFragment = new SafetyMeasuresFragment();
    final Fragment updatesFragment = new UpdatesFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = trackerFragment;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm.beginTransaction().add(R.id.main_container, updatesFragment, "4").hide(updatesFragment).commit();
        fm.beginTransaction().add(R.id.main_container, infoFragment, "3").hide(infoFragment).commit();
        fm.beginTransaction().add(R.id.main_container, countryFragment, "2").hide(countryFragment).commit();
        fm.beginTransaction().add(R.id.main_container, trackerFragment, "1").commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    fm.beginTransaction().hide(active).show(trackerFragment).commit();
                    active = trackerFragment;
                    break;
                case R.id.country:
                    fm.beginTransaction().hide(active).show(countryFragment).commit();
                    active = countryFragment;
                    break;
                case R.id.charts:
                    fm.beginTransaction().hide(active).show(infoFragment).commit();
                    active = infoFragment;
                    break;
                case R.id.updates:
                    fm.beginTransaction().hide(active).show(updatesFragment).commit();
                    active = updatesFragment;
                    break;
            }
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (active != trackerFragment) {
            bottomNavigationView.setSelectedItemId(R.id.home);
            fm.beginTransaction().hide(active).show(trackerFragment).commit();
            active = trackerFragment;
        } else {
            super.onBackPressed();
        }
    }
}
