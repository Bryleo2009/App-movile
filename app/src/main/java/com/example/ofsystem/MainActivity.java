package com.example.ofsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ofsystem.Componet.Modal;
import com.example.ofsystem.Model.Producto;
import com.example.ofsystem.Model.Rol;
import com.example.ofsystem.Service.ProductoServiceImpl;
import com.example.ofsystem.Service.RolServiceImpl;

//tengo que implementar ambas librerias, para el click del boton y el click de la lista
public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    //creacion de variables globales que usaré
    ListView listProduct;
    Button btnNuevo;
    SwipeRefreshLayout swipeRefreshLayout; //recarga de pagina
    ProductoServiceImpl productService = new ProductoServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listProduct = findViewById(R.id.listProductos);
        btnNuevo = findViewById(R.id.btnNuevo);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        btnNuevo.setOnClickListener(this);
        listProduct.setOnItemClickListener(this);

        productService.listarProductos(null);
        productService.listarProductos(listProduct);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                productService.listarProductos(listProduct);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnNuevo) {
            productService.listarProductos(listProduct);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == listProduct) {

            // Obtener el objeto Producto de la posición seleccionada
            Producto seleccion = (Producto) parent.getItemAtPosition(position);

            // Imprimir la información del objeto en la consola
            System.out.println("Prodcuto seleccionado: " + seleccion.toString());

            // Crear una instancia del diálogo y pasarle los datos
            Modal dialog = Modal.newInstance(seleccion);

            // Mostrar el diálogo
            dialog.show(getSupportFragmentManager(), "my_dialog");

        }
    }
}