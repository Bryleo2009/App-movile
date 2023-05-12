package com.example.ofsystem;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ofsystem.Model.Producto;
import com.example.ofsystem.Service.ModalServiceImpl;
import com.example.ofsystem.Service.ProductoServiceImpl;

//tengo que implementar ambas librerias, para el click del boton y el click de la lista
public class ListProduct extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    //creacion de variables globales que usaré
    ListView listProduct;
    Button btnNuevo;
    SwipeRefreshLayout swipeRefreshLayout; //recarga de pagina
    ProductoServiceImpl productService = new ProductoServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);



        Toolbar toolbar = findViewById(R.id.toolbar);
        listProduct = findViewById(R.id.listProductos);
        btnNuevo = findViewById(R.id.btnNuevo);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        btnNuevo.setOnClickListener(this);
        listProduct.setOnItemClickListener(this);

        productService.listarProductos(null);
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //accion click del boton
    @Override
    public void onClick(View v) {
        if (v == btnNuevo) {
            productService.listarProductos(listProduct);
        }

    }

    //accion click de la lista
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == listProduct) {

            // Obtener el objeto Producto de la posición seleccionada
            Producto seleccion = (Producto) parent.getItemAtPosition(position);

            // Imprimir la información del objeto en la consola
            System.out.println("Prodcuto seleccionado: " + seleccion.toString());

            // Crear una instancia del diálogo y pasarle los datos
            ModalServiceImpl dialog = ModalServiceImpl.newInstance(seleccion);

            // Mostrar el diálogo
            dialog.show(getSupportFragmentManager(), "my_dialog");

        }
    }

}