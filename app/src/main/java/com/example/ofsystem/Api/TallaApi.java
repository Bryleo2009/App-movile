package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Talla;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TallaApi {
    @GET("/Tallas")
    Call<List<Talla>> getTallas();
}

