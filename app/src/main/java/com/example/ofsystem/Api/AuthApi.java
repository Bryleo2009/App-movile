package com.example.ofsystem.Api;

import com.example.ofsystem.Model.LoginRequest;
import com.example.ofsystem.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("/login") // Endpoint para el inicio de sesión
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
