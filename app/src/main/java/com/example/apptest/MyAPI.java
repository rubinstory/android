package com.example.apptest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyAPI{ // API를 정의하는 인터페이스

    @POST("/users/")
    Call<UserItem> post_users(@Body UserItem user); // 새로운 사용자를 추가하는 request

    @POST("/comments/")
    Call<CommentItem> post_comments(@Body CommentItem comment); // 새로운 댓글을 추가하는 request

    @GET("/users/")
    Call<List<UserItem>> get_users(); // 모든 사용자 정보를 불러오는 request

    @GET("/users/{id}")
    Call<UserItem> get_user_by_id(@Path("id") int id); // 특정 id 값을 가진 사용자를 불러오는 request

    @GET("/medicines/")
    Call<List<MedicineItem>> get_medicines(); // 모든 약품 정보를 불러오는 request

    @GET("/medicines/{id}")
    Call<MedicineItem> get_medicines_by_id(@Path("id") int id); // 특정 id 값을 가진 약품을 불러오는 request

    @GET("/medicines/")
    Call<List<MedicineItem>> get_medicines_by_category(@Query("category") String category); // 특정 카테고리의 모든 약품들을 불러오는 request
}