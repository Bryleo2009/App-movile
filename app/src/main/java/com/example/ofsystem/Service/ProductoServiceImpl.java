package com.example.ofsystem.Service;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.ofsystem.Api.ProductoApi;
import com.example.ofsystem.Config.Config;
import com.example.ofsystem.FormProductActivity;
import com.example.ofsystem.ListClientActivity;
import com.example.ofsystem.ListProduct;
import com.example.ofsystem.MenuActivity;
import com.example.ofsystem.Model.Color;
import com.example.ofsystem.Model.Producto;
import com.example.ofsystem.Model.ProductoFilter;
import com.example.ofsystem.Model.RegistroProductFilter;
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

public class ProductoServiceImpl {
    private ProductoApi ProductoApi;
    private Spinner spinner;
    private List<ProductoFilter> objeto = new ArrayList<>();

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
        //realiza una llamada al metodo getProductos
        Call<List<ProductoFilter>> call = ProductoApi.getProductos();
        //se agrega un Callback es responsable de manejar la respuesta de la llamada cuando se recibe una respuesta del servidor.
        call.enqueue(new Callback<List<ProductoFilter>>() {
            @Override
            public void onResponse(Call<List<ProductoFilter>> call, Response<List<ProductoFilter>> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Consumo Exitoso");
                        List<ProductoFilter> productoFilters = response.body();
                        objeto = response.body();
                        // Crear un ArrayAdapter con los Productos obtenidos de la API RESTful
                        ArrayAdapter<ProductoFilter> adapter = new ArrayAdapter<ProductoFilter>(
                                listView.getContext(),
                                R.layout.cmp_listproduct,
                                productoFilters
                        ) {
                            public View getView(int position, View convertView, ViewGroup parent) {
                                View itemView = convertView;

                                try {
                                    if (itemView == null) {
                                        LayoutInflater inflater = LayoutInflater.from(getContext());
                                        itemView = inflater.inflate(R.layout.cmp_listproduct, parent, false);
                                    }

                                    // Obtener el Producto actual
                                    ProductoFilter productoFilter = getItem(position);
                                    System.out.println("Listando ...:" + productoFilter);

                                    // Actualizar los elementos de la vista con los datos del Producto
                                    TextView tvNombre = (TextView) itemView.findViewById(R.id.txtNombreProducto);
                                    tvNombre.setText(productoFilter.getProducto().getNombreProduct());

                                    TextView tvNumero = (TextView) itemView.findViewById(R.id.txtPrecio);
                                    tvNumero.setText(String.valueOf(productoFilter.getProducto().getPrecioUni()));

                                    TextView tvDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
                                    tvDescripcion.setText(productoFilter.getProducto().getDescripcionProduct());

                                    TextView tvTipo = (TextView) itemView.findViewById(R.id.txtTipo);
                                    tvTipo.setText(productoFilter.getProducto().getIdTipoProduc().getIdentItem());

                                    TextView tvId = (TextView) itemView.findViewById(R.id.txtId);
                                    tvId.setText(String.valueOf(productoFilter.getProducto().getIdProduct()));

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
            public void onFailure(Call<List<ProductoFilter>> call, Throwable t) {
                System.out.println("Error al procesar la solicitud: " + t.getMessage());
                // Mostrar la alerta flotante
                Snackbar.make(listView, "Error al procesar la solicitud: " + t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    public void crearProductos(Context context, RegistroProductFilter producto, View v, boolean edit) {
        Call<Void> call;

        if(!edit){
            call = ProductoApi.createProduct(producto);
        } else {
            call = ProductoApi.modificareProduct(producto);
        }

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Creacion de Producto Exitoso");
                        MaterialAlertDialogBuilder materialDialogBuilder = new MaterialAlertDialogBuilder(v.getContext())
                                .setTitle("Producto Registrado")
                                .setMessage("Este producto ha sido creado con éxito")
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Acciones a realizar al hacer clic en Aceptar
                                        Intent intent = new Intent(context, ListProduct.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Agrega esta línea si estás llamando desde una clase sin actividad
                                        context.startActivity(intent);
                                    }
                                });

                        materialDialogBuilder.show();
                    } else {
                        // Hubo un error al crear el producto
                        System.out.println("Consumo NO Exitoso " + response.message());
                        MaterialAlertDialogBuilder materialDialogBuilder = new MaterialAlertDialogBuilder(v.getContext())
                                .setTitle("Producto NO Registrado")
                                .setMessage("Este producto no ha sido creado, posiblemente ya se encuentra uno similar en la base de datos")
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

    public void editarProductos(Context context) {
        // Consumir el endpoint de la API RESTful usando la interfaz MyApi
        System.out.println("Enviando solicitud HTTP...");
        Call<List<ProductoFilter>> call = ProductoApi.getProductos();
        call.enqueue(new Callback<List<ProductoFilter>>() {
            @Override
            public void onResponse(Call<List<ProductoFilter>> call, Response<List<ProductoFilter>> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Consumo Exitoso");
                        List<ProductoFilter> productoFilters = response.body();
                        // Procesamiento de la respuesta


                        List<String> list = new ArrayList<>();

                        for (ProductoFilter productoFilter: productoFilters){
                            list.add(productoFilter.getProducto().getIUP() + " | " + productoFilter.getProducto().getNombreProduct());
                        }

                        // Crear un ArrayAdapter con los nombres de los productos
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Crear el diálogo
                        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(context);
                        dialogBuilder.setTitle("Seleccionar producto para editar");

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


                                for (ProductoFilter producto: objeto){
                                    if(selectedProduct.equals(producto.getProducto().getIUP() + " | " + producto.getProducto().getNombreProduct())){
                                        // Navegar a otra vista (reemplaza con la actividad de destino)
                                        Intent intent = new Intent(context, FormProductActivity.class);
                                        System.out.println("seleccionado: " + producto);
                                        intent.putExtra("Item",producto);
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
            public void onFailure(Call<List<ProductoFilter>> call, Throwable t) {
                System.out.println("Error al procesar la solicitud: " + t.getMessage());

            }
        });
    }

    public void eliminarProdcutos(Context context){
        System.out.println("Enviando solicitud HTTP...");
        Call<List<ProductoFilter>> call = ProductoApi.getProductos();
        call.enqueue(new Callback<List<ProductoFilter>>() {
            @Override
            public void onResponse(Call<List<ProductoFilter>> call, Response<List<ProductoFilter>> response) {
                try {
                    if (response.isSuccessful()) {
                        System.out.println("Consumo Exitoso");
                        List<ProductoFilter> productoFilters = response.body();
                        // Procesamiento de la respuesta


                        List<String> list = new ArrayList<>();

                        for (ProductoFilter productoFilter: productoFilters){
                            list.add(productoFilter.getProducto().getIUP() + " | " + productoFilter.getProducto().getNombreProduct());
                        }

                        // Crear un ArrayAdapter con los nombres de los productos
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Crear el diálogo
                        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(context);
                        dialogBuilder.setTitle("Seleccionar producto para eliminar");

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


                                for (ProductoFilter producto: objeto){
                                    if(selectedProduct.equals(producto.getProducto().getIUP() + " | " + producto.getProducto().getNombreProduct())){
                                        // Navegar a otra vista (reemplaza con la actividad de destino)
                                        Call<Void> call = ProductoApi.eliminarProducto(producto.getProducto().getIdProduct());
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                if (response.isSuccessful()) {
                                                    MaterialAlertDialogBuilder materialDialogBuilder = new MaterialAlertDialogBuilder(context)
                                                            .setTitle("Producto Eliminado")
                                                            .setMessage("Este producto ha sido eliminado con éxito")
                                                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    // Acciones a realizar al hacer clic en Aceptar
                                                                    Intent intent = new Intent(context, ListProduct.class);
                                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Agrega esta línea si estás llamando desde una clase sin actividad
                                                                    context.startActivity(intent);
                                                                }
                                                            });

                                                    materialDialogBuilder.show();
                                                    System.out.println("Eliminacion de Producto Exitoso");
                                                } else {
                                                    MaterialAlertDialogBuilder materialDialogBuilder = new MaterialAlertDialogBuilder(context)
                                                            .setTitle("Producto NO Eliminado")
                                                            .setMessage("Este producto no ha sido eliminado")
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
            public void onFailure(Call<List<ProductoFilter>> call, Throwable t) {
                System.out.println("Error al procesar la solicitud: " + t.getMessage());

            }
        });
    }

    public int obtenerElementoSeleccionado() {
        int position = spinner.getSelectedItemPosition();
        String selectedValue = spinner.getItemAtPosition(position).toString();
        for (ProductoFilter objeto: objeto){
            if(selectedValue.equals(objeto.getProducto().getIUP() + " | " + objeto.getProducto().getNombreProduct())){
                return objeto.getProducto().getIdProduct();
            }
        }
        return 0;
    }
}
