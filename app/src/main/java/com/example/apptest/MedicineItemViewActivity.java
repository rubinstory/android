package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicineItemViewActivity extends AppCompatActivity {
    private final  String TAG = getClass().getSimpleName();
    private final String BASE_URL = "http://192.168.35.223:8000";
    private MyAPI mMyAPI;

    private int id;
    private ArrayAdapter adapter;

    private Button review;
    private ListView listView;
    private TextView nameView;
    private TextView explanationView;

    static MedicineItem medicine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_view);
        id = getIntent().getIntExtra("id", 0);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        review = (Button)findViewById(R.id.review);
        listView = (ListView)findViewById(R.id.reviewList);
        nameView = (TextView)findViewById(R.id.nameView);
        explanationView = (TextView)findViewById(R.id.explanationView);

        initMyAPI(BASE_URL);
        Call<MedicineItem> getCall = mMyAPI.get_medicines_by_id(id);

        getCall.enqueue(new Callback<MedicineItem>() {
            @Override
            public void onResponse(Call<MedicineItem> call, Response<MedicineItem> response) {
                if(response.isSuccessful()) {
                    MedicineItem item = response.body();
                    medicine = item;
                    List<CommentItem> comments = item.getComments();
                    nameView.setText(item.getName());
                    explanationView.setText(item.getExplanation());
                    for(CommentItem c: comments) {
                        adapter.add(c.getText());
                    }
                    listView.setAdapter(adapter);
                } else {
                    Log.d(TAG, "Status Code : " + response.code());
                }
            }
            @Override
            public void onFailure(Call<MedicineItem> call, Throwable t) {
                Log.d(TAG,"Fail msg : " + t.getMessage());
            }
        });

        review.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicineItemViewActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });//리뷰 버튼 눌렀을때
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