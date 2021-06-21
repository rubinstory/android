package com.example.apptest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

public class MedicineItemView extends LinearLayout { // 약품 뷰 설정을 위한 클래스
    TextView nameView;
    ImageView imageView;
    RatingBar ratingBar;

    public MedicineItemView(Context context) {
        super(context);
        init(context);
    }
    public MedicineItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.medicine_item, this, true);

        nameView = (TextView) findViewById(R.id.nameView);
        imageView = (ImageView) findViewById(R.id.imageView);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
    }

    public void setName(String name) { nameView.setText(name); }
    public void setRate(float rate) { ratingBar.setRating(rate); }
    public void setImage(String imageUrl) {
        Glide.with(this).load(imageUrl).into(imageView);
    }

}
