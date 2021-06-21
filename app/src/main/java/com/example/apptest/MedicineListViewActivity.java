package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicineListViewActivity extends AppCompatActivity { // 약품들의 목록을 보여주기 위한 클래스
    private final  String TAG = getClass().getSimpleName();

    private String category; // 현재 약품의 카테고리를 저장하는 변수
    GridView gridView;
    private MedicineAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public void onResume() { // 현재 페이지가 로딩 되었을 때 기능 구현
        super.onResume();
        setContentView(R.layout.medicine_list);
        category = getIntent().getStringExtra("category"); // 현재 카테고리 설정
        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new MedicineAdapter(this);
        ArrayList<MedicineItem> list = new ArrayList<MedicineItem>();

        Call<List<MedicineItem>> getCall = MyAPICall.mMyAPI.get_medicines_by_category(category); // 카테고리별 약품들을 불러오는 API 호출
        getCall.enqueue(new Callback<List<MedicineItem>>() { // API에 Request를 enqueue
            @Override
            public void onResponse(Call<List<MedicineItem>> call, Response<List<MedicineItem>> response) { // 응답 성공 시 수행하는 내용
                if(response.isSuccessful()) { // 만약 응답이 올바르면
                    List<MedicineItem> mList = response.body(); // 약품 목록을 저장
                    for( MedicineItem item : mList){ // 모든 약품에 대하여
                        int id = item.getId(); // id 값 저장
                        String name = item.getName(); // 약품 이름 저장
                        String explanation = item.getExplanation(); // 약품 설명 저장
                        Float rate = item.getRate(); // 약품 평점 저장
                        List<CommentItem> comments = item.getComments(); // 약품에 달린 댓글 저장
                        String image = item.getImage(); // 약품의 이미지 URL 저장
                        adapter.addItem(new MedicineItem(id, name, explanation, rate, image)); // 새로운 약품 객체를 만들어 어댑터에 추가
                    }
                    gridView.setAdapter(adapter); // 그리드뷰에 반영
                } else {
                    Log.d(TAG, "Status Code : " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<MedicineItem>> call, Throwable t) { // 실패 시
                Log.d(TAG,"Fail msg : " + t.getMessage()); // 오류 메시지 출력
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 그리드 뷰 클릭 시 수행할 기능 구현
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) { // 그리드 뷰의 아이템 클릭 시
                MedicineItem item = (MedicineItem) adapter.getItem(index); // 클릭 된 약품 객체 가져오기
                // 약품의 정보와 리뷰를 보여주는 액티비티 생성
                Intent intent = new Intent(MedicineListViewActivity.this, MedicineItemViewActivity.class);
                intent.putExtra("id", item.getId()); // 현재 약품의 id 값을 같이 전달
                startActivity(intent); // 새로운 액티비티 시작
            }
        });
    }
}
