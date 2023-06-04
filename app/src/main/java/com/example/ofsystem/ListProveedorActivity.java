package com.example.ofsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ofsystem.Service.ProveedorServiceImpl;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class ListProveedorActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    ListView listProveedor;
    FloatingActionButton newProveedor, edictProveedor, eliminarProveedor;
    SwipeRefreshLayout swipeRefreshLayout; //recarga de pagina
    ProveedorServiceImpl ProveedorService = new ProveedorServiceImpl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_proveedor);

        Toolbar toolbar = findViewById(R.id.toolbar);
        listProveedor = findViewById(R.id.listProveedor);
        newProveedor = (FloatingActionButton) findViewById(R.id.accion_agregarProveedor);
        edictProveedor = (FloatingActionButton) findViewById(R.id.accion_editarProveedor);
        eliminarProveedor = (FloatingActionButton) findViewById(R.id.accion_eliminarProveedor);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        listProveedor.setOnItemClickListener(this);
        newProveedor.setOnClickListener(this);
        edictProveedor.setOnClickListener(this);
        eliminarProveedor.setOnClickListener(this);

        ProveedorService.listarProveedors(listProveedor);

        //activacion del toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //accion al refrescar la vista
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ProveedorService.listarProveedors(listProveedor);
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
        if(v == newProveedor){
            Intent intent = new Intent(this, FormProveedorActivity.class);
            startActivity(intent);
        }
        if (v == edictProveedor) {
            Context context = this;
            ProveedorService.editarProveedors(this);
        }
        if (v == eliminarProveedor) {
            Context context = this;
            ProveedorService.eliminarProveedors(this);
        }
    }

    //accion click de la lista
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}