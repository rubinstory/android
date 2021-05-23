package com.example.apptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final  String TAG = getClass().getSimpleName();

    // server의 url을 적어준다
    private final String BASE_URL = "http://95654b229aa6.ngrok.io";
    private MyAPI mMyAPI;
    private ImageButton mGetButton;
    private ImageButton mPostButton;
    private ImageButton mPatchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGetButton = findViewById(R.id.button1);
        mGetButton.setOnClickListener(this);
        mPostButton = findViewById(R.id.button2);
        mPostButton.setOnClickListener(this);
        mPatchButton = findViewById(R.id.button3);
        mPatchButton.setOnClickListener(this);
        initMyAPI(BASE_URL);

        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        mGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
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


    @Override
    public void onClick(View v) {
        if( v == mGetButton){
            /*
            Log.d(TAG,"GET");
            Call<List<PostItem>> getCall = mMyAPI.get_posts();
            getCall.enqueue(new Callback<List<PostItem>>() {
                @Override
                public void onResponse(Call<List<PostItem>> call, Response<List<PostItem>> response) {
                    if(response.isSuccessful()){
                        List<PostItem> mList = response.body();
                        String result ="";
                        for( PostItem item : mList){
                            result += "title : " + item.getTitle() + " text: " + item.getText() + "\n";
                        }
                        mListTv.setText(result);
                    }else {
                        Log.d(TAG,"Status Code : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<PostItem>> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                }
            });*/
        }else if(v == mPostButton){
            /*
            Log.d(TAG,"POST");


            PostItem item = new PostItem();
            item.setTitle("Android title");
            item.setText("Android text");
            Call<PostItem> postCall = mMyAPI.post_posts(item);
            postCall.enqueue(new Callback<PostItem>() {
                @Override
                public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG,"등록 완료");
                    }else {
                        Log.d(TAG,"Status Code : " + response.code());
                        Log.d(TAG,response.errorBody().toString());
                        Log.d(TAG,call.request().body().toString());
                    }
                }

                @Override
                public void onFailure(Call<PostItem> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                }
            });*/

        }else if( v == mPatchButton){
            Log.d(TAG,"PATCH");
            PostItem item = new PostItem();
            item.setTitle("android patch title");
            item.setText("android patch text");
            //pk 값은 임의로 하드코딩하였지만 동적으로 setting 해서 사용가능
            Call<PostItem> patchCall = mMyAPI.patch_posts(1,item);
            patchCall.enqueue(new Callback<PostItem>() {
                @Override
                public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG,"patch 성공");
                    }else{
                        Log.d(TAG,"Status Code : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<PostItem> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                }
            });
        }
    }
}