package com.example.apptest;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MedicineItem {
    private int id;
    private String name;
    private String explanation;
    private float rate;
    private int resId;
    public MedicineItem(int id, String name, String explanation,  float rate) {
        this.id = id; this.name = name; this.explanation = explanation; this.rate = rate;
    }

    public MedicineItem(int id, String name, String explanation,  float rate, int resId) {
        this.id = id; this.name = name; this.explanation = explanation; this.rate = rate; this.resId = resId;
    }
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getExplanation() { return this.explanation; }

    //public ArrayList<Integer> getCategory() { return this.category; }

    public float getRate(){ return this.rate; }
    public int getResId(){ return this.resId; }

    public void setId(int id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setExplanation(String explanation){ this.explanation = explanation; }
    //public void setCategory(ArrayList<Integer> category){ this.category = category; }
    public void setName(float rate){ this.rate = rate; }
    public void setResId(int resId){ this.resId = resId; }
}
