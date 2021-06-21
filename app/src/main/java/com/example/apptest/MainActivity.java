package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{ // 메인 액티비티 클래스
    private final String TAG = getClass().getSimpleName();
    private Button medicineButton;
    private Button loginButton;
    private Button pharmacyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyAPICall.initMyAPI(); // API 통신을 위한 initial 함수 호출
        medicineButton = findViewById(R.id.button1);
        medicineButton.setOnClickListener(this);
        loginButton = findViewById(R.id.button2);
        loginButton.setOnClickListener(this);
        pharmacyButton = findViewById(R.id.button3);
        pharmacyButton.setOnClickListener(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        medicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
        pharmacyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PharmacyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {}
}