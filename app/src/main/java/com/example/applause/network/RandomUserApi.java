package com.example.applause.network;


import com.example.applause.model.Results;

import retrofit.http.GET;
import retrofit.http.Query;

public interface RandomUserApi {

    @GET("/")
    Results listUsers(@Query("results") int size);
}
