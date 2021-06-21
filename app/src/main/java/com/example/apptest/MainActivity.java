package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final  String TAG = getClass().getSimpleName();

    // server의 url을 적어준다
    private final String BASE_URL = "http://192.168.35.223:8000";
    private MyAPI mMyAPI;
    //private ImageButton mGetButton;
    //private ImageButton mPostButton;
    //private ImageButton mPatchButton;
    private Button mGetButton;
    private Button mPostButton;
    private Button mPatchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGetButton = findViewById(R.id.button1);
        mGetButton.setOnClickListener(this);
        mPostButton = findViewById(R.id.button2);
        mPostButton.setOnClickListener(this);
        mPatchButton = findViewById(R.id.button3);
        mPatchButton.setOnClickListener(this);
        initMyAPI(BASE_URL);

        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        mGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
        mPatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PharmacyActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initMyAPI(String baseUrl) {
        Log.d(TAG, "initMyAPI : " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mMyAPI = retrofit.create(MyAPI.class);
    }

    @Override
    public void onClick(View view) {

    }
}