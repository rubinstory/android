package com.example.apptest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAPICall { // API 호출을 위한 클래스
    static MyAPI mMyAPI; // API를 호출하는 전역변수
    private static String BASE_URL = "http://192.168.35.223:8000"; // API의 기본 주소를 저장하는 변수

    static void initMyAPI() { // API 호출 변수에 URL을 연동하는 함수
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mMyAPI = retrofit.create(MyAPI.class);
    }
}
