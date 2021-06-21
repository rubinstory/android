package com.example.apptest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAPICall {
    static MyAPI mMyAPI;
    private static String BASE_URL = "http://192.168.35.223:8000";

    static void initMyAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mMyAPI = retrofit.create(MyAPI.class);
    }
}
