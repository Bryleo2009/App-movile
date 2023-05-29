package com.example.ofsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.getbase.floatingactionbutton.FloatingActionButton;

public class ListClientActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    ListView listCliente;
    FloatingActionButton newCliente, edictCliente, eliminarCliente;
    SwipeRefreshLayout swipeRefreshLayout; //recarga de pagina
    ClienteServiceImpl clienteService = new ClienteServiceImpl();
    
    
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client);

        Toolbar toolbar = findViewById(R.id.toolbar);
        listCliente = findViewById(R.id.listClientes);
        newCliente = (FloatingActionButton) findViewById(R.id.accion_agregarCliente);
        edictCliente = (FloatingActionButton) findViewById(R.id.accion_editarCliente);
        eliminarCliente = (FloatingActionButton) findViewById(R.id.accion_eliminarCliente);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        listCliente.setOnItemClickListener(this);
        newCliente.setOnClickListener(this);
        edictCliente.setOnClickListener(this);
        eliminarCliente.setOnClickListener(this);

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
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //accion click del boton
    @Override
    public void onClick(View v) {
        if(v == newCliente){
            Intent intent = new Intent(ListClientActivity.this, FormClienteActivity.class);
            startActivity(intent);
        }
        if (v == edictCliente) {
            Context context = this;
            clienteService.editarClientes(this);
        }
        if (v == eliminarCliente) {
            Context context = this;
            clienteService.eliminarClientes(this);
        }
    }

    //accion click de la lista
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}