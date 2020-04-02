package com.nullbyte.covid_19support.ui.updates;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.adapters.NewsArticleAdapter;
import com.nullbyte.covid_19support.constants.Constant;
import com.nullbyte.covid_19support.databinding.FragmentUpdatesBinding;
import com.nullbyte.covid_19support.models.ArticleModel;
import com.nullbyte.covid_19support.models.NewsModel;
import com.nullbyte.covid_19support.rests.ApiClient;
import com.nullbyte.covid_19support.rests.ApiInterface;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatesFragment extends Fragment {

    private UpdatesViewModel updatesViewModel;
    private FragmentUpdatesBinding mUpdatesBinding;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        updatesViewModel =
                ViewModelProviders.of(this).get(UpdatesViewModel.class);
        mUpdatesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_updates, container, false);
        mUpdatesBinding.setInfoViewModel(updatesViewModel);

        mUpdatesBinding.refreshUpdates.setOnRefreshListener(this::getUpdates);
        mUpdatesBinding.rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));

        getUpdates();

        return mUpdatesBinding.getRoot();
    }

    private void getUpdates() {
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<NewsModel> call = apiService.getLatestNews("corona", "en", Constant.NEWS_KEY_VALUE);

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(@NotNull Call<NewsModel> call, @NotNull Response<NewsModel> response) {
                if (response.body() != null && response.body().getStatus().equals("ok")) {
                    List<ArticleModel> articleList = response.body().getArticles();
                    if (articleList.size() > 0) {
                        mUpdatesBinding.rvNews.setAdapter(new NewsArticleAdapter(articleList));
                    }
                    mUpdatesBinding.refreshUpdates.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(@NotNull Call<NewsModel> call, @NotNull Throwable t) {
                Snackbar snackbar = Snackbar.make(mUpdatesBinding.getRoot(), "Please check your network connection", Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundTintList(ContextCompat.getColorStateList(Objects.requireNonNull(getActivity()), R.color.red));
                snackbar.show();
                Log.i("Error", t.toString());
                mUpdatesBinding.refreshUpdates.setRefreshing(false);
            }
        });
    }
}
