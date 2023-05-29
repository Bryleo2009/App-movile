package com.example.ofsystem.Api;

import com.example.ofsystem.Model.Cliente;
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

public interface ClienteApi {
    @GET("/Clientes")
    Call<List<Cliente>> getClientes();

    @POST("/Clientes")
    Call<Void> createCliente (@Body Cliente cliente);

    @PUT("/Clientes")
    Call<Void> modificareCliente (@Body Cliente cliente);

    @DELETE("/Clientes/{id}")
    Call<Void> eliminarCliente(@Path("id") String id);
}

