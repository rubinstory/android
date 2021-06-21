package com.example.apptest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicineItemViewActivity extends AppCompatActivity { // 약품 정보와 리뷰 댓글을 보여주기 위한 클래스
    private final  String TAG = getClass().getSimpleName();

    private int id;
    private ListViewAdapter listViewAdapter;

    private Button review;
    private Button pharmacy;
    private ListView listView;
    private TextView nameView;
    private TextView explanationView;
    private ImageView imageView;

    static MedicineItem medicine;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_view);
        id = getIntent().getIntExtra("id", 0);
        listViewAdapter = new ListViewAdapter();
        review = (Button)findViewById(R.id.review);
        pharmacy = (Button)findViewById(R.id.pharmacy);
        listView = (ListView)findViewById(R.id.reviewList);
        nameView = (TextView)findViewById(R.id.nameView);
        explanationView = (TextView)findViewById(R.id.explanationView);
        imageView = (ImageView)findViewById(R.id.imageView);

        Call<MedicineItem> getCall = MyAPICall.mMyAPI.get_medicines_by_id(id); // id 값을 통해 약품을 쿼리하는 API 호출
        getCall.enqueue(new Callback<MedicineItem>() { // API에 Request를 enqueue
            @Override
            public void onResponse(Call<MedicineItem> call, Response<MedicineItem> response) { // 응답 성공 시 수행하는 내용
                if(response.isSuccessful()) { // 만약 응답이 올바르면
                    MedicineItem item = response.body(); // 수신한 약품 데이터를 저장
                    medicine = item; // 현재 약품을 item으로 저장
                    List<CommentItem> comments = item.getComments(); // 약품에 달린 리뷰 댓글 저장
                    nameView.setText(item.getName()); // nameView의 텍스트를 약품 이름으로 설정
                    explanationView.setText(item.getExplanation()); // explanationView의 텍스트를 약품 설명으로 설정
                    Glide.with(imageView).load(medicine.getImage()).into(imageView); // 약품 이미지 주소 URL을 imageView에 로딩
                    for(CommentItem c: comments) { // 모든 댓글에 대해
                        ListViewItem listViewItem = new ListViewItem(); // 새로운 댓글 객체 생성 및 정보 저장
                        listViewItem.setText(c.getText());
                        listViewItem.setNumStar(c.getRate());

                        Call<UserItem> getUserCall = MyAPICall.mMyAPI.get_user_by_id(c.getUserId()); // id 값을 통해 사용자를 쿼리하는 API 호출
                        getUserCall.enqueue(new Callback<UserItem>() { // API에 Request를 enqueue
                            @Override
                            public void onResponse(Call<UserItem> call, Response<UserItem> response) { // 응답 성공 시 수행하는 내용
                                if(response.isSuccessful()) { // 만약 응답이 올바르면
                                    UserItem userItem = response.body(); // 수신한 사용자 데이터를 저장
                                    listViewItem.setName(userItem.getName());
                                    listViewItem.setIcon(userItem.getImage());
                                }
                            }
                            @Override
                            public void onFailure(Call<UserItem> call, Throwable t) { // 실패 시
                                Log.d(TAG,"Fail msg : " + t.getMessage()); // 오류 메시지 출력
                            }
                        });

                        Handler handler = new Handler(); // 비동기 통신 때문에 API 호출이 끝난 후에 listViewAdapter에 데이터를 추가하게 끔 기다림
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                listViewAdapter.addItem(listViewItem);
                            }
                        }, 100);
                    }
                    Handler handler = new Handler(); // 비동기 통신 때문에 API 호출이 끝난 후에 listView를 생성하게 끔 기다림
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.setAdapter(listViewAdapter);
                        }
                    }, 200);
                } else {
                    Log.d(TAG, "Status Code : " + response.code());
                }
            }
            @Override
            public void onFailure(Call<MedicineItem> call, Throwable t) { // 실패 시
                Log.d(TAG,"Fail msg : " + t.getMessage()); // 오류 메시지 출력
            }
        });

        review.setOnClickListener(new View.OnClickListener(){ // 리뷰작성 버튼 클릭 시 수행하는 내용
            @Override
            public void onClick(View v) { // 리뷰 작성 액티비티 호출
                Intent intent = new Intent(MedicineItemViewActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        pharmacy.setOnClickListener(new View.OnClickListener(){ // 약국 조회 버튼 클릭 시 수행하는 내용
            @Override
            public void onClick(View v) { // 약국 조회 액티비티 호출
                Intent intent = new Intent(MedicineItemViewActivity.this, PharmacyActivity.class);
                startActivity(intent);
            }
        });
    }
}