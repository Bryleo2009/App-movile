package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Color;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ColorApi {
    @GET("/Colors")
    Call<List<Color>> getColors();
}

