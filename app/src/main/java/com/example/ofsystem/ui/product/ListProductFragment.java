package com.example.ofsystem.ui.product;

// Importaciones de clases y bibliotecas adicionales

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ofsystem.FormProductActivity;
import com.example.ofsystem.MenuActivity;
import com.example.ofsystem.Model.CartItem;
import com.example.ofsystem.Model.CartItems;
import com.example.ofsystem.R;
import com.example.ofsystem.Service.ProductoServiceImpl;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListProductFragment extends Fragment implements View.OnClickListener {

    RecyclerView listProduct;
    FloatingActionButton newProduct, edictProduct, eliminarProduct;
    SwipeRefreshLayout swipeRefreshLayout;
    ProductoServiceImpl productService = new ProductoServiceImpl();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_product, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        listProduct = view.findViewById(R.id.listProductos);
        newProduct = (FloatingActionButton) view.findViewById(R.id.accion_agregar);
        edictProduct = (FloatingActionButton) view.findViewById(R.id.accion_editar);
        eliminarProduct = (FloatingActionButton) view.findViewById(R.id.accion_eliminar);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        newProduct.setOnClickListener(this);
        edictProduct.setOnClickListener(this);
        eliminarProduct.setOnClickListener(this);

        listProduct = view.findViewById(R.id.listProductos);
        listProduct.setHasFixedSize(true);
        listProduct.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        productService.listarProductos2(listProduct);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                productService.listarProductos2(listProduct);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(requireActivity(), MenuActivity.class);
            startActivity(intent);
            requireActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == newProduct) {
            Intent intent = new Intent(requireActivity(), FormProductActivity.class);
            startActivity(intent);
        }
        if (v == edictProduct) {
            Context context = requireContext();
            productService.editarProductos(requireActivity());
        }
        if (v == eliminarProduct) {
            Context context = requireContext();
            productService.eliminarProdcutos(requireActivity());
        }
    }

    public CartItems obtenerCarrito() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);

        // Obtener el carrito de compras como una cadena JSON desde SharedPreferences
        String carritoJson = sharedPreferences.getString("carrito", "");

        System.out.println("json: " + carritoJson);

        // Convertir la cadena JSON a una lista de objetos de tipo CartItem
        Gson gson = new Gson();
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        List<CartItem> cartItemsList = gson.fromJson(carritoJson, type);

        System.out.println("cartItemsList: " + cartItemsList);

        // Crear una instancia de CartItems y establecer la lista de elementos
        CartItems cart = new CartItems();
        cart.setItems(cartItemsList);

        // Si no se pudo recuperar el carrito de compras, inicializar una lista vacía
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }

        return cart;
    }

}
