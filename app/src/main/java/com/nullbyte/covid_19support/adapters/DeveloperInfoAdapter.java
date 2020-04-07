package com.nullbyte.covid_19support.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.constants.Constant;
import com.nullbyte.covid_19support.ui.activities.WebActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DeveloperInfoAdapter extends RecyclerView.Adapter<DeveloperInfoAdapter.ViewHolder> {

    private ArrayList<String> mName, mEmail;
    private Context mContext;

    public DeveloperInfoAdapter(ArrayList<String> mName, ArrayList<String> mEmail) {
        this.mName = mName;
        this.mEmail = mEmail;
    }

    @NonNull
    @Override
    public DeveloperInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_developer_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeveloperInfoAdapter.ViewHolder holder, int position) {
        holder.name.setText(mName.get(position));
        holder.email.setText(mEmail.get(position));
        holder.github.setOnClickListener(view -> {
            if (mName.get(position).equals("Harishiv Singh")) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url", Constant.HARISHIV_GITHUB_URL);
                mContext.startActivity(intent);
            } else if(mName.get(position).equals("Anant Raman")) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url", Constant.ANANT_GITHUB_URL);
                mContext.startActivity(intent);
            }
        });
        holder.linkedIn.setOnClickListener(view -> {
            if (mName.get(position).equals("Harishiv Singh")) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url", Constant.HARISHIV_LINKEDIN_URL);
                mContext.startActivity(intent);
            } else if(mName.get(position).equals("Anant Raman")) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url", Constant.ANANT_LINKEDIN_URL);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mName.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, email;
        CircleImageView github, linkedIn;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_developer_name);
            email = itemView.findViewById(R.id.tv_email);
            github = itemView.findViewById(R.id.civ_github);
            linkedIn = itemView.findViewById(R.id.civ_linkedIn);
        }
    }
}
