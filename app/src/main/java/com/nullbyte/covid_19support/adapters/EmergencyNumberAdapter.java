package com.nullbyte.covid_19support.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.models.ContactModel;

import java.util.ArrayList;

public class EmergencyNumberAdapter extends RecyclerView.Adapter<EmergencyNumberAdapter.ViewHolder> {

    private ArrayList<ContactModel> stateNumList;
    private Context mContext;

    public EmergencyNumberAdapter(ArrayList<ContactModel> stateNumList) {
        this.stateNumList = stateNumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.stateName.setText(stateNumList.get(position).getmStateName());
        holder.contNum.setText(stateNumList.get(position).getmNumber());
        holder.itemView.setOnClickListener(view -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + stateNumList.get(position).getmNumber()));
            mContext.startActivity(dialIntent);
        });
    }

    @Override
    public int getItemCount() {
        return stateNumList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stateName, contNum;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            stateName = itemView.findViewById(R.id.tv_country_name);
            contNum = itemView.findViewById(R.id.tv_cases_count);
        }
    }
}
