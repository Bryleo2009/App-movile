package com.example.ofsystem.Api;

import com.example.ofsystem.Model.TipoComprobante;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TipoComprobanteApi {
    @GET("/TipoComprobantes")
    Call<List<TipoComprobante>> getTipoComprobantes();

    @POST("/TipoComprobantes")
    Call<Void> createTipoComprobante (@Header("Authorization") String authToken, @Body TipoComprobante TipoComprobante);

    @PUT("/TipoComprobantes")
    Call<Void> modificareTipoComprobante (@Header("Authorization") String authToken,@Body TipoComprobante TipoComprobante);

    @DELETE("/TipoComprobantes/{id}")
    Call<Void> eliminarTipoComprobante(@Header("Authorization") String authToken,@Path("id") int id);
}

