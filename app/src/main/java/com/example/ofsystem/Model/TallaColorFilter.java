package com.example.ofsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TallaColorFilter {

    public int cantidad;
    public int talla;
    public int color;

    public TallaColorFilter(int cantidad, int talla, int color) {
        this.cantidad = cantidad;
        this.talla = talla;
        this.color = color;
    }

    public TallaColorFilter() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
