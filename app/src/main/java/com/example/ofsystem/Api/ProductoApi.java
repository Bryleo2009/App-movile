package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Producto;
import com.example.ofsystem.Model.Rol;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductoApi {
    @GET("/Productos/listar")
    Call<List<Producto>> getProductos();
}

