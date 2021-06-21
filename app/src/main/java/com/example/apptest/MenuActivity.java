package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity { // 메뉴 화면을 위한 클래스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button head = (Button)findViewById(R.id.headache);
        Button stomach = (Button)findViewById(R.id.stomachache);
        Button abrasion = (Button)findViewById(R.id.abrasion);
        Button dermatitis = (Button)findViewById(R.id.dermatitis);

        head.setOnClickListener(new View.OnClickListener(){ // 두통 증상 클릭시 기능 구현
            @Override
            public void onClick(View v) { // 두통 관련 약품을 보여주는 액티비티 시작
                Intent intent = new Intent(MenuActivity.this, MedicineListViewActivity.class);
                intent.putExtra("category", "headache");
                startActivity(intent);
            }
        });
        stomach.setOnClickListener(new View.OnClickListener(){ // 복통 증상 클릭시 기능 구현
            @Override
            public void onClick(View v) { // 복통 관련 약품을 보여주는 액티비티 시작
                Intent intent = new Intent(MenuActivity.this, MedicineListViewActivity.class);
                intent.putExtra("category", "stomachache");
                startActivity(intent);
            }
        });
        abrasion.setOnClickListener(new View.OnClickListener(){ // 찰과상 증상 클릭시 기능 구현
            @Override
            public void onClick(View v) { // 찰과상 관련 약품을 보여주는 액티비티 시작
                Intent intent = new Intent(MenuActivity.this, MedicineListViewActivity.class);
                intent.putExtra("category", "abrasion");
                startActivity(intent);
            }
        });
        dermatitis.setOnClickListener(new View.OnClickListener(){ // 피부염 증상 클릭시 기능 구현
            @Override
            public void onClick(View v) { // 피부염 관련 약품을 보여주는 액티비티 시작
                Intent intent = new Intent(MenuActivity.this, MedicineListViewActivity.class);
                intent.putExtra("category", "dermatitis");
                startActivity(intent);
            }
        });

    }
}