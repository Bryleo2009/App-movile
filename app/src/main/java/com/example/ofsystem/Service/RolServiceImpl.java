package com.example.ofsystem.Service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ofsystem.Api.RolApi;
import com.example.ofsystem.Config.Config;
import com.example.ofsystem.Model.Rol;
import com.example.ofsystem.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RolServiceImpl {
    private RolApi rolApi;

    public RolServiceImpl() {
        // Crear una instancia de Retrofit con la URL base de la API RESTful
        //se usa la red 10.0.2.2 siempre para acceder al localhost
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de la interfaz MyApi
        rolApi = retrofit.create(RolApi.class);
    }

    public void listarRols(ListView listView) {
        // Consumir el endpoint de la API RESTful usando la interfaz MyApi
        System.out.println("Enviando solicitud HTTP...");
        Call<List<Rol>> call = rolApi.getRols();
        call.enqueue(new Callback<List<Rol>>() {
            @Override
            public void onResponse(Call<List<Rol>> call, Response<List<Rol>> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Consumo Exitoso");
                        List<Rol> Rols = response.body();

                        // Crear un ArrayAdapter con los Rols obtenidos de la API RESTful
                        ArrayAdapter<Rol> adapter = new ArrayAdapter<Rol>(
                                listView.getContext(),
                                R.layout.cmp_listproduct,
                                Rols
                        ) {
                            public View getView(int position, View convertView, ViewGroup parent) {
                                View itemView = convertView;

                                try {
                                    if (itemView == null) {
                                        LayoutInflater inflater = LayoutInflater.from(getContext());
                                        itemView = inflater.inflate(R.layout.cmp_listproduct, parent, false);
                                    }

                                    // Obtener el Rol actual
                                    Rol rol = getItem(position);

                                    // Actualizar los elementos de la vista con los datos del Rol
                                    TextView tvNombre = (TextView) itemView.findViewById(R.id.txtNombre);
                                    tvNombre.setText(rol.getVistaItem());

                                    TextView tvNumero = (TextView) itemView.findViewById(R.id.txtPrecio);
                                    tvNumero.setText(rol.getAbreviItem());

                                    TextView tvDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
                                    tvDescripcion.setText(rol.getNombreItem());

                                    TextView tvId = (TextView) itemView.findViewById(R.id.txtId);
                                    tvId.setText(String.valueOf(rol.getIdRol()));
                                } catch (Exception e) {
                                    System.out.println("Error de consumo interno: " + e.getMessage());
                                    // Aquí puedes hacer algo en caso de error, como mostrar un mensaje al usuario o lanzar una excepción
                                }

                                return itemView;
                            }

                        };

                        // Asignar el adapter a la ListView
                        listView.setAdapter(adapter);
                    } else {
                        System.out.println("Consumo NO Exitoso " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Rol>> call, Throwable t) {
                System.out.println("Error al procesar la solicitud: " + t.getMessage());
            }
        });

    }
}
