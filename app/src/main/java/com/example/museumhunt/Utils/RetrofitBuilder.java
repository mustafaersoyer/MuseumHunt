package com.example.museumhunt.Utils;

import com.example.museumhunt.Api.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    public static Retrofit retrofit;
    public static void retrofitBuild(){
         retrofit = new Retrofit.Builder()
                .baseUrl(Api.Companion.getBASE_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
