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

public interface MyAPI{

    @POST("/posts/")
    Call<PostItem> post_posts(@Body PostItem post);

    @POST("/users/")
    Call<UserItem> post_users(@Body UserItem user);

    @POST("/comments/")
    Call<CommentItem> post_comments(@Body CommentItem comment);

    @PATCH("/posts/{pk}/")
    Call<PostItem> patch_posts(@Path("pk") int pk, @Body PostItem post);

    @DELETE("/posts/{pk}/")
    Call<PostItem> delete_posts(@Path("pk") int pk);

    @GET("/posts/")
    Call<List<PostItem>> get_posts();

    @GET("/users/")
    Call<List<UserItem>> get_users();

    @GET("/medicines/")
    Call<List<MedicineItem>> get_medicines();

    @GET("/medicines/{id}")
    Call<MedicineItem> get_medicines_by_id(@Path("id") int id);

    @GET("/medicines/")
    Call<List<MedicineItem>> get_medicines_by_category(@Query("category") String category);

    @GET("/posts/{pk}/")
    Call<PostItem> get_post_pk(@Path("pk") int pk);

}