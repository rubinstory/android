package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TairenolActivity extends AppCompatActivity {
    static final String[] reviewList = {"완전 최악!", "이보다 좋을 순 없ㅅ다", "b", "c", "f"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_view);
        Button review = (Button)findViewById(R.id.review);
        ListView listview = (ListView)findViewById(R.id.tairenolReviewList);

        review.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TairenolActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });//리뷰 버튼 눌렀을때

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, reviewList);
        listview.setAdapter(adapter);
    }
}