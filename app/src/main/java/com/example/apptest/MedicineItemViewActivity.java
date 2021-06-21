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

public class MedicineItemViewActivity extends AppCompatActivity {
    private final  String TAG = getClass().getSimpleName();
    private final String BASE_URL = "http://192.168.35.223:8000";
    private MyAPI mMyAPI;

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
                    Glide.with(imageView).load(medicine.getImage()).into(imageView);
                    for(CommentItem c: comments) {
                        ListViewItem listViewItem = new ListViewItem();
                        listViewItem.setText(c.getText());
                        listViewItem.setNumStar(c.getRate());

                        Call<UserItem> getUserCall = mMyAPI.get_user_by_id(c.getUserId());
                        getUserCall.enqueue(new Callback<UserItem>() {
                            @Override
                            public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                                if(response.isSuccessful()) {
                                    UserItem userItem = response.body();
                                    listViewItem.setName(userItem.getName());
                                }
                            }
                            @Override
                            public void onFailure(Call<UserItem> call, Throwable t) {
                                Log.d(TAG,"Fail msg : " + t.getMessage());
                            }
                        });

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                listViewAdapter.addItem(listViewItem);
                            }
                        }, 100);
                    }
                    Handler handler = new Handler();
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

        pharmacy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicineItemViewActivity.this, PharmacyActivity.class);
                startActivity(intent);
            }
        });//약국 버튼 눌렀을때
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