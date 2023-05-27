package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Etiqueta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EtiquetaApi {
    @GET("/Etiquetas")
    Call<List<Etiqueta>> getEtiquetas();
}

