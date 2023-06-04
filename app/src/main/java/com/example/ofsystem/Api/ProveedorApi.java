package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Proveedor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProveedorApi {
    @GET("/Proveedors")
    Call<List<Proveedor>> getProveedors();

    @POST("/Proveedors")
    Call<Void> createProveedor (@Body Proveedor Proveedor);

    @PUT("/Proveedors")
    Call<Void> modificareProveedor (@Body Proveedor Proveedor);

    @DELETE("/Proveedors/{id}")
    Call<Void> eliminarProveedor(@Path("id") String id);
}

