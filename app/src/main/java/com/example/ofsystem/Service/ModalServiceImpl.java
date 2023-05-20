package com.example.ofsystem.Service;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.ofsystem.Config.Config;
import com.example.ofsystem.Model.Producto;
import com.example.ofsystem.Model.ProductoFilter;
import com.example.ofsystem.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class ModalServiceImpl extends DialogFragment {

    private Serializable mData;

    public static ModalServiceImpl newInstance(ProductoFilter data) {
        ModalServiceImpl fragment = new ModalServiceImpl();
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mData = getArguments().getSerializable("data");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        try {

            // Inflar el diseño personalizado para el modal
            View view = inflater.inflate(R.layout.cmp_modal, null);

            // Configurar el contenido del modal
            TextView txtNombre = (TextView) view.findViewById(R.id.txtNombre2);
            TextView txtTP = (TextView) view.findViewById(R.id.txtTipo2);
            TextView txtPrecio = (TextView) view.findViewById(R.id.txtPrecio2);
            TextView txtDesc = (TextView) view.findViewById(R.id.txtDescripcion2);
            TextView txtTalla = (TextView) view.findViewById(R.id.txtTalla);
            TextView txtEtiquet = (TextView) view.findViewById(R.id.txtEtiqueta);
            TextView txtMarcas = (TextView) view.findViewById(R.id.txtMarca);
            TextView txtIUP = (TextView) view.findViewById(R.id.txtIUP);
            TextView txtColor = (TextView) view.findViewById(R.id.txtColor);

            txtNombre.setText(((ProductoFilter) mData).getProducto().getNombreProduct());
            txtTP.setText(((ProductoFilter) mData).getProducto().getIdTipoProduc().getVistaItem());
            if(((ProductoFilter) mData).getProducto().isPrecioDescProduct()){
                txtPrecio.setText(String.valueOf(((ProductoFilter) mData).getProducto().getPrecioDescuProduct()));
            } else {
                txtPrecio.setText(String.valueOf(((ProductoFilter) mData).getProducto().getPrecioUni()));
            }
            txtPrecio.setText(String.valueOf(((ProductoFilter) mData).getProducto().getPrecioUni()));
            txtDesc.setText(((ProductoFilter) mData).getProducto().getDescripcionProduct());
            txtTalla.setText(((ProductoFilter) mData).getTallas());
            txtEtiquet.setText(((ProductoFilter) mData).getProducto().concatenarEtiqueta(((ProductoFilter) mData).getProducto().getIdEtiqueta()));
            txtMarcas.setText(((ProductoFilter) mData).getProducto().concatenarMarca(((ProductoFilter) mData).getProducto().getIdMarca()));
            txtIUP.setText(((ProductoFilter) mData).getProducto().getIUP());
            txtColor.setText(((ProductoFilter) mData).getColors());

            ImageView imageView = view.findViewById(R.id.imgQr); // Reemplaza R.id.imageView con el ID de tu vista ImageView

            try {
                //Picasso.get().load(Config.BASE_URL + "/media/productosQr/" + ((Producto) mData).getIUP() + ".png").into(imageView);

                Picasso.get().load(((ProductoFilter) mData).getProducto().getImagen()).into(imageView);
                System.out.println("Imagen cargada correctamente");
            } catch (Exception e) {
                System.out.println("Error al cargar la imagen: " + e.getMessage());
            }

            // Configurar los botones del modal
            builder.setView(view)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // Cerrar el modal
                        }
                    });
//                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // Cerrar el modal
//                        }
//                    });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return builder.create();
    }
}
