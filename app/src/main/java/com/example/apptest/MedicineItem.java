package com.example.apptest;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MedicineItem { // 약품 정보를 저장하기 위한 클래스
    private int id; // 약품의 id 값을 저장하는 변수
    private String name; // 약품의 이름을 저장하는 변수
    private String explanation; // 약품의 설명을 저장하는 변수
    private float rate; // 약품의 평점을 저장하는 변수
    private String image; // 약품의 이미지 주소를 저장하는 변수
    private List<CommentItem> comments; // 약품에 달린 리뷰 댓글들을 저장하는 변수

    public MedicineItem(int id, String name, String explanation,  float rate) {
        this.id = id; this.name = name; this.explanation = explanation; this.rate = rate;
    }

    public MedicineItem(int id, String name, String explanation,  float rate, List<CommentItem> comments) {
        this.id = id; this.name = name; this.explanation = explanation; this.rate = rate; this.comments = comments;
    }

    public MedicineItem(int id, String name, String explanation,  float rate, String image) {
        this.id = id; this.name = name; this.explanation = explanation; this.rate = rate; this.image = image;
    }
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getExplanation() { return this.explanation; }
    public List<CommentItem> getComments() { return this.comments; }

    public float getRate(){ return this.rate; }
    public String getImage(){ return this.image; }

    public void setId(int id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setExplanation(String explanation){ this.explanation = explanation; }
    public void setName(float rate){ this.rate = rate; }
    public void setImage(String image){ this.image = image; }
}
