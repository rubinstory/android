package com.example.apptest;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CommentItem {
    private int id;
    private String text;
    public CommentItem(int id, String text) { this.id = id; this.text = text; }

    public void setId(int id) { this.id = id; }
    public void setText(String text) { this.text = text; }

    public int getId() { return this.id; }
    public String getText() { return this.text; }
}
