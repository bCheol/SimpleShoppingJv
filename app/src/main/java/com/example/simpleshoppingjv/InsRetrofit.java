package com.example.simpleshoppingjv;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsRetrofit {

    RetrofitAPI retrofitAPI;

    public InsRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }

    private static class singleInstanceHolder {
        private static final InsRetrofit INSTANCE = new InsRetrofit();
    }
    public static synchronized InsRetrofit getInstance() {
        return singleInstanceHolder.INSTANCE;
    }
}