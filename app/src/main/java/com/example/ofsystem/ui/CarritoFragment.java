package com.example.ofsystem.ui;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ofsystem.Model.CartItem;
import com.example.ofsystem.Model.CartItems;
import com.example.ofsystem.Model.ProductoFilter;
import com.example.ofsystem.R;
import com.example.ofsystem.Service.ProductoServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CarritoFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private List<CartItem> cartItems;
    private ProductoAdapterCarrito cartAdapter;
    Button btnRealizarCompra;
    ProductoServiceImpl productService = new ProductoServiceImpl();

    public CarritoFragment() {
        // Constructor vacío requerido
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carrito, container, false);

        // Inicializar la lista de elementos del carrito
        cartItems = obtenerCarrito().getItems();

        if (cartItems != null) {
            btnRealizarCompra = view.findViewById(R.id.btnRealizarCompra);
            btnRealizarCompra.setOnClickListener(this);
        }

        // Configurar el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewCarrito);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);

        // Crear una instancia del adaptador del carrito
        cartAdapter = new ProductoAdapterCarrito(cartItems, new ProductoAdapterCarrito.OnItemClickListener() {
            @Override
            public void onItemClick(CartItem cartItem) {
                // Lógica para manejar el clic en un elemento del carrito
                // ...
                eliminarProductoDelCarrito(cartItem); // Eliminar el producto del carrito
                actualizarCarrito(); // Actualizar el carrito y guardar en SharedPreferences
            }
        });

        // Asignar el adaptador al RecyclerView
        recyclerView.setAdapter(cartAdapter);

        return view;
    }

    private void eliminarProductoDelCarrito(CartItem cartItem) {
        // Lógica para eliminar el producto del carrito
        cartItems.remove(cartItem);
    }

    private void actualizarCarrito() {
        cartAdapter.notifyDataSetChanged();
        guardarCarrito(); // Guardar el carrito actualizado en SharedPreferences
    }

    private void guardarCarrito() {
        // Obtener una instancia de SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);

        // Obtener un editor de SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Convertir la lista de elementos del carrito a una representación de cadena (por ejemplo, JSON)
        Gson gson = new Gson();
        String carritoJson = gson.toJson(cartItems);

        // Guardar el carrito de compras en SharedPreferences
        editor.putString("carrito", carritoJson);
        editor.apply();
    }

    // Resto de los métodos y lógica relacionados con el carrito...

    private CartItems obtenerCarrito() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);

        // Obtener el carrito de compras como una cadena JSON desde SharedPreferences
        String carritoJson = sharedPreferences.getString("carrito", "");

        // Convertir la cadena JSON a una lista de objetos de tipo CartItem
        Gson gson = new Gson();
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        List<CartItem> cartItemsList = gson.fromJson(carritoJson, type);

        // Crear una instancia de CartItems y establecer la lista de elementos
        CartItems cart = new CartItems();
        cart.setItems(cartItemsList);

        // Si no se pudo recuperar el carrito de compras, inicializar una lista vacía
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }

        return cart;
    }

    @Override
    public void onClick(View v) {
        if (v == btnRealizarCompra) {
            System.out.println("Accedí al botón");
            List<ProductoFilter> productoFilters = new ArrayList<>();
            System.out.println("cartItems " + cartItems);
            try {
                for (CartItem item : cartItems) {
                    ProductoFilter productoFilter = new ProductoFilter();
                    System.out.println(item.getProducto());
                    productoFilter.setProducto(item.getProducto());
                    productoFilter.setCantidad(item.getQuantity());
                    productoFilters.add(productoFilter);
                    eliminarProductoDelCarrito(item);
                }

                System.out.println(productoFilters.size());
                // Pasar requireContext() como contexto al llamar a disminuirStock()
                productService.disminuirStock(requireContext(), productoFilters);
                actualizarCarrito();
            } catch (Exception e) {
                System.out.println(e);
            }
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
        holder.txtPrecio.setText(String.valueOf(cartItem.getProducto().getPrecioUni()));
        holder.txtTipo.setText(cartItem.getProducto().getIdTipoProduc().getIdentItem());

        try {
            Picasso.get().load(cartItem.getProducto().getImagen()).into(holder.productImageView);
            System.out.println("Imagen cargada correctamente");
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
        // Otros elementos de la tarjeta del carrito...

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para eliminar el producto del carrito
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(cartItem);
                }
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
        ImageView productImageView;
        TextView txtTipo;
        TextView txtPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            txtTipo = itemView.findViewById(R.id.txtTipo);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            txtNombreProducto = itemView.findViewById(R.id.txtNombreProducto);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
            btnEliminar = itemView.findViewById(R.id.btnEliminarDelCarrito);
        }
    }
}