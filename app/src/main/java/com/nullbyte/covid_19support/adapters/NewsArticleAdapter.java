package com.nullbyte.covid_19support.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.models.ArticleModel;
import com.nullbyte.covid_19support.ui.WebActivity;

import java.util.List;

public class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.ViewHolder> {

    private List<ArticleModel> articleArrayList;
    private Context mContext;

    public NewsArticleAdapter(List<ArticleModel> articleArrayList) {
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsArticleAdapter.ViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_image_grey_24dp)
                .error(R.drawable.ic_broken_image_grey_24dp);

        Glide.with(mContext).load(articleArrayList.get(position).getUrlToImage()).centerCrop().apply(options).into(holder.image);
        holder.title.setText(articleArrayList.get(position).getTitle());
        holder.description.setText(articleArrayList.get(position).getDescription());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("url", articleArrayList.get(position).getUrl());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        ImageView image;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_article_title);
            description = itemView.findViewById(R.id.tv_article_description);
            image = itemView.findViewById(R.id.iv_article_image);
        }
    }
}
