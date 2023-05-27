package com.example.ofsystem.Api;

import com.example.ofsystem.Model.TipoProducto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TipoProductoApi {
    @GET("/TipoProductos")
    Call<List<TipoProducto>> getTipoProductos();
}

