package com.nullbyte.covid_19support.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.callbacks.ViewCallback;
import com.nullbyte.covid_19support.constants.Constant;
import com.nullbyte.covid_19support.ui.fragments.country_stat.CountryStatFragment;
import com.nullbyte.covid_19support.ui.fragments.info.InfoFragment;
import com.nullbyte.covid_19support.ui.fragments.search.SearchFragment;
import com.nullbyte.covid_19support.ui.fragments.tracker.TrackerFragment;
import com.nullbyte.covid_19support.ui.fragments.updates.UpdatesFragment;
import com.nullbyte.covid_19support.utilities.DialogHelperUtility;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedpreferences;
    private Fragment trackerFragment = new TrackerFragment();
    final Fragment searchFragment = new SearchFragment();
    final Fragment infoFragment = new InfoFragment();
    final Fragment updatesFragment = new UpdatesFragment();
    final Fragment countryFragment = new CountryStatFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = trackerFragment;
    private BottomNavigationView bottomNavigationView;
    private TextView mTitleBar;
    private RadioGroup mRadioGroup;
    private RadioButton mWorld, mMyCountry;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = this.getSharedPreferences(Constant.PREFERENCES, Context.MODE_PRIVATE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        mTitleBar = findViewById(R.id.tv_title_bar);
        mRadioGroup = findViewById(R.id.rg_toggle);
        mWorld = findViewById(R.id.rb_world);
        mMyCountry = findViewById(R.id.rb_my_country);
        mTitleBar.setText(R.string.home);
        mToolbar = findViewById(R.id.toolbar_main);

        fm.beginTransaction().add(R.id.main_container, countryFragment, "5").hide(countryFragment).commit();
        fm.beginTransaction().add(R.id.main_container, updatesFragment, "4").hide(updatesFragment).commit();
        fm.beginTransaction().add(R.id.main_container, infoFragment, "3").hide(infoFragment).commit();
        fm.beginTransaction().add(R.id.main_container, searchFragment, "2").hide(searchFragment).commit();
        fm.beginTransaction().add(R.id.main_container, trackerFragment, "1").commit();

        mMyCountry.setOnClickListener(view -> {
            if (sharedpreferences.getString(Constant.COUNTRY_NAME, "DEFAULT").equals("DEFAULT")) {
                DialogHelperUtility.customDialog(this, R.layout.item_set_country, new ViewCallback() {
                    @Override
                    public void onSuccess(View view, AlertDialog dialog) {
                        Button submit;
                        EditText mCountryName;
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        mCountryName = view.findViewById(R.id.et_country_name);
                        submit = view.findViewById(R.id.btn_submit);
                        submit.setOnClickListener(view1 -> {
                            if (mCountryName.getText() != null) {
                                String countryName = mCountryName.getText().toString();
                                editor.putString(Constant.COUNTRY_NAME, countryName);
                                editor.apply();
                                dialog.dismiss();
                            } else
                                Toast.makeText(MainActivity.this, "Please enter country name", Toast.LENGTH_SHORT).show();
                        });
                        dialog.show();
                    }

                    @Override
                    public void onSuccessBottomSheet(View view, BottomSheetDialog dialog) {

                    }
                });
            }
            fm.beginTransaction().hide(trackerFragment).show(countryFragment).commit();
            active = countryFragment;
        });

        mWorld.setOnClickListener(view -> {
            fm.beginTransaction().hide(countryFragment).show(trackerFragment).commit();
            active = trackerFragment;
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    fm.beginTransaction().hide(active).show(trackerFragment).commit();
                    mRadioGroup.setVisibility(View.VISIBLE);
                    mToolbar.setVisibility(View.GONE);
                    mWorld.performClick();
                    mTitleBar.setText(R.string.home);
                    active = trackerFragment;
                    break;
                case R.id.country:
                    fm.beginTransaction().hide(active).show(searchFragment).commit();
                    mTitleBar.setText(R.string.search_by_country);
                    mRadioGroup.setVisibility(View.GONE);
                    mToolbar.setVisibility(View.VISIBLE);
                    active = searchFragment;
                    break;
                case R.id.info:
                    fm.beginTransaction().hide(active).show(infoFragment).commit();
                    mTitleBar.setText(R.string.info);
                    mRadioGroup.setVisibility(View.GONE);
                    mToolbar.setVisibility(View.VISIBLE);
                    active = infoFragment;
                    break;
                case R.id.updates:
                    fm.beginTransaction().hide(active).show(updatesFragment).commit();
                    mTitleBar.setText(R.string.latest_update);
                    mRadioGroup.setVisibility(View.GONE);
                    mToolbar.setVisibility(View.VISIBLE);
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
            mRadioGroup.setVisibility(View.VISIBLE);
            mToolbar.setVisibility(View.GONE);
            mWorld.performClick();
            mTitleBar.setText(R.string.home);
            active = trackerFragment;
        } else {
            super.onBackPressed();
        }
    }
}
