package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MedicineActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String text = intent.getExtras().getString("medicine"); // 약의 이름을 받아온다
        if(text.equals("타이레놀"))
        {
            Intent i = new Intent(MedicineActivity.this, MedicineItemViewActivity.class);
            startActivity(i);
        }
        }
    }

