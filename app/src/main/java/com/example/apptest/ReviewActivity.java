package com.example.apptest;

import android.content.Intent;
import android.graphics.Color;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewActivity extends AppCompatActivity { // 리뷰 댓글을 보여주기 위한 클래스
    private final  String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_review);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        EditText text = (EditText)findViewById(R.id.contents);
        Button save = (Button)findViewById(R.id.saveButton);

        save.setOnClickListener(new View.OnClickListener() { // 저장 버튼을 눌렀을 때 기능 구현
            @Override
            public void onClick(View view) {
                if (!LoginActivity.is_login) { // 만약 현재 로그인이 되어있지 않다면
                    Toast myToast = Toast.makeText(getApplicationContext(),"로그인을 해야 리뷰를 작성할 수 있습니다.!", Toast.LENGTH_SHORT);
                    myToast.show(); // 메시지 출력
                    return ;
                }
                CommentItem comment = new CommentItem(LoginActivity.user.getId(), // 현재 작성한 정보를 통해 새로운 댓글 객체 생성
                                                      MedicineItemViewActivity.medicine.getId(),
                                                      text.getText().toString(),
                                                      ratingBar.getRating());

                Call<CommentItem> commentCall = MyAPICall.mMyAPI.post_comments(comment); // 댓글을 추가하기 위한 API 호출
                commentCall.enqueue(new Callback<CommentItem>() { // API에 Request를 enqueue
                    @Override
                    public void onResponse(Call<CommentItem> call, Response<CommentItem> response) { // 응답 성공 시
                        if (response.isSuccessful()) { // 만약 응답이 올바르면
                            Toast myToast = Toast.makeText(getApplicationContext(),"댓글 성공!", Toast.LENGTH_SHORT);
                            myToast.show(); // 메시지 출력
                            // 댓글을 단 약품의 정보를 보여주는 액티비티 호출
                            Intent intent = new Intent(ReviewActivity.this, MedicineItemViewActivity.class);
                            intent.putExtra("id", MedicineItemViewActivity.medicine.getId()); // 현재 약품의 id 값을 같이 전달
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 현재 액티비티는 스택에서 제외
                            startActivity(intent);
                            return ;
                        } else { // 응답이 올바르지 않으면 오류 메시지 출력
                            Log.d(TAG, "Status Code : " + response.code());
                            Log.d(TAG, response.errorBody().toString());
                            Log.d(TAG, call.request().body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentItem> call, Throwable t) { // 응답 실패 시
                        Log.d(TAG, "Fail msg : " + t.getMessage()); // 오류 메시지 출력
                    }
                });
            }
        });
    }
}
