package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Cliente;
import com.example.ofsystem.Model.ProductoFilter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClienteApi {
    @GET("/Clientes")
    Call<List<Cliente>> getClientes();
}

