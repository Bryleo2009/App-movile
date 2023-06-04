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

import com.example.ofsystem.Service.MedioPagoServiceImpl;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class ListMedioPagoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    ListView listMedioPago;
    FloatingActionButton newMedioPago, edictMedioPago, eliminarMedioPago;
    SwipeRefreshLayout swipeRefreshLayout; //recarga de pagina
    MedioPagoServiceImpl MedioPagoService = new MedioPagoServiceImpl();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_medio_pago);

        Toolbar toolbar = findViewById(R.id.toolbar);
        listMedioPago = findViewById(R.id.listMedioPago);
        newMedioPago = (FloatingActionButton) findViewById(R.id.accion_agregarMedioPago);
        edictMedioPago = (FloatingActionButton) findViewById(R.id.accion_editarMedioPago);
        eliminarMedioPago = (FloatingActionButton) findViewById(R.id.accion_eliminarMedioPago);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        listMedioPago.setOnItemClickListener(this);
        newMedioPago.setOnClickListener(this);
        edictMedioPago.setOnClickListener(this);
        eliminarMedioPago.setOnClickListener(this);

        MedioPagoService.listarMedioPagos(listMedioPago);

        //activacion del toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //accion al refrescar la vista
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MedioPagoService.listarMedioPagos(listMedioPago);
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
        if(v == newMedioPago){
            Intent intent = new Intent(this, FormMedioPagoActivity.class);
            startActivity(intent);
        }
        if (v == edictMedioPago) {
            MedioPagoService.editarMedioPagos(this);
        }
        if (v == eliminarMedioPago) {
            MedioPagoService.eliminarMedioPagos(this);
        }
    }

    //accion click de la lista
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}