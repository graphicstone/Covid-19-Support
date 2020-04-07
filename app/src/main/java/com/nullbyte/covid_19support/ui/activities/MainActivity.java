package com.nullbyte.covid_19support.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.adapters.EmergencyNumberAdapter;
import com.nullbyte.covid_19support.constants.Constant;
import com.nullbyte.covid_19support.models.ContactModel;
import com.nullbyte.covid_19support.ui.fragments.country_stat.CountryStatFragment;
import com.nullbyte.covid_19support.ui.fragments.info.InfoFragment;
import com.nullbyte.covid_19support.ui.fragments.search.SearchFragment;
import com.nullbyte.covid_19support.ui.fragments.tracker.TrackerFragment;
import com.nullbyte.covid_19support.ui.fragments.updates.UpdatesFragment;
import com.nullbyte.covid_19support.utilities.DialogHelperUtility;
import com.nullbyte.covid_19support.utilities.ISOCodeUtility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
    private ArrayList<ContactModel> mStatesNumList = new ArrayList<>();
    private AlertDialog mAlertDialog;

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
        FloatingActionButton mFab = findViewById(R.id.floating_action_button);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogHelperUtility.customClosableDialog(MainActivity.this, R.layout.emergency_layout, (view, dialog) -> {
                    getHelplineNumbers();
                    RecyclerView contactList = view.findViewById(R.id.rv_contact_list);
                    contactList.setLayoutManager(new LinearLayoutManager(getApplication()));
                    contactList.setAdapter(new EmergencyNumberAdapter(mStatesNumList));
                    mAlertDialog = dialog;
                    mAlertDialog.show();
                });
            }

            private void getHelplineNumbers() {
                mStatesNumList.add(new ContactModel("Andhra Pradesh", "0866-2410978"));
                mStatesNumList.add(new ContactModel("Arunachal Pradesh", "9436055743"));
                mStatesNumList.add(new ContactModel("Assam", "6913347770"));
                mStatesNumList.add(new ContactModel("Bihar", "104"));
                mStatesNumList.add(new ContactModel("Chhattisgarh", "104"));
                mStatesNumList.add(new ContactModel("Goa", "104"));
                mStatesNumList.add(new ContactModel("Gujarat", "104"));
                mStatesNumList.add(new ContactModel("Haryana", "8558893911"));
                mStatesNumList.add(new ContactModel("Himachal Pradesh", "104"));
                mStatesNumList.add(new ContactModel("Jharkhand", "104"));
                mStatesNumList.add(new ContactModel("Karnataka", "104"));
                mStatesNumList.add(new ContactModel("Kerala", "0471-2552056"));
                mStatesNumList.add(new ContactModel("Madhya Pradesh ", "104"));
                mStatesNumList.add(new ContactModel("Maharashtra", "020-26127394"));
                mStatesNumList.add(new ContactModel("Manipur", "3852411668"));
                mStatesNumList.add(new ContactModel("Meghalaya", "108"));
                mStatesNumList.add(new ContactModel("Mizoram", "102"));
                mStatesNumList.add(new ContactModel("Nagaland", "7005539653"));
                mStatesNumList.add(new ContactModel("Odisha", "9439994859"));
                mStatesNumList.add(new ContactModel("Punjab", "104"));
                mStatesNumList.add(new ContactModel("Rajasthan", "0141-2225624"));
                mStatesNumList.add(new ContactModel("Sikkim", "104"));
                mStatesNumList.add(new ContactModel("Tamil Nadu", "044-29510500"));
                mStatesNumList.add(new ContactModel("Telangana", "104"));
                mStatesNumList.add(new ContactModel("Tripura", "0381-2315879"));
                mStatesNumList.add(new ContactModel("Uttarakhand", "104"));
                mStatesNumList.add(new ContactModel("Uttar Pradesh", "18001805145"));
                mStatesNumList.add(new ContactModel("West Bengal ", "03323412600"));
                mStatesNumList.add(new ContactModel("Andaman and Nicobar Islands", "03192-232102"));
                mStatesNumList.add(new ContactModel("Chandigarh", "9779558282"));
                mStatesNumList.add(new ContactModel("Dadra and Nagar Haveli", "104"));
                mStatesNumList.add(new ContactModel("Daman & Diu", "104"));
                mStatesNumList.add(new ContactModel("Delhi", "011-22307145"));
                mStatesNumList.add(new ContactModel("Jammu & Kashmir", "0194-2440283"));
                mStatesNumList.add(new ContactModel("Ladakh", "01982256462"));
                mStatesNumList.add(new ContactModel("Lakshadweep", "104"));
                mStatesNumList.add(new ContactModel("Puducherry", "104"));
            }
        });

        mToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.credits) {
                Intent intent = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.reset_country) {
                setMyCountry();
            }
            return false;
        });

        fm.beginTransaction().add(R.id.main_container, countryFragment, "5").hide(countryFragment).commit();
        fm.beginTransaction().add(R.id.main_container, updatesFragment, "4").hide(updatesFragment).commit();
        fm.beginTransaction().add(R.id.main_container, infoFragment, "3").hide(infoFragment).commit();
        fm.beginTransaction().add(R.id.main_container, searchFragment, "2").hide(searchFragment).commit();
        fm.beginTransaction().add(R.id.main_container, trackerFragment, "1").commit();

        mMyCountry.setOnClickListener(view -> {
            if (sharedpreferences.getString(Constant.COUNTRY_NAME, "DEFAULT").equals("DEFAULT")) {
                setMyCountry();
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
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.white));
                    mRadioGroup.setVisibility(View.VISIBLE);
                    mTitleBar.setVisibility(View.GONE);
                    mWorld.performClick();
                    mTitleBar.setText(R.string.home);
                    active = trackerFragment;
                    break;
                case R.id.country:
                    fm.beginTransaction().hide(active).show(searchFragment).commit();
                    mTitleBar.setText(R.string.search_by_country);
                    mRadioGroup.setVisibility(View.GONE);
                    mTitleBar.setVisibility(View.VISIBLE);
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.accent));
                    active = searchFragment;
                    break;
                case R.id.info:
                    fm.beginTransaction().hide(active).show(infoFragment).commit();
                    mTitleBar.setText(R.string.info);
                    mRadioGroup.setVisibility(View.GONE);
                    mTitleBar.setVisibility(View.VISIBLE);
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.accent));
                    active = infoFragment;
                    break;
                case R.id.updates:
                    fm.beginTransaction().hide(active).show(updatesFragment).commit();
                    mTitleBar.setText(R.string.latest_update);
                    mRadioGroup.setVisibility(View.GONE);
                    mTitleBar.setVisibility(View.VISIBLE);
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.accent));
                    active = updatesFragment;
                    break;
            }
            return true;
        });
    }

    private void setMyCountry() {
        DialogHelperUtility.customDialog(this, R.layout.item_set_country, (dialogView, dialog) -> {
            Button submit;
            Spinner mCountryName;
            SharedPreferences.Editor editor = sharedpreferences.edit();
            mCountryName = dialogView.findViewById(R.id.spinner_country_name);

            List<String> countryList;
            countryList = ISOCodeUtility.getCountryList();
            Collections.sort(countryList);
            countryList.add(0, "Select your country");

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, countryList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mCountryName.setAdapter(adapter);
            submit = dialogView.findViewById(R.id.btn_submit);

            submit.setOnClickListener(view1 -> {
                if (mCountryName.getSelectedItem() != null && mCountryName.getSelectedItemPosition() > 0) {
                    String countryName = mCountryName.getSelectedItem().toString();
                    editor.putString(Constant.COUNTRY_NAME, countryName);
                    editor.apply();
                    fm.beginTransaction().detach(countryFragment).attach(countryFragment).commit();
                    dialog.dismiss();
                } else
                    Toast.makeText(MainActivity.this, "Please select your country", Toast.LENGTH_SHORT).show();
            });
            dialog.show();
        });
    }

    @Override
    public void onBackPressed() {
        if (active != trackerFragment) {
            bottomNavigationView.setSelectedItemId(R.id.home);
            fm.beginTransaction().hide(active).show(trackerFragment).commit();
            mRadioGroup.setVisibility(View.VISIBLE);
            mTitleBar.setBackgroundColor(getResources().getColor(R.color.white));
            mWorld.performClick();
            mTitleBar.setText(R.string.home);
            active = trackerFragment;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
