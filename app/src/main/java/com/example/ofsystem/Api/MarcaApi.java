package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Marca;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MarcaApi {
    @GET("/Marcas")
    Call<List<Marca>> getMarcas();
}

