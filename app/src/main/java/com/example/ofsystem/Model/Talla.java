package com.example.ofsystem.Model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Talla {

    private int idTalla;

    private String identItem;

    private  String nombreItem;

    private  String abreviItem;

    private  String vistaItem;

    public int getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(int idTalla) {
        this.idTalla = idTalla;
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
}


