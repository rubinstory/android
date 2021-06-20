package com.example.apptest;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable icon;
    private String text;
    private float numStar;
    private String name;

    public void setIcon(Drawable icon)
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
    public Drawable getIcon() {
        return this.icon ;
    }
    public float getNumStar() {
        return this.numStar ;
    }
    public String getText() {
        return this.text ;
    }
    public String getName() { return this.name ; }

}
