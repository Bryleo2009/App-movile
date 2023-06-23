package com.example.ofsystem.ui.product;

// Importaciones de clases y bibliotecas adicionales

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ofsystem.FormProductActivity;
import com.example.ofsystem.MenuActivity;
import com.example.ofsystem.Model.CartItems;
import com.example.ofsystem.R;
import com.example.ofsystem.Service.ProductoAdapter;
import com.example.ofsystem.Service.ProductoServiceImpl;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListProductFragment extends Fragment implements View.OnClickListener {

    RecyclerView listProduct;
    FloatingActionButton newProduct, edictProduct, eliminarProduct;
    SwipeRefreshLayout swipeRefreshLayout;
    ProductoServiceImpl productService = new ProductoServiceImpl();
    private CartItems cartItems; // Variable para almacenar los elementos del carrito

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_product, container, false);

        newProduct = view.findViewById(R.id.accion_agregar);
        edictProduct = view.findViewById(R.id.accion_editar);
        eliminarProduct = view.findViewById(R.id.accion_eliminar);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        newProduct.setOnClickListener(this);
        edictProduct.setOnClickListener(this);
        eliminarProduct.setOnClickListener(this);

        listProduct = view.findViewById(R.id.listProductos);
        listProduct.setHasFixedSize(true);
        listProduct.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        // Crear una instancia de ProductoAdapter sin asignar cartItems inicialmente
        ProductoAdapter adapter = new ProductoAdapter(new ArrayList<>());
        listProduct.setAdapter(adapter);

        // Recuperar los datos del carrito si están disponibles
        Bundle bundle = getArguments();
        if (bundle != null) {
            cartItems = (CartItems) bundle.getSerializable("cartItems");
            adapter.setCartItems(cartItems);
        }

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
}
