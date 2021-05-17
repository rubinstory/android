package com.example.apptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button head = (Button)findViewById(R.id.headache);
        Button stomach = (Button)findViewById(R.id.stomachache);
        Button abrasion = (Button)findViewById(R.id.abrasion);
        Button skin = (Button)findViewById(R.id.skin);

        head.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, HeadActivity.class);
                startActivity(intent);
            }
        });
        stomach.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, StomachActivity.class);
                startActivity(intent);
            }
        });
        abrasion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AbrasionActivity.class);
                startActivity(intent);
            }
        });


    }
}