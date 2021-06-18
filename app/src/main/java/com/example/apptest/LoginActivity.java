package com.example.apptest;

import android.content.Intent;
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
// test
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
    private Button logoutButton;

    private EditText name, password;
    List<UserItem> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initMyAPI(BASE_URL);
        getUserList();

        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);
        logoutButton = findViewById(R.id.logout_button);
        name = (EditText) findViewById(R.id.login_name);
        password = (EditText) findViewById(R.id.login_password);

        if (LoginActivity.is_login) {
            loginButton.setVisibility(View.GONE);
            registerButton.setVisibility(View.GONE);
            name.setVisibility(View.GONE);
            password.setVisibility(View.GONE);
        }
        else logoutButton.setVisibility(View.GONE);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                UserItem item = new UserItem();
                item.setName(name.getText().toString());
                item.setPassword(password.getText().toString());
                for (UserItem u: userList) {
                    if (u.getName().equals(item.getName())) {
                        Toast myToast = Toast.makeText(getApplicationContext(),"중복된 아이디가 존재합니다.", Toast.LENGTH_SHORT);
                        myToast.show();
                        return ;
                    }
                }

                Call<UserItem> userCall = mMyAPI.post_users(item);
                userCall.enqueue(new Callback<UserItem>() {
                    @Override
                    public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                        if (response.isSuccessful()) {
                            Toast myToast = Toast.makeText(getApplicationContext(),"회원가입 성공!", Toast.LENGTH_SHORT);
                            myToast.show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            return ;
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
                            for (UserItem u: userList) {
                                if (u.getName().equals(item.getName()) && u.getPassword().equals(item.getPassword())) {
                                    LoginActivity.user = u;
                                    LoginActivity.is_login = true;
                                    Toast myToast = Toast.makeText(getApplicationContext(),"로그인 성공!", Toast.LENGTH_SHORT);
                                    myToast.show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
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

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (LoginActivity.is_login) {
                    LoginActivity.is_login = false;
                    LoginActivity.user = null;
                    Toast myToast = Toast.makeText(getApplicationContext(),"로그아웃하셨습니다.", Toast.LENGTH_SHORT);
                    myToast.show();
                    loginButton.setVisibility(View.VISIBLE);
                    logoutButton.setVisibility(View.GONE);
                    registerButton.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);
                    password.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initMyAPI(String baseUrl){

        Log.d(TAG,"initMyAPI : " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMyAPI = retrofit.create(MyAPI.class);
    }

    private void getUserList() {
        Call<List<UserItem>> userListCall = mMyAPI.get_users();
        userListCall.enqueue(new Callback<List<UserItem>>() {
            @Override
            public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) {
                if (response.isSuccessful()) userList = response.body();
            }
            @Override
            public void onFailure(Call<List<UserItem>> call, Throwable t) {
                Log.d(TAG, "Fail msg : " + t.getMessage());
            }
        });
    }
}