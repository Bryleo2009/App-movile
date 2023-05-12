package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductoApi {
    @GET("/Productos")
    Call<List<Producto>> getProductos();
}

