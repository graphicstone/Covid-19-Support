package com.nullbyte.covid_19support.rests;

import com.nullbyte.covid_19support.models.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<NewsModel> getLatestNews(@Query("q") String query, @Query("language") String language, @Query("apiKey") String apiKey);
}
