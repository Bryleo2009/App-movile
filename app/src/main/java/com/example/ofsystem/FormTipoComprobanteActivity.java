package com.example.ofsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.example.ofsystem.Model.TipoComprobante;
import com.example.ofsystem.Service.TipoComprobanteServiceImpl;

public class FormTipoComprobanteActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    TipoComprobanteServiceImpl TipoComprobanteService = new TipoComprobanteServiceImpl();
    Button btnRegistrarTipoComprobante;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tipo_comprobante);

        Toolbar toolbar = findViewById(R.id.toolbar);
        btnRegistrarTipoComprobante = findViewById(R.id.btnRegistrarTipoComprobante);

        //activacion del toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRegistrarTipoComprobante.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.hasExtra("Item")) {
            System.out.println("entre al if");
            TipoComprobante valor = (TipoComprobante) intent.getSerializableExtra("Item");
            System.out.println("Item: " + valor.getIdTipoComp());

            // Obtener referencias a los campos del formulario
            EditText nombreEditText = findViewById(R.id.input_nomrbeTipoComprobante);
            EditText descripcionEditText = findViewById(R.id.input_descripcionTipoComprobante);
            EditText idEditText = findViewById(R.id.input_idTipoComprobante);

            // Asignar los valores del objeto ProductoFilter a los campos del formulario
            nombreEditText.setText(valor.getNombre());
            descripcionEditText.setText(valor.getDescripcion());
            idEditText.setText(String.valueOf(valor.getIdTipoComp()));

            btnRegistrarTipoComprobante.setText("Guardar");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnRegistrarTipoComprobante) {
            TipoComprobante TipoComprobante = new TipoComprobante();

            EditText nombreEditText = findViewById(R.id.input_nomrbeTipoComprobante);
            EditText descripcionEditText = findViewById(R.id.input_descripcionTipoComprobante);
            EditText idEditText = findViewById(R.id.input_idTipoComprobante);


            String decripcion = nombreEditText.getText().toString();
            String nombre = descripcionEditText.getText().toString();


            TipoComprobante.setDescripcion(decripcion);
            TipoComprobante.setNombre(nombre);


            Context context = this;
            if(btnRegistrarTipoComprobante.getText().equals("Guardar")){
                int id = Integer.parseInt(idEditText.getText().toString());
                TipoComprobante.setIdTipoComp(id);
                TipoComprobanteService.crearTipoComprobantes(context,TipoComprobante,v,true);
            } else {
                TipoComprobanteService.crearTipoComprobantes(context,TipoComprobante,v,false);
            }

            System.out.println("TipoComprobante " + TipoComprobante);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
}