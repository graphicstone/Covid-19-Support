package com.nullbyte.covid_19support.ui.help;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.adapter.CountryListAdapter;
import com.nullbyte.covid_19support.api.CasesByCountryAPI;
import com.nullbyte.covid_19support.api.SearchByCountryAPI;
import com.nullbyte.covid_19support.callback.APICallback;
import com.nullbyte.covid_19support.databinding.FragmentSearchBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class SearchFragment extends Fragment implements TextWatcher {

    private SearchViewModel mSearchViewModel;
    private FragmentSearchBinding mSearchBinding;
    private ArrayList<String> mCountriesList;
    private ArrayList<String> mCasesList;
    private ArrayList<String> mTempCountriesList;
    private ArrayList<String> mTempCasesList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mSearchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        mSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        mSearchBinding.setHelpViewModel(mSearchViewModel);

        init();
        getCountriesList();

        mSearchBinding.etSearchBar.addTextChangedListener(this);

        return mSearchBinding.getRoot();
    }

    private void getCountriesList() {
        CasesByCountryAPI casesByCountryAPI = new CasesByCountryAPI(data -> {
            data = data.substring(18, data.length() - 1);
            try {
                JSONArray jsonArr = new JSONArray(data);
                for (int i = 0; i < data.length(); ++i) {
                    JSONObject object = jsonArr.getJSONObject(i);
                    mCountriesList.add(object.getString("country_name"));
                    mCasesList.add(object.getString("cases"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mTempCountriesList.addAll(mCountriesList);
            mTempCasesList.addAll(mCasesList);
            mSearchBinding.rvCountriesList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mSearchBinding.rvCountriesList.setAdapter(new CountryListAdapter(mCountriesList, mCasesList));
        });
        casesByCountryAPI.execute();
    }

    private void getSearchedCountryData(String searchedCountry) {
        SearchByCountryAPI searchByCountryAPI = new SearchByCountryAPI(searchedCountry, data -> {
            try {
                JSONObject dataObject = new JSONObject(data);
                mCountriesList.clear();
                mCasesList.clear();
                mCountriesList.add(dataObject.getString("country"));
                mCasesList.add(dataObject.getString("total_cases"));
                Objects.requireNonNull(mSearchBinding.rvCountriesList.getAdapter()).notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        searchByCountryAPI.execute();
    }

    private void init() {
        mCountriesList = new ArrayList<>();
        mCasesList = new ArrayList<>();
        mTempCountriesList = new ArrayList<>();
        mTempCasesList = new ArrayList<>();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable inputString) {
        boolean resultFound = false;
        mCountriesList.clear();
        mCasesList.clear();
        if (inputString == null) {
            mCountriesList.addAll(mTempCountriesList);
            mCasesList.addAll(mTempCasesList);
            Objects.requireNonNull(mSearchBinding.rvCountriesList.getAdapter()).notifyDataSetChanged();
        } else {
            for (int i = 0; i < mTempCountriesList.size(); i++) {
                if (mTempCountriesList.get(i).toLowerCase().contains(inputString.toString())) {
                    mCountriesList.add(mTempCountriesList.get(i));
                    mCasesList.add(mTempCasesList.get(i));
                    Objects.requireNonNull(mSearchBinding.rvCountriesList.getAdapter()).notifyDataSetChanged();
                    resultFound = true;
                }
            }
            if (!resultFound) {
                mCountriesList.clear();
                mCasesList.clear();
                Objects.requireNonNull(mSearchBinding.rvCountriesList.getAdapter()).notifyDataSetChanged();
            }
        }
    }
}
