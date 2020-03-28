package com.nullbyte.covid_19support.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.ui.help.SearchFragment;
import com.nullbyte.covid_19support.ui.safety_measures.SafetyMeasuresFragment;
import com.nullbyte.covid_19support.ui.tracker.TrackerFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment trackerFragment = new TrackerFragment();
    final Fragment countryFragment = new SearchFragment();
    final Fragment infoFragment = new SafetyMeasuresFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = trackerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        fm.beginTransaction().add(R.id.main_container, infoFragment, "3").hide(infoFragment).commit();
        fm.beginTransaction().add(R.id.main_container, countryFragment, "2").hide(countryFragment).commit();
        fm.beginTransaction().add(R.id.main_container, trackerFragment, "1").commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
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
            }
            return true;
        });
    }
}
