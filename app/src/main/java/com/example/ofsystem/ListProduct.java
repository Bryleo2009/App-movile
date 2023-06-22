package com.example.ofsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ofsystem.Api.ProductoApi;
import com.example.ofsystem.Model.Color;
import com.example.ofsystem.Model.Producto;
import com.example.ofsystem.Model.ProductoFilter;
import com.example.ofsystem.Service.ModalServiceImpl;
import com.example.ofsystem.Service.ProductoServiceImpl;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//tengo que implementar ambas librerias, para el click del boton y el click de la lista
public class ListProduct extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    //creacion de variables globales que usaré
    ListView listProduct;
    FloatingActionButton newProduct, edictProduct, eliminarProduct;
    SwipeRefreshLayout swipeRefreshLayout; //recarga de pagina
    ProductoServiceImpl productService = new ProductoServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        Toolbar toolbar = findViewById(R.id.toolbar);
        listProduct = findViewById(R.id.listProductos2);
        newProduct = (FloatingActionButton) findViewById(R.id.accion_agregar2);
        edictProduct = (FloatingActionButton) findViewById(R.id.accion_editar2);
        eliminarProduct = (FloatingActionButton) findViewById(R.id.accion_eliminar2);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        listProduct.setOnItemClickListener(this);
        newProduct.setOnClickListener(this);
        edictProduct.setOnClickListener(this);
        eliminarProduct.setOnClickListener(this);

        productService.listarProductos(listProduct);

        //activacion del toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //accion al refrescar la vista
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                productService.listarProductos(listProduct);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    //me regresa a la vista home, necesita implementacion de import android.view.MenuItem;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // Acción que deseas realizar al hacer clic en la flecha de navegación
            // Por ejemplo, puedes finalizar la actividad actual y regresar a la anterior
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    //accion click del boton aun no visible para registro
    @Override
    public void onClick(View v) {
        if(v == newProduct){
            Intent intent = new Intent(ListProduct.this, FormProductActivity.class);
            startActivity(intent);
        }
        if (v == edictProduct) {
            Context context = this;
            productService.editarProductos(this);
        }
        if (v == eliminarProduct) {
            Context context = this;
            productService.eliminarProdcutos(this);
        }
    }

    //accion click de la lista
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == listProduct) {

            // Obtener el objeto Producto de la posición seleccionada
            ProductoFilter seleccion = (ProductoFilter) parent.getItemAtPosition(position);

            // Imprimir la información del objeto en la consola
            System.out.println("Prodcuto seleccionado: " + seleccion.toString());

            // Crear una instancia del diálogo y pasarle los datos
            ModalServiceImpl dialog = ModalServiceImpl.newInstance(seleccion);

            // Mostrar el diálogo
            dialog.show(getSupportFragmentManager(), "my_dialog");

        }
    }

}