package com.example.apptest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MedicineItemView extends LinearLayout {
    TextView nameView;
    TextView rateView;
    //ImageView imageView;

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
        rateView = (TextView) findViewById(R.id.rateView);
        //imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void setName(String name) { nameView.setText(name); }
    public void setRate(float rate) { rateView.setText(Float.toString(rate)); }
    //public void setImage(int resId) { imageView.setImageResource(resId); }

}
