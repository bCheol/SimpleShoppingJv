package com.example.simpleshoppingjv;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsRetrofit {

    private static InsRetrofit INSTANCE = null;
    RetrofitAPI retrofitAPI;

    private InsRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }

    public static synchronized InsRetrofit getInstance() {
        if(INSTANCE == null){
            INSTANCE = new InsRetrofit();
        }
        return INSTANCE;
    }
}