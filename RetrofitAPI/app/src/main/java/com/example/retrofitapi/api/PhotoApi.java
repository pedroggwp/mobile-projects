package com.example.retrofitapi.api;

import com.example.retrofitapi.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PhotoApi {

    @GET("/photos")
    Call<List<Photo>> getAll();

    @GET("/photos/{id}")
    Call<Photo> getById(@Path("id") String id);
}
