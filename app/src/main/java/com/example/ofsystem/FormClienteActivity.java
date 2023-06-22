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

import com.example.ofsystem.Model.Categoria;
import com.example.ofsystem.Model.Cliente;
import com.example.ofsystem.Model.Etiqueta;
import com.example.ofsystem.Model.Marca;
import com.example.ofsystem.Model.Producto;
import com.example.ofsystem.Model.ProductoFilter;
import com.example.ofsystem.Model.RegistroProductFilter;
import com.example.ofsystem.Model.TallaColorFilter;
import com.example.ofsystem.Model.TipoProducto;
import com.example.ofsystem.Service.ClienteServiceImpl;
import com.example.ofsystem.Service.MarcaServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormClienteActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    ClienteServiceImpl clienteService = new ClienteServiceImpl();
    Button btnRegistrarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cliente);

        Toolbar toolbar = findViewById(R.id.toolbar);
        btnRegistrarCliente = findViewById(R.id.btnRegistrarCliente);

        //activacion del toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRegistrarCliente.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.hasExtra("Item")) {
            System.out.println("entre al if");
            Cliente valor = (Cliente) intent.getSerializableExtra("Item");
            System.out.println("Item: " + valor);

            // Obtener referencias a los campos del formulario
            EditText dniEditText = findViewById(R.id.input_dniProduct);
            EditText nombreEditText = findViewById(R.id.input_nombreCliente);
            EditText apellidoEditText = findViewById(R.id.input_apellidoCliente);
            EditText direccionEditText = findViewById(R.id.input_direccionCliente);
            EditText celularEditText = findViewById(R.id.input_celularCliente);
            EditText correoEditText = findViewById(R.id.input_correoCliente);

            // Asignar los valores del objeto ProductoFilter a los campos del formulario
            dniEditText.setText(valor.getNumDocumento());
            nombreEditText.setText(valor.getNombre());
            apellidoEditText.setText(valor.getApellido());
            direccionEditText.setText(valor.getDireccion());
            celularEditText.setText(valor.getTelefono());
            correoEditText.setText(valor.getCorreo());

            dniEditText.setEnabled(false);

            btnRegistrarCliente.setText("Guardar");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnRegistrarCliente) {
            Cliente cliente = new Cliente();

            EditText dniEditText = findViewById(R.id.input_dniProduct);
            EditText nombreEditText = findViewById(R.id.input_nombreCliente);
            EditText apellidoEditText = findViewById(R.id.input_apellidoCliente);
            EditText direccionEditText = findViewById(R.id.input_direccionCliente);
            EditText celularEditText = findViewById(R.id.input_celularCliente);
            EditText correoEditText = findViewById(R.id.input_correoCliente);

            String dni = dniEditText.getText().toString();
            String nombre = nombreEditText.getText().toString();
            String apellido = apellidoEditText.getText().toString();
            String direccion = direccionEditText.getText().toString();
            String celular = celularEditText.getText().toString();
            String correo = correoEditText.getText().toString();


            cliente.setNumDocumento(dni);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDireccion(direccion);
            cliente.setTelefono(celular);
            cliente.setCorreo(correo);

            Context context = this;
            if(btnRegistrarCliente.getText().equals("Guardar")){
                clienteService.crearClientes(context,cliente,v,true);
            } else {
                clienteService.crearClientes(context,cliente,v,false);
            }

            System.out.println("cliente " + cliente);
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