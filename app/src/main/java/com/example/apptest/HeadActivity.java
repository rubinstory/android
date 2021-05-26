package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HeadActivity extends AppCompatActivity {
    static final String[] LIST_MENU = {"타이레놀", "게보린", "펜잘"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //화면에 보여질 내용물 뷰 설정
        setContentView(R.layout.head);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU);
        //string 배열을 입력받으므로 ArrayAdapter사용

        ListView listview = (ListView)findViewById(R.id.listview);
        listview.setAdapter(adapter);
        //리스트뷰에 어댑터 적용

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position);
                if(strText.equals("타이레놀"))
                {
                    Intent intent = new Intent(HeadActivity.this, MedicineListViewActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
