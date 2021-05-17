package com.example.apptest;

import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);

        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        EditText text = (EditText)findViewById(R.id.contents);
        Button save = (Button)findViewById(R.id.saveButton);




    }
}
