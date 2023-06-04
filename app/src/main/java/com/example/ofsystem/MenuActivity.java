package com.example.ofsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ofsystem.Model.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout btnProductos,btnClientes, btnProveedor, btnMedioPago, btnTipoComprobante;
    Button bt1, bt2, btn4,btn5, btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnProductos = findViewById(R.id.btnProductos);
        bt1 = findViewById(R.id.btn1);
        btnProductos.setOnClickListener(this);
        bt1.setOnClickListener(this);

        btnClientes = findViewById(R.id.btnClientes);
        bt2 = findViewById(R.id.btn2);
        btnClientes.setOnClickListener(this);
        bt2.setOnClickListener(this);

        btnProveedor = findViewById(R.id.btnProveedor);
        btn4 = findViewById(R.id.btn4);
        btnProveedor.setOnClickListener(this);
        btn4.setOnClickListener(this);

        btnMedioPago = findViewById(R.id.btnMedioPago);
        btn5 = findViewById(R.id.btn5);
        btnMedioPago.setOnClickListener(this);
        btn5.setOnClickListener(this);

        btnTipoComprobante = findViewById(R.id.btnTipoComprobante);
        btn6 = findViewById(R.id.btn6);
        btnTipoComprobante.setOnClickListener(this);
        btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnProductos || v == bt1) {
            Intent intent = new Intent(MenuActivity.this, ListProduct.class);
            startActivity(intent);
        }
        if (v == btnClientes || v == bt2) {
            Intent intent = new Intent(MenuActivity.this, ListClientActivity.class);
            startActivity(intent);
        }
        if (v == btnProveedor || v == btn4) {
            Intent intent = new Intent(MenuActivity.this, ListProveedorActivity.class);
            startActivity(intent);
        }
        if (v == btnMedioPago || v == btn5) {
            Intent intent = new Intent(MenuActivity.this, ListMedioPagoActivity.class);
            startActivity(intent);
        }
        if (v == btnTipoComprobante || v == btn6) {
            Intent intent = new Intent(MenuActivity.this, ListTipoComprobanteActivity.class);
            startActivity(intent);
        }
    }
}