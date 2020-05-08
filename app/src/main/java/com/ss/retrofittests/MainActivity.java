package com.ss.retrofittests;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.ss.retrofittests.model.LanguageModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String API_KEY = "trnsl.1.1.20200507T081401Z.4caa33daf596e9cb.08b226257c92b02eb19f85e91d48207a7ac0c3a2";
    String API_URL = "https://translate.yandex.net/api/v1.5/tr.json/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YandexAPIInterface yandexAPIInterface = retrofit.create(YandexAPIInterface.class);

        Call<LanguageModel> call = yandexAPIInterface.getLangs(API_KEY);

        call.enqueue(new Callback<LanguageModel>() {
            @Override
            public void onResponse(Call<LanguageModel> call, Response<LanguageModel> response) {
                if (!response.isSuccessful()){
                    Log.d("MainActivity", "Code: " + response.code());
                    return;
                }

                Log.d("MainActivity" , response.body().getDirs().toString());
            }

            @Override
            public void onFailure(Call<LanguageModel> call, Throwable t) {
                Log.d("MainActivity", "Что то пошло не так: " + t.getMessage());
            }
        });
    }
}
