package com.nullbyte.covid_19support.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nullbyte.covid_19support.R;

import java.util.ArrayList;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {

    private ArrayList<String> countriesList;
    private ArrayList<String> casesList;

    public CountryListAdapter(ArrayList<String> countriesList, ArrayList<String> casesList) {
        this.countriesList = countriesList;
        this.casesList = casesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.countryName.setText(countriesList.get(position));
        holder.casesCount.setText(casesList.get(position));
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryName, casesCount;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            countryName = itemView.findViewById(R.id.tv_country_name);
            casesCount = itemView.findViewById(R.id.tv_cases_count);
        }
    }
}
