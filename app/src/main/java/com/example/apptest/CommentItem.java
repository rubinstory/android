package com.example.apptest;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CommentItem {
    private int user; // 댓글을 작성한 사용자의 id 값을 저장하는 변수
    private int medicine; // 댓글이 달린 약품의 id 값을 저장하는 변수
    private String text; // 댓글의 내용을 저장하는 변수
    private float rate; // 댓굴의 평점을 저장하는 변수

    public CommentItem(int user, int medicine, String text) {
        this.user = user; this.medicine = medicine; this.text = text; }
    public CommentItem(int user, int medicine, String text, float rate) {
        this.user = user; this.medicine = medicine; this.text = text; this.rate = rate;}

    public void setUserId(int user) { this.user = user; }
    public void setText(String text) { this.text = text; }

    public int getUserId() { return this.user; }
    public String getText() { return this.text; }
    public float getRate() { return this.rate; }
}
