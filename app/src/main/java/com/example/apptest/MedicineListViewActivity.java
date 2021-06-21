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

public class MedicineListViewActivity extends AppCompatActivity {
    private final  String TAG = getClass().getSimpleName();

    private String category;
    GridView gridView;
    private MedicineAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        setContentView(R.layout.medicine_list);
        category = getIntent().getStringExtra("category");
        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new MedicineAdapter(this);
        ArrayList<MedicineItem> list = new ArrayList<MedicineItem>();
        Call<List<MedicineItem>> getCall = MyAPICall.mMyAPI.get_medicines_by_category(category);

        getCall.enqueue(new Callback<List<MedicineItem>>() {
            @Override
            public void onResponse(Call<List<MedicineItem>> call, Response<List<MedicineItem>> response) {
                if(response.isSuccessful()) {
                    List<MedicineItem> mList = response.body();
                    for( MedicineItem item : mList){
                        int id = item.getId();
                        String name = item.getName();
                        String explanation = item.getExplanation();
                        Float rate = item.getRate();
                        List<CommentItem> comments = item.getComments();
                        String image = item.getImage();
                        adapter.addItem(new MedicineItem(id, name, explanation, rate, image));
                    }
                    gridView.setAdapter(adapter);
                } else {
                    Log.d(TAG, "Status Code : " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<MedicineItem>> call, Throwable t) {
                Log.d(TAG,"Fail msg : " + t.getMessage());
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {
                MedicineItem item = (MedicineItem) adapter.getItem(index);
                Intent intent = new Intent(MedicineListViewActivity.this, MedicineItemViewActivity.class);
                intent.putExtra("id", item.getId());
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), "클릭: " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
