package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Categoria;
import com.example.ofsystem.Model.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriaApi {
    @GET("/Categorias")
    Call<List<Categoria>> getCategorias();
}

