package com.example.ofsystem.Service;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ofsystem.Api.TallaApi;
import com.example.ofsystem.Config.Config;
import com.example.ofsystem.Model.Categoria;
import com.example.ofsystem.Model.Talla;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TallaServiceImpl implements AdapterView.OnItemSelectedListener {
    private TallaApi TallaApi;
    private Spinner spinner;
    private List<Talla> tallas = new ArrayList<>();

    public TallaServiceImpl() {
        // Crear una instancia de Retrofit con la URL base de la API RESTful
        //se usa la red 10.0.2.2 siempre para acceder al localhost
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de la interfaz MyApi
        TallaApi = retrofit.create(TallaApi.class);
    }

    public void listarTallas(Spinner spinner, List<Talla> obj) {
        // Consumir el endpoint de la API RESTful usando la interfaz MyApi
        System.out.println("Enviando solicitud HTTP...");
        this.spinner = spinner; //cojo el valor para usarlo posteriormente
        List<String> cateList = new ArrayList<>();
        Call<List<Talla>> call = TallaApi.getTallas();
        call.enqueue(new Callback<List<Talla>>() {
            @Override
            public void onResponse(Call<List<Talla>> call, Response<List<Talla>> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Consumo Exitoso");
                        List<Talla> Tallas = response.body();
                        tallas = response.body();

                        // Procesamiento de la respuesta
                        for (Talla Talla: Tallas){
                            cateList.add(Talla.getVistaItem());
                        }

                        //recorrido de un objeto para asignar a un adaptador ya existente
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, cateList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                        if (obj != null) {
                            for (int i = 0; i< tallas.size(); i++){
                                for (Talla talla: obj){
                                    if(talla.getIdTalla() == tallas.get(i).getIdTalla()){
                                        spinner.setSelection(i);
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
            public void onFailure(Call<List<Talla>> call, Throwable t) {
                System.out.println("Error al procesar la solicitud: " + t.getMessage());
                
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public int obtenerElementoSeleccionado() {
        int position = spinner.getSelectedItemPosition();
        String selectedValue = spinner.getItemAtPosition(position).toString();
        for (Talla talla: tallas){
            if(selectedValue.equals(talla.getVistaItem())){
                return talla.getIdTalla();
            }
        }
        return 0;
    }
}
