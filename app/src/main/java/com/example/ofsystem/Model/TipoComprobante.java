package com.example.ofsystem.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TipoComprobante implements Serializable {
    private int idTipoComp;
    private String nombre;
    private String descripcion;

    public TipoComprobante() {
    }

    public TipoComprobante(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdTipoComp() {
        return idTipoComp;
    }

    public void setIdTipoComp(int idTipoComp) {
        this.idTipoComp = idTipoComp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
