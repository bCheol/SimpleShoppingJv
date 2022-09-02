package com.example.simpleshoppingjv;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("/v1/search/shop.json")
    Call<GetData> getData(
            @Query("query") String query,   //검색할 항목 (EditText 값)
            @Query("display") int display,  //가져오는 갯수
            @Query("sort") String sort,     //가져오는 유형 (스피너 값)
            @Header("X-Naver-Client-Id") String id,     //네이버에서 발급 받은 id
            @Header("X-Naver-Client-Secret") String pwd //네이버에서 발급 받은 pwd
    );
}