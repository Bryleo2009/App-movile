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

import com.example.ofsystem.Api.TipoComprobanteApi;
import com.example.ofsystem.Config.Config;
import com.example.ofsystem.FormTipoComprobanteActivity;
import com.example.ofsystem.ListClientActivity;
import com.example.ofsystem.ListTipoComprobanteActivity;
import com.example.ofsystem.Model.TipoComprobante;
import com.example.ofsystem.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TipoComprobanteServiceImpl {
    private TipoComprobanteApi TipoComprobanteApi;
    private List<TipoComprobante> objeto = new ArrayList<>();

    public TipoComprobanteServiceImpl() {
        // Crear una instancia de Retrofit con la URL base de la API RESTful
        //se usa la red 10.0.2.2 siempre para acceder al localhost
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de la interfaz MyApi
        TipoComprobanteApi = retrofit.create(TipoComprobanteApi.class);
    }

    public void listarTipoComprobantes(ListView listView) {
        // Consumir el endpoint de la API RESTful usando la interfaz MyApi
        System.out.println("Enviando solicitud HTTP...");
        Call<List<TipoComprobante>> call = TipoComprobanteApi.getTipoComprobantes();
        call.enqueue(new Callback<List<TipoComprobante>>() {
            @Override
            public void onResponse(Call<List<TipoComprobante>> call, Response<List<TipoComprobante>> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Consumo Exitoso");
                        List<TipoComprobante> TipoComprobantes = response.body();
                        objeto = response.body();
                        // Crear un ArrayAdapter con los TipoComprobantes obtenidos de la API RESTful
                        ArrayAdapter<TipoComprobante> adapter = new ArrayAdapter<TipoComprobante>(
                                listView.getContext(),
                                R.layout.cmp_listtipocompro,
                                TipoComprobantes
                        ) {
                            //recorrido de un objeto para asignar a un nuevo adaptador
                            public View getView(int position, View convertView, ViewGroup parent) {
                                View itemView = convertView;

                                try {
                                    if (itemView == null) {
                                        LayoutInflater inflater = LayoutInflater.from(getContext());
                                        itemView = inflater.inflate(R.layout.cmp_listtipocompro, parent, false);
                                    }

                                    // Obtener el TipoComprobante actual
                                    TipoComprobante TipoComprobante = getItem(position);
                                    System.out.println("Listando ...:" + TipoComprobante);

                                    // Actualizar los elementos de la vista con los datos del TipoComprobante
                                    TextView txtnombre = (TextView) itemView.findViewById(R.id.txtNombreComprobante);
                                    TextView txtdescripcion = (TextView) itemView.findViewById(R.id.txtDescripcionComprobante);
                                    txtnombre.setText(TipoComprobante.getNombre());
                                    txtdescripcion.setText(TipoComprobante.getDescripcion());

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
            public void onFailure(Call<List<TipoComprobante>> call, Throwable t) {
                System.out.println("Error al procesar la solicitud: " + t.getMessage());
                // Mostrar la alerta flotante
                Snackbar.make(listView, "Error al procesar la solicitud: " + t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    public void crearTipoComprobantes(Context context, TipoComprobante TipoComprobante, View v, boolean edit) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString("authToken", "");
        Call<Void> call;

        if(!edit){
            call = TipoComprobanteApi.createTipoComprobante("Bearer " + authToken,TipoComprobante);
        } else {
            call = TipoComprobanteApi.modificareTipoComprobante("Bearer " + authToken,TipoComprobante);
        }

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Creacion de TipoComprobante Exitoso");
                        MaterialAlertDialogBuilder materialDialogBuilder = new MaterialAlertDialogBuilder(v.getContext())
                                .setTitle("TipoComprobante Registrado")
                                .setMessage("Este TipoComprobante ha sido creado con éxito")
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Acciones a realizar al hacer clic en Aceptar
                                        Intent intent = new Intent(context, ListTipoComprobanteActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Agrega esta línea si estás llamando desde una clase sin actividad
                                        context.startActivity(intent);
                                    }
                                });

                        materialDialogBuilder.show();
                    } else {
                        // Hubo un error al crear el producto
                        System.out.println("Consumo NO Exitoso " + response.message());
                        MaterialAlertDialogBuilder materialDialogBuilder = new MaterialAlertDialogBuilder(v.getContext())
                                .setTitle("TipoComprobante NO Registrado")
                                .setMessage("Este TipoComprobante no ha sido creado, posiblemente ya se encuentra uno similar en la base de datos")
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

    public void editarTipoComprobantes(Context context) {
        // Consumir el endpoint de la API RESTful usando la interfaz MyApi
        System.out.println("Enviando solicitud HTTP...");
        Call<List<TipoComprobante>> call = TipoComprobanteApi.getTipoComprobantes();
        call.enqueue(new Callback<List<TipoComprobante>>() {
            @Override
            public void onResponse(Call<List<TipoComprobante>> call, Response<List<TipoComprobante>> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Consumo Exitoso");
                        List<TipoComprobante> TipoComprobantes = response.body();
                        // Procesamiento de la respuesta


                        List<String> list = new ArrayList<>();

                        for (TipoComprobante TipoComprobante: TipoComprobantes){
                            list.add(TipoComprobante.getNombre());
                        }

                        // Crear un ArrayAdapter con los nombres de los productos
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Crear el diálogo
                        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(context);
                        dialogBuilder.setTitle("Seleccionar TipoComprobante para editar");

                        // Crear el Spinner y configurarlo con el ArrayAdapter
                        final Spinner spinner = new Spinner(context);
                        spinner.setAdapter(adapter);
                        dialogBuilder.setView(spinner);

                        // Configurar el botón "Next"
                        dialogBuilder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Acciones a realizar al hacer clic en "Next"
                                String selectedProduct = (String) spinner.getSelectedItem();
                                // Realizar alguna acción con el producto seleccionado


                                for (TipoComprobante TipoComprobante: objeto){
                                    if(selectedProduct.equals(TipoComprobante.getNombre())){
                                        // Navegar a otra vista (reemplaza con la actividad de destino)
                                        Intent intent = new Intent(context, FormTipoComprobanteActivity.class);
                                        System.out.println("seleccionado: " + TipoComprobante);
                                        intent.putExtra("Item",TipoComprobante);
                                        context.startActivity(intent);
                                    }
                                }
                            }
                        });

                        // Configurar el botón "Cancel"
                        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Acciones a realizar al hacer clic en "Cancel"
                                // Por ejemplo, cerrar el diálogo sin hacer nada
                                dialog.dismiss();
                            }
                        });

                        // Mostrar el diálogo
                        dialogBuilder.show();
                    } else {
                        System.out.println("Consumo NO Exitoso " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<TipoComprobante>> call, Throwable t) {
                System.out.println("Error al procesar la solicitud: " + t.getMessage());

            }
        });
    }

    public void eliminarTipoComprobantes(Context context){
        System.out.println("Enviando solicitud HTTP...");
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString("authToken", "");
        Call<List<TipoComprobante>> call = TipoComprobanteApi.getTipoComprobantes();
        call.enqueue(new Callback<List<TipoComprobante>>() {
            @Override
            public void onResponse(Call<List<TipoComprobante>> call, Response<List<TipoComprobante>> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Consumo Exitoso");
                        List<TipoComprobante> TipoComprobantes = response.body();
                        // Procesamiento de la respuesta


                        List<String> list = new ArrayList<>();

                        for (TipoComprobante TipoComprobante: TipoComprobantes){
                            list.add(TipoComprobante.getNombre());
                        }

                        // Crear un ArrayAdapter con los nombres de los productos
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Crear el diálogo
                        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(context);
                        dialogBuilder.setTitle("Seleccionar TipoComprobante para eliminar");

                        // Crear el Spinner y configurarlo con el ArrayAdapter
                        final Spinner spinner = new Spinner(context);
                        spinner.setAdapter(adapter);
                        dialogBuilder.setView(spinner);

                        // Configurar el botón "Next"
                        dialogBuilder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Acciones a realizar al hacer clic en "Next"
                                String selectedProduct = (String) spinner.getSelectedItem();
                                // Realizar alguna acción con el producto seleccionado


                                for (TipoComprobante TipoComprobante: objeto){
                                    if(selectedProduct.equals(TipoComprobante.getNombre())){
                                        // Navegar a otra vista (reemplaza con la actividad de destino)
                                        Call<Void> call = TipoComprobanteApi.eliminarTipoComprobante("Bearer " + authToken,TipoComprobante.getIdTipoComp());
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                if (response.isSuccessful()) {
                                                    MaterialAlertDialogBuilder materialDialogBuilder = new MaterialAlertDialogBuilder(context)
                                                            .setTitle("TipoComprobante Eliminado")
                                                            .setMessage("Este TipoComprobante ha sido eliminado con éxito")
                                                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    // Acciones a realizar al hacer clic en Aceptar
                                                                    Intent intent = new Intent(context, ListTipoComprobanteActivity.class);
                                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Agrega esta línea si estás llamando desde una clase sin actividad
                                                                    context.startActivity(intent);
                                                                }
                                                            });

                                                    materialDialogBuilder.show();
                                                    System.out.println("Eliminacion de TipoComprobante Exitoso");
                                                } else {
                                                    MaterialAlertDialogBuilder materialDialogBuilder = new MaterialAlertDialogBuilder(context)
                                                            .setTitle("TipoComprobante NO Eliminado")
                                                            .setMessage("Este TipoComprobante no ha sido eliminado")
                                                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    // Acciones a realizar al hacer clic en Aceptar

                                                                }
                                                            });

                                                    materialDialogBuilder.show();
                                                    System.out.println("Eliminacion de Producto no Exitoso");
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                // Error de red u otro tipo de error
                                                // Maneja el error de acuerdo a tus necesidades
                                                System.out.println("Consumo NO Exitoso " + response.message());
                                            }
                                        });


                                    }
                                }
                            }
                        });

                        // Configurar el botón "Cancel"
                        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Acciones a realizar al hacer clic en "Cancel"
                                // Por ejemplo, cerrar el diálogo sin hacer nada
                                dialog.dismiss();
                            }
                        });

                        // Mostrar el diálogo
                        dialogBuilder.show();
                    } else {
                        System.out.println("Consumo NO Exitoso " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<TipoComprobante>> call, Throwable t) {
                System.out.println("Error al procesar la solicitud: " + t.getMessage());

            }
        });
    }


}
