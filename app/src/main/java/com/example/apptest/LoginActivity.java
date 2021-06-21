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
    static boolean is_login = false; // 사용자의 로그인 상태를 저장하는 변수
    static UserItem user; // 사용자 객체를 저장하는 변수

    private final String TAG = getClass().getSimpleName(); // 로그 출력을 위한 변수
    private Button registerButton;
    private Button loginButton;
    private Button logoutButton;

    private EditText name, password;
    List<UserItem> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        MyAPICall.initMyAPI(); // API 활성화를 위한 함수
        getUserList(); // 서버의 사용자 정보 불러오기

        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);
        logoutButton = findViewById(R.id.logout_button);
        name = (EditText) findViewById(R.id.login_name);
        password = (EditText) findViewById(R.id.login_password);

        if (LoginActivity.is_login) { // 만약 이미 로그인이 되어 있다면
            loginButton.setVisibility(View.GONE); // 로그인 버튼 비활성화
            registerButton.setVisibility(View.GONE); // 회원가입 버튼 비활성화
            name.setVisibility(View.GONE); // 이름 입력 창 비활성화
            password.setVisibility(View.GONE); // 비밀번호 입력 창 비활성화
        }
        else logoutButton.setVisibility(View.GONE); // 로그인이 안되어 있으면 로그아웃 버튼 비활성화

        registerButton.setOnClickListener(new View.OnClickListener() { // 회원가입 버튼 클릭시 기능 구현
            @Override
            public void onClick (View v) {
                UserItem item = new UserItem(); // 사용자가 입력한 정보로 새로운 UserItem 객체 선언
                item.setName(name.getText().toString()); // 입력된 이름 가져오기
                item.setPassword(password.getText().toString()); // 입력된 비민번호 가져오기
                if (item.getName().equals("") || item.getName() == null) { // 만약 이름이 입력 되지 않았다면
                    Toast myToast = Toast.makeText(getApplicationContext(),"아이디를 입력하세요.", Toast.LENGTH_SHORT);
                    myToast.show(); // 메시지 출력
                    return ;
                }
                if (item.getPassword().equals("") || item.getPassword() == null) { // 만약 비밀번호가 입력 되지 않았다면
                    Toast myToast = Toast.makeText(getApplicationContext(),"비밀번호를 입력하세요.", Toast.LENGTH_SHORT);
                    myToast.show(); // 메시지 출력
                    return ;
                }
                for (UserItem u: userList) { // 전체 사용자에 대하여
                    if (u.getName().equals(item.getName())) { // 만약 중복되는 이름이 있다면
                        Toast myToast = Toast.makeText(getApplicationContext(),"중복된 아이디가 존재합니다.", Toast.LENGTH_SHORT);
                        myToast.show(); // 메시지 출력
                        return ;
                    }
                }

                Call<UserItem> userCall = MyAPICall.mMyAPI.post_users(item); // 모든 검사가 끝나고 새로운 사용자를 생성하는 API 호출
                userCall.enqueue(new Callback<UserItem>() { // 사용자를 생성하기 위해 API에 Request를 enqueue
                    @Override
                    public void onResponse(Call<UserItem> call, Response<UserItem> response) { // 응답 성공 시 수행하는 내용
                        if (response.isSuccessful()) { // 만약 응답이 올바르면
                            Toast myToast = Toast.makeText(getApplicationContext(),"회원가입 성공!", Toast.LENGTH_SHORT);
                            myToast.show(); // 메시지 출력
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class); // 메인 액티비티 호출
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 현재 창은 스택에서 제외
                            startActivity(intent); // 새로운 액티비티 시작
                            return ;
                        } else { // 응답이 올바르지 않을 경우 오류 메시지 출력
                            Log.d(TAG, "Status Code : " + response.code());
                            Log.d(TAG, response.errorBody().toString());
                            Log.d(TAG, call.request().body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserItem> call, Throwable t) { // 응답 실패 시 수행하는 내용
                        Log.d(TAG, "Fail msg : " + t.getMessage()); // 오류 메시지 출력
                    }
                });
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() { // 로그인 버튼을 눌렀을 때 기능 구현
            @Override
            public void onClick (View v) {
                UserItem item = new UserItem(); // 사용자가 입력한 정보로 새로운 UserItem 객체 선언
                item.setName(name.getText().toString()); // 입력된 이름 가져오기
                item.setPassword(password.getText().toString()); // 입력된 비민번호 가져오기

                if (item.getName().equals("") || item.getName() == null) { // 만약 이름이 입력 되지 않았다면
                    Toast myToast = Toast.makeText(getApplicationContext(),"아이디를 입력하세요.", Toast.LENGTH_SHORT);
                    myToast.show(); // 메시지 출력
                    return ;
                }
                if (item.getPassword().equals("") || item.getPassword() == null) { // 만약 비밀번호가 입력 되지 않았다면
                    Toast myToast = Toast.makeText(getApplicationContext(),"비밀번호를 입력하세요.", Toast.LENGTH_SHORT);
                    myToast.show(); // 메시지 출력
                    return ;
                }

                Call<List<UserItem>> userCall = MyAPICall.mMyAPI.get_users(); // 사용자 정보를 모두 불러오는 API 호출
                userCall.enqueue(new Callback<List<UserItem>> () { // 사용자 정보를 불러오기 위해 API에 Request를 enqueue
                    @Override
                    public void onResponse(Call<List<UserItem>>  call, Response<List<UserItem>>  response) { // 응답 성공 시 수행하는 내용
                        if (response.isSuccessful()) {
                            boolean check_name = false; // 같은 이름 체크하는 변수
                            for (UserItem u: userList) { // 모든 사용자에 대하여
                                if (u.getName().equals(item.getName()) && u.getPassword().equals(item.getPassword())) { // 만약 계정 정보가 동일하다면
                                    LoginActivity.user = u; // 현재 사용자 객체 저장
                                    LoginActivity.is_login = true; // 로그인 체크 변수 true로 변경
                                    Toast myToast = Toast.makeText(getApplicationContext(),"로그인 성공!", Toast.LENGTH_SHORT);
                                    myToast.show(); // 메시지 출력
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class); // 메인 액티비티 호출
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 현재 창은 스택에서 제외
                                    startActivity(intent); // 새로운 액티비티 시작
                                    return ;
                                }
                                else if (u.getName().equals(item.getName()) && !u.getPassword().equals(item.getPassword())) { // 비밀번호가 틀리면
                                    Toast myToast = Toast.makeText(getApplicationContext(),"비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT);
                                    myToast.show(); // 메시지 출력
                                    return ;
                                }
                            }
                            Toast myToast = Toast.makeText(getApplicationContext(),"존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT);
                            myToast.show(); // 메시지 출력
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserItem>>  call, Throwable t) { // 응답 실패 시 수행하는 내용
                        Log.d(TAG, "Fail msg : " + t.getMessage()); // 오류 메시지 출력
                    }
                });
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() { // 로그아웃 버튼 클릭 시 수행하는 내용
            @Override
            public void onClick (View v) {
                if (LoginActivity.is_login) { // 만약 로그인이 되어 있으면
                    LoginActivity.is_login = false; // 로그인 변수 false로 변경
                    LoginActivity.user = null; // 사용자 객체 null로 초기화
                    Toast myToast = Toast.makeText(getApplicationContext(),"로그아웃하셨습니다.", Toast.LENGTH_SHORT);
                    myToast.show(); // 메시지 출력
                    loginButton.setVisibility(View.VISIBLE);
                    logoutButton.setVisibility(View.GONE);
                    registerButton.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);
                    password.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void getUserList() { // 서버로부터 사용자 정보를 모두 불러오는 함수
        Call<List<UserItem>> userListCall = MyAPICall.mMyAPI.get_users(); // 모든 사용자 정보를 불러오는 API 호출
        userListCall.enqueue(new Callback<List<UserItem>>() { // 사용자 정보를 불러오는 API에 Request를 enqueue
            @Override
            public void onResponse(Call<List<UserItem>> call, Response<List<UserItem>> response) { // 응답 성공 시 수행하는 내용
                if (response.isSuccessful()) userList = response.body(); // 사용자 정보를 userList에 저장
            }
            @Override
            public void onFailure(Call<List<UserItem>> call, Throwable t) { // 실패 시
                Log.d(TAG, "Fail msg : " + t.getMessage()); // 오류 메시지 출력
            }
        });
    }
}