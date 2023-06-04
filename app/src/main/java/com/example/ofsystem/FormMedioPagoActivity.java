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

import com.example.ofsystem.Model.MedioPago;
import com.example.ofsystem.Service.MedioPagoServiceImpl;

import java.text.ParseException;

public class FormMedioPagoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    MedioPagoServiceImpl MedioPagoService = new MedioPagoServiceImpl();
    Button btnRegistrarMedioPago;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_medio_pago);

        Toolbar toolbar = findViewById(R.id.toolbar);
        btnRegistrarMedioPago = findViewById(R.id.btnRegistrarMedioPago);

        //activacion del toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRegistrarMedioPago.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.hasExtra("Item")) {
            System.out.println("entre al if");
            MedioPago valor = (MedioPago) intent.getSerializableExtra("Item");
            System.out.println("Item: " + valor);

            // Obtener referencias a los campos del formulario
            EditText nombreEditText = findViewById(R.id.input_nomrbeMedioPAgo);
            EditText descripcionEditText = findViewById(R.id.input_descripcionMedioPago);
            EditText idEditText = findViewById(R.id.input_idMedioPago);

            // Asignar los valores del objeto ProductoFilter a los campos del formulario
            nombreEditText.setText(valor.getNombre());
            descripcionEditText.setText(valor.getDescripcion());
            idEditText.setText(String.valueOf(valor.getIdMedioPago()));

            btnRegistrarMedioPago.setText("Guardar");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnRegistrarMedioPago) {
            MedioPago MedioPago = new MedioPago();

            EditText nombreEditText = findViewById(R.id.input_nomrbeMedioPAgo);
            EditText descripcionEditText = findViewById(R.id.input_descripcionMedioPago);


            String decripcion = nombreEditText.getText().toString();
            String nombre = descripcionEditText.getText().toString();


            MedioPago.setDescripcion(decripcion);
            MedioPago.setNombre(nombre);


            Context context = this;
            if(btnRegistrarMedioPago.getText().equals("Guardar")){
                EditText idEditText = findViewById(R.id.input_idMedioPago);
                int id = Integer.parseInt(idEditText.getText().toString());
                MedioPago.setIdMedioPago(id);
                MedioPagoService.crearMedioPagos(context,MedioPago,v,true);
            } else {
                MedioPagoService.crearMedioPagos(context,MedioPago,v,false);
            }

            System.out.println("MedioPago " + MedioPago);
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