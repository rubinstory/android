package com.example.apptest;

import android.graphics.drawable.Drawable;
import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListViewItem {
    private String icon; // 사용자 이미지 주소를 저장하는 변수
    private String text; // 사용자가 작성한 댓글의 내용을 저장하는 변수
    private float numStar; // 사용자가 작성한 평점을 저장하는 변수
    private String name; // 사용자의 이름을 저장하는 변수

    public ListViewItem() {}
    public ListViewItem(String text, float numStar, String name) {
        this.text = text; this.numStar = numStar; this.name = name;
    }
    public void setIcon(String icon)
    {
        this.icon = icon;
    }
    public void setNumStar(float star)
    {
        this.numStar = star;
    }
    public void setText(String text)
    {
        this.text = text;
    }
    public void setName(String name) { this.name = name; }
    public String getIcon() { return this.icon ; }
    public float getNumStar() {
        return this.numStar ;
    }
    public String getText() {
        return this.text ;
    }
    public String getName() { return this.name ; }
}
