package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button head = (Button)findViewById(R.id.headache);
        Button stomach = (Button)findViewById(R.id.stomachache);
        Button abrasion = (Button)findViewById(R.id.abrasion);
        Button dermatitis = (Button)findViewById(R.id.dermatitis);

        head.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MedicineListViewActivity.class);
                intent.putExtra("category", "headache");
                startActivity(intent);
            }
        });
        stomach.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MedicineListViewActivity.class);
                intent.putExtra("category", "stomachache");
                startActivity(intent);
            }
        });
        abrasion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MedicineListViewActivity.class);
                intent.putExtra("category", "abrasion");
                startActivity(intent);
            }
        });
        dermatitis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MedicineListViewActivity.class);
                intent.putExtra("category", "dermatitis");
                startActivity(intent);
            }
        });

    }
}