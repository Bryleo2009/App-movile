package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Producto;
import com.example.ofsystem.Model.ProductoFilter;
import com.example.ofsystem.Model.RegistroProductFilter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface ProductoApi {
    @GET("/Productos")
    Call<List<ProductoFilter>> getProductos();

    @POST("/Productos/filter")
    Call<Void> createProduct (@Body RegistroProductFilter unProducto);

    @PUT("/Productos")
    Call<Void> modificareProduct (@Body RegistroProductFilter unProducto);

    @DELETE("/Productos/{id}")
    Call<Void> eliminarProducto(@Path("id") int id);

}

