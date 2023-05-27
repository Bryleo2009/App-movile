package com.example.ofsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.ofsystem.Model.Categoria;
import com.example.ofsystem.Model.Etiqueta;
import com.example.ofsystem.Model.Marca;
import com.example.ofsystem.Model.Producto;
import com.example.ofsystem.Model.ProductoFilter;
import com.example.ofsystem.Model.RegistroProductFilter;
import com.example.ofsystem.Model.TallaColorFilter;
import com.example.ofsystem.Model.TipoProducto;
import com.example.ofsystem.Service.CategoriaServiceImpl;
import com.example.ofsystem.Service.ClienteServiceImpl;
import com.example.ofsystem.Service.ColorServiceImpl;
import com.example.ofsystem.Service.EtiquetaServiceImpl;
import com.example.ofsystem.Service.MarcaServiceImpl;
import com.example.ofsystem.Service.ProductoServiceImpl;
import com.example.ofsystem.Service.TallaServiceImpl;
import com.example.ofsystem.Service.TipoProductoServiceImpl;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    CategoriaServiceImpl categoriaService = new CategoriaServiceImpl();
    TallaServiceImpl tallaService = new TallaServiceImpl();
    MarcaServiceImpl marcaService = new MarcaServiceImpl();
    ColorServiceImpl colorService = new ColorServiceImpl();
    EtiquetaServiceImpl etiquetaService = new EtiquetaServiceImpl();
    TipoProductoServiceImpl tipoProductoService = new TipoProductoServiceImpl();
    ProductoServiceImpl productoService = new ProductoServiceImpl();

    Spinner selectCategoria, selectMarca, selectTalla, selectColor, selectTipoProducto;
    LinearLayout checkEtiqueta;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product);

        Toolbar toolbar = findViewById(R.id.toolbar);
        selectCategoria = findViewById(R.id.select_categoria);
        selectMarca = findViewById(R.id.select_marca);
        selectTalla = findViewById(R.id.select_talla);
        selectColor = findViewById(R.id.select_color);
        selectTipoProducto = findViewById(R.id.select_tipo);
        checkEtiqueta = findViewById(R.id.etiquetaProduct);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        selectCategoria.setOnItemSelectedListener(this);
        selectMarca.setOnItemSelectedListener(this);
        selectTalla.setOnItemSelectedListener(this);
        selectColor.setOnItemSelectedListener(this);
        selectTipoProducto.setOnItemSelectedListener(this);
        btnRegistrar.setOnClickListener(this);

        categoriaService.listarCategorias(null);
        categoriaService.listarCategorias(selectCategoria);
        tallaService.listarTallas(null);
        tallaService.listarTallas(selectTalla);
        marcaService.listarMarcas(null);
        marcaService.listarMarcas(selectMarca);
        colorService.listarColors(null);
        colorService.listarColors(selectColor);
        tipoProductoService.listarTipoProductos(null);
        tipoProductoService.listarTipoProductos(selectTipoProducto);
        etiquetaService.listarEtiquetas(null);
        etiquetaService.listarEtiquetas(checkEtiqueta);

        //activacion del toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public void onClick(View v) {
        if (v == btnRegistrar) {
            Producto producto = new Producto();
            RegistroProductFilter registroProductFilter = new RegistroProductFilter();

            EditText nombreProductEditText = findViewById(R.id.input_nombreProduct);
            EditText descripcionProductEditText = findViewById(R.id.input_descripcionProduct);
            EditText imagenProductEditText = findViewById(R.id.input_imagenProduct);
            EditText precioUniProductEditText = findViewById(R.id.input_precioUniProduct);
            EditText precioDescProductEditText = findViewById(R.id.input_precioDescProduct);

            String nombreProduct = nombreProductEditText.getText().toString();
            String descripcionProduct = descripcionProductEditText.getText().toString();
            String imagenProduct = imagenProductEditText.getText().toString();
            double precioUniProduct = Double.parseDouble(precioUniProductEditText.getText().toString());
            double precioDescProduct = Double.parseDouble(precioDescProductEditText.getText().toString());

            producto.setDescripcionProduct(descripcionProduct);
            producto.setNombreProduct(nombreProduct);
            producto.setPrecioUni(precioUniProduct);
            producto.setPrecioDescuProduct(precioDescProduct);
            producto.setImagen(imagenProduct);
            producto.setIdCateg(new Categoria(categoriaService.obtenerElementoSeleccionado()));
            producto.setIdMarca(new Marca(marcaService.obtenerElementoSeleccionado()));
            producto.setIdTipoProduc(new TipoProducto(tipoProductoService.obtenerElementoSeleccionado()));
            if(producto.getPrecioDescuProduct() != 0){
                producto.setPrecioDescProduct(true);
            }
            List<Etiqueta> etiquetas =  new ArrayList<>();
            for (Integer codEtiqueta: etiquetaService.obtenerEtiquetasSeleccionadas()) {
                etiquetas.add(new Etiqueta(codEtiqueta));
            }
            producto.setIdEtiqueta(etiquetas);
            producto.setExistente(true);

            List<TallaColorFilter> tallaColorFilters =  new ArrayList<>();
            tallaColorFilters.addAll(Arrays.asList(
                    new TallaColorFilter(50,tallaService.obtenerElementoSeleccionado(),colorService.obtenerElementoSeleccionado())));

            registroProductFilter.setTallaColorFilters(tallaColorFilters);
            registroProductFilter.setProducto(producto);

            Context context = this;
            productoService.crearProductos(context,registroProductFilter,v);

            System.out.println("Producto " + producto);
            System.out.println("Etiqueta " + etiquetaService.obtenerEtiquetasSeleccionadas());
            System.out.println("Categoria " +categoriaService.obtenerElementoSeleccionado());
            System.out.println("Talla " +tallaService.obtenerElementoSeleccionado());
            System.out.println("Marca " +marcaService.obtenerElementoSeleccionado());
            System.out.println("TipoProducto " +tipoProductoService.obtenerElementoSeleccionado());
            System.out.println("Color " +colorService.obtenerElementoSeleccionado());
        }
    }
}