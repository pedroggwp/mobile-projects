package com.example.retrofitapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.retrofitapi.adapter.PhotoAdapter;
import com.example.retrofitapi.api.PhotoApi;
import com.example.retrofitapi.model.Photo;
import com.google.gson.Gson;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity_getPhoto extends AppCompatActivity {

    private RecyclerView photoRecyclerView;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_get_photo);

        photoRecyclerView = findViewById(R.id.fotoRecyclerView);

        // config recycler view
        photoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // chamar API
        Intent intent = getIntent();

        if (intent.getBooleanExtra("byId", false)) {
            callRetrofitApiById();
        } else {
            callRetrofitApi();
        }

    }

    private void callRetrofitApiById() {
        String baseUrl = "https://jsonplaceholder.typicode.com";

        // config API
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // criar chamada
        PhotoApi photoApi = retrofit.create(PhotoApi.class);
        Call<Photo> call = photoApi.getById("12");

        call.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                List<Photo> photo = Collections.singletonList(response.body());
                photoRecyclerView.setAdapter(new PhotoAdapter(photo));
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable throwable) {
                Log.e("error", throwable.getMessage());
            }
        });
    }

    private void callRetrofitApi() {
        String baseUrl = "https://jsonplaceholder.typicode.com";

        // config API
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // criar chamada
        PhotoApi photoApi = retrofit.create(PhotoApi.class);
        Call<List<Photo>> call = photoApi.getAll();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                List<Photo> allPhotos = response.body();
                photoRecyclerView.setAdapter(new PhotoAdapter(allPhotos));
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable throwable) {
                Log.e("error", throwable.getMessage());
            }
        });
    }
}