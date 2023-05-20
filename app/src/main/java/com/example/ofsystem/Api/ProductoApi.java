package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Producto;
import com.example.ofsystem.Model.ProductoFilter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductoApi {
    @GET("/Productos")
    Call<List<ProductoFilter>> getProductos();

    @POST("users")
    Call<ProductoFilter> createUser(@Body ProductoFilter unProducto);

}

