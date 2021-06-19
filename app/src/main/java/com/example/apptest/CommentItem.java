package com.example.apptest;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CommentItem {
    //private int id;
    private int user;
    private int medicine;
    private String text;
    private float rate;

    public CommentItem(int user, int medicine, String text) {
        this.user = user; this.medicine = medicine; this.text = text; }
    public CommentItem(int user, int medicine, String text, float rate) {
        this.user = user; this.medicine = medicine; this.text = text; this.rate = rate;}

    //public void setId(int id) { this.id = id; }
    public void setText(String text) { this.text = text; }

    //public int getId() { return this.id; }
    public String getText() { return this.text; }
    public float getRate() { return this.rate; }
}
