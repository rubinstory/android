package com.example.apptest;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MedicineItem {
    private int id;
    private String name;
    private String explanation;
    private float rate;
    private String image;
    private List<CommentItem> comments;

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
    //public ArrayList<Integer> getCategory() { return this.category; }

    public float getRate(){ return this.rate; }
    public String getImage(){ return this.image; }

    public void setId(int id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setExplanation(String explanation){ this.explanation = explanation; }
    //public void setCategory(ArrayList<Integer> category){ this.category = category; }
    public void setName(float rate){ this.rate = rate; }
    public void setImage(String image){ this.image = image; }
}
