package com.example.ofsystem.Service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ofsystem.Api.ClienteApi;
import com.example.ofsystem.Config.Config;
import com.example.ofsystem.Model.Cliente;
import com.example.ofsystem.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteServiceImpl {
    private ClienteApi ClienteApi;

    public ClienteServiceImpl() {
        // Crear una instancia de Retrofit con la URL base de la API RESTful
        //se usa la red 10.0.2.2 siempre para acceder al localhost
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de la interfaz MyApi
        ClienteApi = retrofit.create(ClienteApi.class);
    }

    public void listarClientes(ListView listView) {
        // Consumir el endpoint de la API RESTful usando la interfaz MyApi
        System.out.println("Enviando solicitud HTTP...");
        Call<List<Cliente>> call = ClienteApi.getClientes();
        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Consumo Exitoso");
                        List<Cliente> Clientes = response.body();

                        // Crear un ArrayAdapter con los Clientes obtenidos de la API RESTful
                        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(
                                listView.getContext(),
                                R.layout.cmp_listclient,
                                Clientes
                        ) {
                            //recorrido de un objeto para asignar a un nuevo adaptador
                            public View getView(int position, View convertView, ViewGroup parent) {
                                View itemView = convertView;

                                try {
                                    if (itemView == null) {
                                        LayoutInflater inflater = LayoutInflater.from(getContext());
                                        itemView = inflater.inflate(R.layout.cmp_listclient, parent, false);
                                    }

                                    // Obtener el Cliente actual
                                    Cliente Cliente = getItem(position);
                                    System.out.println("Listando ...:" + Cliente);

                                    // Actualizar los elementos de la vista con los datos del Cliente
                                    TextView tvDni = (TextView) itemView.findViewById(R.id.txtDni);
                                    tvDni.setText(Cliente.getDniCliente());

                                    TextView tvCorreo = (TextView) itemView.findViewById(R.id.txtCorreoCliente);
                                    tvCorreo.setText(String.valueOf(Cliente.getCorreoCliente()));

                                    TextView tvNombre = (TextView) itemView.findViewById(R.id.txtNombreCliente);
                                    tvNombre.setText(Cliente.getNombreCliente() + " " + Cliente.getApellidoCliente());

                                    TextView tvCelular = (TextView) itemView.findViewById(R.id.txtCelularCliente);
                                    tvCelular.setText(Cliente.getCelularCliente());

                                } catch (Exception e) {
                                    System.out.println("Error de consumo interno: " + e.getMessage());
                                    // Aquí puedes hacer algo en caso de error, como mostrar un mensaje al usuario o lanzar una excepción
                                }

                                return itemView;
                            }

                        };

                        // Asignar el adapter a la ListView
                        listView.setAdapter(adapter);

                        // Mostrar la alerta flotante
                        //Snackbar.make(listView, "Lista actualizada", Snackbar.LENGTH_SHORT).show();
                        Toast.makeText(listView.getContext(), "Lista actualizada", Toast.LENGTH_SHORT).show();
                    } else {
                        System.out.println("Consumo NO Exitoso " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                System.out.println("Error al procesar la solicitud: " + t.getMessage());
                // Mostrar la alerta flotante
                Snackbar.make(listView, "Error al procesar la solicitud: " + t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

    }
}
