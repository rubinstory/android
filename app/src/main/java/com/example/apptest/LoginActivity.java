package com.example.apptest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private final  String TAG = getClass().getSimpleName();

    private final String BASE_URL = "http://b65ea10860cd.ngrok.io";
    private MyAPI mMyAPI;


    private Button registerButton;
    private EditText name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(this);

        name = (EditText) findViewById(R.id.login_name);
        password = (EditText) findViewById(R.id.login_password);
        initMyAPI(BASE_URL);
    }

    private void initMyAPI(String baseUrl){

        Log.d(TAG,"initMyAPI : " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMyAPI = retrofit.create(MyAPI.class);
    }

    @Override
    public void onClick (View v) {
        Log.d(TAG, "POST");

        UserItem item = new UserItem();

        item.setName(name.getText().toString());
        item.setPassword(password.getText().toString());
        Call<UserItem> userCall = mMyAPI.post_users(item);
        userCall.enqueue(new Callback<UserItem>() {
            @Override
            public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "등록 완료");
                } else {
                    Log.d(TAG, "Status Code : " + response.code());
                    Log.d(TAG, response.errorBody().toString());
                    Log.d(TAG, call.request().body().toString());
                }
            }

            @Override
            public void onFailure(Call<UserItem> call, Throwable t) {
                Log.d(TAG, "Fail msg : " + t.getMessage());
            }
        });
    }
}