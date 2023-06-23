package com.example.ofsystem.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ofsystem.Model.CartItem;
import com.example.ofsystem.Model.ProductoFilter;
import com.example.ofsystem.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarritoFragment#} factory method to
 * create an instance of this fragment.
 */
public class CarritoFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductoAdapterCarrito cartAdapter;
    private List<CartItem> cartItems;

    public CarritoFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_carrito, container, false);

        // Inicializar la lista de elementos del carrito
        cartItems = new ArrayList<>();

        // Configurar el RecyclerView
        recyclerView = rootView.findViewById(R.id.recyclerViewCarrito);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        // Configurar el adaptador del carrito
        cartAdapter = new ProductoAdapterCarrito(cartItems, new ProductoAdapterCarrito.OnItemClickListener() {
            @Override
            public void onItemClick(CartItem cartItem) {
                // Lógica para manejar el clic en un elemento del carrito
                // ...
            }
        });
        recyclerView.setAdapter(cartAdapter);

        return rootView;
    }

    public void agregarAlCarrito(CartItem cartItem) {
        cartItems.add(cartItem);
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("CarritoFragment - Tamaño de la lista cartItems: " + cartItems.size());
    }
}
class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;

    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cmp_cards_carrito, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);

        // Actualizar los elementos de la tarjeta con los datos del elemento del carrito
        holder.txtNombreProducto.setText(cartItem.getProducto().getNombreProduct());
        holder.txtCantidad.setText(String.valueOf(cartItem.getQuantity()));
        // Otros elementos de la tarjeta del carrito...

        // Configurar el clic del botón "Eliminar del carrito"
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para eliminar el elemento del carrito...
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreProducto;
        TextView txtCantidad;
        // Otros elementos de la tarjeta del carrito...
        Button btnEliminar;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreProducto = itemView.findViewById(R.id.txtNombreProducto);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
            // Otros elementos de la tarjeta del carrito...
            btnEliminar = itemView.findViewById(R.id.btnEliminarDelCarrito);
        }
    }
}

class ProductoAdapterCarrito extends RecyclerView.Adapter<ProductoAdapterCarrito.ProductoViewHolder> {

    private List<CartItem> cartItems;
    private OnItemClickListener itemClickListener;

    public ProductoAdapterCarrito(List<CartItem> cartItems, OnItemClickListener itemClickListener) {
        this.cartItems = cartItems;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cmp_cards_carrito, parent, false);
        return new ProductoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);

        holder.txtNombreProducto.setText(cartItem.getProducto().getNombreProduct());
        holder.txtCantidad.setText(String.valueOf(cartItem.getQuantity()));

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(cartItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(CartItem cartItem);
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreProducto;
        TextView txtCantidad;
        Button btnEliminar;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreProducto = itemView.findViewById(R.id.txtNombreProducto);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
            btnEliminar = itemView.findViewById(R.id.btnEliminarDelCarrito);
        }
    }
}