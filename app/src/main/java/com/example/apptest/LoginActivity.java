package com.example.apptest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity{
    static boolean is_login = false;
    static UserItem user;

    private final  String TAG = getClass().getSimpleName();

    private final String BASE_URL = "http://192.168.35.223:8000";
    private MyAPI mMyAPI;


    private Button registerButton;
    private Button loginButton;

    private EditText name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
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
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                UserItem item = new UserItem();
                item.setName(name.getText().toString());
                item.setPassword(password.getText().toString());

                Call<List<UserItem>> userCall = mMyAPI.get_users();
                userCall.enqueue(new Callback<List<UserItem>> () {
                    @Override
                    public void onResponse(Call<List<UserItem>>  call, Response<List<UserItem>>  response) {
                        if (response.isSuccessful()) {
                            boolean check_name = false;
                            List<UserItem> userList = response.body();
                            for (UserItem u: userList) {
                                if (u.getName().equals(item.getName()) && u.getPassword().equals(item.getPassword())) {
                                    LoginActivity.user = item;
                                    LoginActivity.is_login = true;
                                    Toast myToast = Toast.makeText(getApplicationContext(),"로그인 성공!", Toast.LENGTH_SHORT);
                                    myToast.show();
                                    return ;
                                }
                                else if (u.getName().equals(item.getName()) && !u.getPassword().equals(item.getPassword())) {
                                    Toast myToast = Toast.makeText(getApplicationContext(),"비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT);
                                    myToast.show();
                                    return ;
                                }
                            }
                            Toast myToast = Toast.makeText(getApplicationContext(),"존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT);
                            myToast.show();
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserItem>>  call, Throwable t) {
                        Log.d(TAG, "Fail msg : " + t.getMessage());
                    }
                });
            }
        });

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
}