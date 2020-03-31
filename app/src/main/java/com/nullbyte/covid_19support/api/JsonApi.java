package com.nullbyte.covid_19support.api;

import com.nullbyte.covid_19support.model.GlobalDateWiseData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("global/count")
    Call<GlobalDateWiseData> getGlobalData();
}
