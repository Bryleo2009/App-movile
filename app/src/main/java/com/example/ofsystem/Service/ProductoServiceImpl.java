package com.example.ofsystem.Service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ofsystem.Api.ProductoApi;
import com.example.ofsystem.Config.Config;
import com.example.ofsystem.Model.Producto;
import com.example.ofsystem.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoServiceImpl {
    private ProductoApi ProductoApi;

    public ProductoServiceImpl() {
        // Crear una instancia de Retrofit con la URL base de la API RESTful
        //se usa la red 10.0.2.2 siempre para acceder al localhost
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de la interfaz MyApi
        ProductoApi = retrofit.create(ProductoApi.class);
    }

    public void listarProductos(ListView listView) {
        // Consumir el endpoint de la API RESTful usando la interfaz MyApi
        System.out.println("Enviando solicitud HTTP...");
        Call<List<Producto>> call = ProductoApi.getProductos();
        System.out.println(ProductoApi.getProductos());
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Consumo Exitoso");
                        List<Producto> Productos = response.body();

                        // Crear un ArrayAdapter con los Productos obtenidos de la API RESTful
                        ArrayAdapter<Producto> adapter = new ArrayAdapter<Producto>(
                                listView.getContext(),
                                R.layout.cmp_listproduct,
                                Productos
                        ) {
                            public View getView(int position, View convertView, ViewGroup parent) {
                                View itemView = convertView;

                                try {
                                    if (itemView == null) {
                                        LayoutInflater inflater = LayoutInflater.from(getContext());
                                        itemView = inflater.inflate(R.layout.cmp_listproduct, parent, false);
                                    }

                                    // Obtener el Producto actual
                                    Producto Producto = getItem(position);

                                    // Actualizar los elementos de la vista con los datos del Producto
                                    TextView tvNombre = (TextView) itemView.findViewById(R.id.txtNombre);
                                    tvNombre.setText(Producto.getNombreProduct());

                                    TextView tvNumero = (TextView) itemView.findViewById(R.id.txtPrecio);
                                    tvNumero.setText(String.valueOf(Producto.getPrecioUni()));

                                    TextView tvDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
                                    tvDescripcion.setText(Producto.getDescripcionProduct());

                                    TextView tvTipo = (TextView) itemView.findViewById(R.id.txtTipo);
                                    tvTipo.setText(Producto.getIdTipoProduc().getIdentItem());

                                    TextView tvId = (TextView) itemView.findViewById(R.id.txtId);
                                    tvId.setText(String.valueOf(Producto.getIdProduct()));

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
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                System.out.println("Error al procesar la solicitud: " + t.getMessage());
                // Mostrar la alerta flotante
                Snackbar.make(listView, "Error al procesar la solicitud: " + t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

    }
}
