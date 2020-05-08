package com.ss.retrofittests;

import com.ss.retrofittests.model.LanguageModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YandexAPIInterface {

    @GET("getLangs")
    Call<LanguageModel> getLangs(@Query("key") String API_KEY);
}
