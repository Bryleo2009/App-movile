package com.example.ofsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    //Creacion de mis componentes
    Button btnMenuListarProduct;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //inciador
        btnMenuListarProduct = findViewById(R.id.btnMenuListarProduct);

        //asignacion de funcion click
        btnMenuListarProduct.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MenuActivity.this, ListProduct.class);
        startActivity(intent);
    }
}