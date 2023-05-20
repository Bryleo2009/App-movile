package com.example.ofsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.ofsystem.Model.ProductoFilter;
import com.example.ofsystem.Service.ClienteServiceImpl;
import com.example.ofsystem.Service.ModalServiceImpl;
import com.example.ofsystem.Service.ProductoServiceImpl;

public class ListClientActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    ListView listCliente;
    Button btnNuevo;
    SwipeRefreshLayout swipeRefreshLayout; //recarga de pagina
    ClienteServiceImpl clienteService = new ClienteServiceImpl();
    
    
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client);

        Toolbar toolbar = findViewById(R.id.toolbar);
        listCliente = findViewById(R.id.listClientes);
        btnNuevo = findViewById(R.id.btnNuevo);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        btnNuevo.setOnClickListener(this);
        listCliente.setOnItemClickListener(this);

        clienteService.listarClientes(null);
        clienteService.listarClientes(listCliente);

        //activacion del toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //accion al refrescar la vista
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clienteService.listarClientes(listCliente);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

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
            clienteService.listarClientes(listCliente);
        }

    }

    //accion click de la lista
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (parent == listCliente) {
//
//            // Obtener el objeto Producto de la posición seleccionada
//            ProductoFilter seleccion = (ProductoFilter) parent.getItemAtPosition(position);
//
//            // Imprimir la información del objeto en la consola
//            System.out.println("Prodcuto seleccionado: " + seleccion.toString());
//
//            // Crear una instancia del diálogo y pasarle los datos
//            ModalServiceImpl dialog = ModalServiceImpl.newInstance(seleccion);
//
//            // Mostrar el diálogo
//            dialog.show(getSupportFragmentManager(), "my_dialog");
//
//        }
    }
}