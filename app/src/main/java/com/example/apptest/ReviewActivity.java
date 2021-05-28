package com.example.apptest;

import android.content.Intent;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewActivity extends AppCompatActivity {
    private final  String TAG = getClass().getSimpleName();

    private final String BASE_URL = "http://192.168.35.223:8000";
    private MyAPI mMyAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_review);
        initMyAPI(BASE_URL);

        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        EditText text = (EditText)findViewById(R.id.contents);
        Button save = (Button)findViewById(R.id.saveButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!LoginActivity.is_login) {
                    Toast myToast = Toast.makeText(getApplicationContext(),"로그인을 해야 리뷰를 작성할 수 있습니다.!", Toast.LENGTH_SHORT);
                    myToast.show();
                    return ;
                }
                CommentItem comment = new CommentItem(LoginActivity.user.getId(), MedicineItemViewActivity.medicine.getId(), text.getText().toString());

                Call<CommentItem> commentCall = mMyAPI.post_comments(comment);
                commentCall.enqueue(new Callback<CommentItem>() {
                    @Override
                    public void onResponse(Call<CommentItem> call, Response<CommentItem> response) {
                        if (response.isSuccessful()) {
                            Toast myToast = Toast.makeText(getApplicationContext(),"댓글 성공!", Toast.LENGTH_SHORT);
                            myToast.show();
                            Intent intent = new Intent(ReviewActivity.this, MedicineItemViewActivity.class);
                            intent.putExtra("id", MedicineItemViewActivity.medicine.getId());
                            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            return ;
                        } else {
                            Log.d(TAG, "Status Code : " + response.code());
                            Log.d(TAG, response.errorBody().toString());
                            Log.d(TAG, call.request().body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<CommentItem> call, Throwable t) {
                        Log.d(TAG, "Fail msg : " + t.getMessage());
                    }
                });

                //float t = ratingBar.getRating();
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
}
