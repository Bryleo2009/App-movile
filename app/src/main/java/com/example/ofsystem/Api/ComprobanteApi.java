package com.example.ofsystem.Api;

import com.example.ofsystem.Model.ComprobanteFilter;
import com.example.ofsystem.Model.ProductoFilter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ComprobanteApi {
    @GET("/Comprobantes")
    Call<List<ProductoFilter>> getProductos();

    @POST("/Comprobantes")
    Call<Void> createComprobante(@Header("Authorization") String authToken, @Body ComprobanteFilter unProducto);

}
