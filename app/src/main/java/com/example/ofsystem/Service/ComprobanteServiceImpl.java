package com.example.ofsystem.Service;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ofsystem.Api.ComprobanteApi;
import com.example.ofsystem.Config.Config;
import com.example.ofsystem.Model.ComprobanteFilter;
import com.example.ofsystem.R;
import com.example.ofsystem.WebViewActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComprobanteServiceImpl {
    private com.example.ofsystem.Api.ComprobanteApi ComprobanteApi;
    private List<ComprobanteFilter> objeto = new ArrayList<>();
    private Context context;

    public ComprobanteServiceImpl() {
        // Crear una instancia de Retrofit con la URL base de la API RESTful
        //se usa la red 10.0.2.2 siempre para acceder al localhost
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de la interfaz MyApi
        ComprobanteApi = retrofit.create(ComprobanteApi.class);
    }

    public void crearComprobantes(Context context, ComprobanteFilter Comprobante, View v, boolean edit) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString("authToken", "");


        Call<Void> call;
            call = ComprobanteApi.createComprobante("Bearer " + authToken,Comprobante);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Creacion de Comprobante Exitoso");
                        MaterialAlertDialogBuilder materialDialogBuilder = new MaterialAlertDialogBuilder(v.getContext())
                                .setTitle("Comprobante Registrado")
                                .setMessage("Este Comprobante ha sido creado con éxito")
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Acciones a realizar al hacer clic en Aceptar
                                        Intent intent = new Intent(context, WebViewActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Agrega esta línea si estás llamando desde una clase sin actividad
                                        context.startActivity(intent);
                                    }
                                });

                        materialDialogBuilder.show();
                    } else {
                        // Hubo un error al crear el producto
                        System.out.println("Consumo NO Exitoso " + response.message());
                        MaterialAlertDialogBuilder materialDialogBuilder = new MaterialAlertDialogBuilder(v.getContext())
                                .setTitle("Comprobante NO Registrado")
                                .setMessage("Este Comprobante no ha sido creado, posiblemente ya se encuentra uno similar en la base de datos")
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Acciones a realizar al hacer clic en Aceptar

                                    }
                                });

                        materialDialogBuilder.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Hubo un error de conexión u otro tipo de error
                System.out.println("Error al procesar la solicitud: " + t.getMessage());
                // Mostrar la alerta flotante
                //Snackbar.make(listView, "Error al procesar la solicitud: " + t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

}
