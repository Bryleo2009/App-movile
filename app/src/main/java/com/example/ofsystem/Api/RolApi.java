package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Rol;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RolApi {
    @GET("/Rols")
    Call<List<Rol>> getRols();
}

