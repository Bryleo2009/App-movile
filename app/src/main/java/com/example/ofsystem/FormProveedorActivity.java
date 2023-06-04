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

import com.example.ofsystem.Model.Proveedor;
import com.example.ofsystem.Service.ProveedorServiceImpl;

public class FormProveedorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    ProveedorServiceImpl ProveedorService = new ProveedorServiceImpl();
    Button btnRegistrarProveedor;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_proveedor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        btnRegistrarProveedor = findViewById(R.id.btnRegistrarProveedor);

        //activacion del toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRegistrarProveedor.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.hasExtra("Item")) {
            System.out.println("entre al if");
            Proveedor valor = (Proveedor) intent.getSerializableExtra("Item");
            System.out.println("Item: " + valor);

            // Obtener referencias a los campos del formulario
            EditText rucEditText = findViewById(R.id.input_rucProveedor);
            EditText razonSocialEditText = findViewById(R.id.input_razonSocialProveedor);
            EditText celularEditText = findViewById(R.id.input_celularProveedor);
            EditText emailEditText = findViewById(R.id.input_emailProveedor);
            EditText direccionEditText = findViewById(R.id.input_direccionProveedor);

            // Asignar los valores del objeto ProductoFilter a los campos del formulario
            rucEditText.setText(valor.getRuc());
            razonSocialEditText.setText(valor.getRazonSocial());
            celularEditText.setText(valor.getCelular());
            emailEditText.setText(valor.getEmail());
            direccionEditText.setText(valor.getDireccion());

            rucEditText.setEnabled(false);

            btnRegistrarProveedor.setText("Guardar");
        }
        
    }

    @Override
    public void onClick(View v) {
        if (v == btnRegistrarProveedor) {
            Proveedor Proveedor = new Proveedor();

            EditText rucEditText = findViewById(R.id.input_rucProveedor);
            EditText razonSocialEditText = findViewById(R.id.input_razonSocialProveedor);
            EditText celularEditText = findViewById(R.id.input_celularProveedor);
            EditText emailEditText = findViewById(R.id.input_emailProveedor);
            EditText direccionEditText = findViewById(R.id.input_direccionProveedor);

            String ruc = rucEditText.getText().toString();
            String razonSocial = razonSocialEditText.getText().toString();
            String celular = celularEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String direccion = direccionEditText.getText().toString();


            Proveedor.setRuc(ruc);
            Proveedor.setRazonSocial(razonSocial);
            Proveedor.setCelular(celular);
            Proveedor.setEmail(email);
            Proveedor.setDireccion(direccion);

            Context context = this;
            if(btnRegistrarProveedor.getText().equals("Guardar")){
                ProveedorService.crearProveedors(context,Proveedor,v,true);
            } else {
                ProveedorService.crearProveedors(context,Proveedor,v,false);
            }

            System.out.println("Proveedor " + Proveedor);
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