package com.example.ofsystem.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Color implements Serializable {
    private int idColor;
    private String identItem;
    private  String nombreItem;

    private  String abreviItem;

    private  String vistaItem;

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public String getIdentItem() {
        return identItem;
    }

    public void setIdentItem(String identItem) {
        this.identItem = identItem;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public String getAbreviItem() {
        return abreviItem;
    }

    public void setAbreviItem(String abreviItem) {
        this.abreviItem = abreviItem;
    }

    public String getVistaItem() {
        return vistaItem;
    }

    public void setVistaItem(String vistaItem) {
        this.vistaItem = vistaItem;
    }

    public Color(int idColor) {
        this.idColor = idColor;
    }
}
