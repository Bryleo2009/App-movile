package com.example.ofsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoFilter implements Serializable {
    Producto producto;
    List<Talla> tallas;
    List<Color> colors;


    public com.example.ofsystem.Model.Producto getProducto() {
        return producto;
    }

    public void setProducto(com.example.ofsystem.Model.Producto producto) {
        producto = producto;
    }

    public String getTallas() {
        return concatenarTalla(tallas);
    }

    public List<Talla> getTallas2() {
        return tallas;
    }

    public void setTallas(List<Talla> tallas) {
        tallas = tallas;
    }

    public String getColors() {
        return concatenarColor(colors);
    }

    public List<Color> getColors2() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        colors = colors;
    }

    public String concatenarColor(List<Color> lista) {
        StringBuilder sb = new StringBuilder();
        for (Color obje : lista) {
            if(obje.getVistaItem() == null){
                sb.append(' ' +", ");
            } else {
                sb.append(obje.getVistaItem() +", ");
            }
        }
        return sb.toString();
    }

    public String concatenarTalla(List<Talla> lista) {
        StringBuilder sb = new StringBuilder();
        for (Talla obje : lista) {
            if(obje.getVistaItem() == null){
                sb.append(' ' +", ");
            } else {
                sb.append(obje.getVistaItem() +", ");
            }
        }
        return sb.toString();
    }
}
