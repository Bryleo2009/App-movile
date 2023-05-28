package com.example.ofsystem.Model;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductoTallaColor implements Serializable {

    private int idProductoTallaColor;

    private Producto producto;

    private Talla talla;

    private Color color;

    public int stockRealProduct;

    public int stockVirtualProduct;

    public boolean existe_noexiste;



    public void setStock(int stockReal, int stockVirtual) {
        this.stockRealProduct = stockReal;
        this.stockVirtualProduct = stockVirtual;
    }

    public ProductoTallaColor() {
    }

    public ProductoTallaColor(Producto producto_id_product, Talla id_talla_id_talla,Color id_color_id_color, int Stock) {
        this.producto = producto_id_product;
        this.talla = id_talla_id_talla;
        this.color = id_color_id_color;
        this.existe_noexiste = true;
        setStock(Stock,Stock);
    }
}
