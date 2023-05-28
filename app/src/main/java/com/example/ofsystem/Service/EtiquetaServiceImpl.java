package com.example.ofsystem.Service;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.ofsystem.Api.EtiquetaApi;
import com.example.ofsystem.Config.Config;
import com.example.ofsystem.Model.Cliente;
import com.example.ofsystem.Model.Color;
import com.example.ofsystem.Model.Etiqueta;
import com.example.ofsystem.R;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EtiquetaServiceImpl {
    private EtiquetaApi EtiquetaApi;
    private List<Etiqueta> etiquetas;
    private LinearLayout layout;

    public EtiquetaServiceImpl() {
        // Crear una instancia de Retrofit con la URL base de la API RESTful
        //se usa la red 10.0.2.2 siempre para acceder al localhost
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de la interfaz MyApi
        EtiquetaApi = retrofit.create(EtiquetaApi.class);
    }

    public void listarEtiquetas(LinearLayout layout, List<Etiqueta> obj) {
        // Consumir el endpoint de la API RESTful usando la interfaz MyApi
        System.out.println("Enviando solicitud HTTP...");
        this.layout = layout;
        Call<List<Etiqueta>> call = EtiquetaApi.getEtiquetas();
        call.enqueue(new Callback<List<Etiqueta>>() {
            @Override
            public void onResponse(Call<List<Etiqueta>> call, Response<List<Etiqueta>> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Consumo Exitoso");
                        List<Etiqueta> Etiquetas = response.body();
                        etiquetas = response.body();

                        // Itera sobre el array de etiquetas y crea un checkbox por cada una
                        for (Etiqueta etiqueta : Etiquetas) {
                            CheckBox checkBox = new CheckBox(layout.getContext());
                            checkBox.setText(etiqueta.getVistaItem());
                            layout.addView(checkBox);

                            if (obj != null) {
                                for (Etiqueta objEtiqueta : obj) {
                                    if (objEtiqueta.getIdEtiqueta() == etiqueta.getIdEtiqueta()) {
                                        checkBox.setChecked(true);
                                        break; // Si encontramos una coincidencia, salimos del bucle interno
                                    }
                                }
                            }
                        }

                    } else {
                        System.out.println("Consumo NO Exitoso " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Etiqueta>> call, Throwable t) {
                System.out.println("Error al procesar la solicitud: " + t.getMessage());
                
            }
        });
    }


    //obtener check seleccionados
    public List<Integer> obtenerEtiquetasSeleccionadas() {
        List<String> etiquetasSeleccionadas = new ArrayList<>();
        List<Integer> codigos = new ArrayList<>();
        int childCount = layout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = layout.getChildAt(i);
            if (view instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()) {
                    etiquetasSeleccionadas.add(checkBox.getText().toString());
                }
            }
        }

        for (String nameEtiqueta: etiquetasSeleccionadas){
            for (Etiqueta etiqueta: etiquetas){
                if(nameEtiqueta.equals(etiqueta.getVistaItem())){
                    codigos.add(etiqueta.getIdEtiqueta());
                }
            }
        }

        return codigos;
    }
}
