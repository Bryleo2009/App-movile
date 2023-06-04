package com.example.ofsystem.Api;

import com.example.ofsystem.Model.MedioPago;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MedioPagoApi {
    @GET("/MedioPagos")
    Call<List<MedioPago>> getMedioPagos();

    @POST("/MedioPagos")
    Call<Void> createMedioPago (@Body MedioPago MedioPago);

    @PUT("/MedioPagos")
    Call<Void> modificareMedioPago (@Body MedioPago MedioPago);

    @DELETE("/MedioPagos/{id}")
    Call<Void> eliminarMedioPago(@Path("id") int id);
}

