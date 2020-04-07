package com.nullbyte.covid_19support.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nullbyte.covid_19support.R;

import java.util.ArrayList;

public class SpecialCreditsAdapter extends RecyclerView.Adapter<SpecialCreditsAdapter.ViewHolder> {

    private ArrayList<String> mCreditsName, mCreditFor;

    public SpecialCreditsAdapter(ArrayList<String> mCreditsName, ArrayList<String> mCreditFor) {
        this.mCreditsName = mCreditsName;
        this.mCreditFor = mCreditFor;
    }

    @NonNull
    @Override
    public SpecialCreditsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special_credits, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialCreditsAdapter.ViewHolder holder, int position) {
        holder.creditName.setText(mCreditsName.get(position));
        holder.creditFor.setText(mCreditFor.get(position));
    }

    @Override
    public int getItemCount() {
        return mCreditsName.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView creditName, creditFor;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            creditName = itemView.findViewById(R.id.tv_credit_name);
            creditFor = itemView.findViewById(R.id.tv_credit_for);
        }
    }
}
