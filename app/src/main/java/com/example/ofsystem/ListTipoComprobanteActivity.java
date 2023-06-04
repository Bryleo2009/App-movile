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

import com.example.ofsystem.Service.TipoComprobanteServiceImpl;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class ListTipoComprobanteActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    ListView listTipoComprobante;
    FloatingActionButton newTipoComprobante, edictTipoComprobante, eliminarTipoComprobante;
    SwipeRefreshLayout swipeRefreshLayout; //recarga de pagina
    TipoComprobanteServiceImpl TipoComprobanteService = new TipoComprobanteServiceImpl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tipo_comprobante);

        Toolbar toolbar = findViewById(R.id.toolbar);
        listTipoComprobante = findViewById(R.id.listTipoComprobante);
        newTipoComprobante = (FloatingActionButton) findViewById(R.id.accion_agregarTipoComprobante);
        edictTipoComprobante = (FloatingActionButton) findViewById(R.id.accion_editarTipoComprobante);
        eliminarTipoComprobante = (FloatingActionButton) findViewById(R.id.accion_eliminarTipoComprobante);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        listTipoComprobante.setOnItemClickListener(this);
        newTipoComprobante.setOnClickListener(this);
        edictTipoComprobante.setOnClickListener(this);
        eliminarTipoComprobante.setOnClickListener(this);

        TipoComprobanteService.listarTipoComprobantes(listTipoComprobante);

        //activacion del toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //accion al refrescar la vista
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                TipoComprobanteService.listarTipoComprobantes(listTipoComprobante);
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
        if(v == newTipoComprobante){
            Intent intent = new Intent(this, FormTipoComprobanteActivity.class);
            startActivity(intent);
        }
        if (v == edictTipoComprobante) {
            TipoComprobanteService.editarTipoComprobantes(this);
        }
        if (v == eliminarTipoComprobante) {
            TipoComprobanteService.eliminarTipoComprobantes(this);
        }
    }

    //accion click de la lista
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}