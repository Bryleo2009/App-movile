package com.example.ofsystem.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class Proveedor implements Serializable {
    private String ruc;
    private String razonSocial;
    private String celular;
    private String email;
    private String direccion;

    public Proveedor() {
    }

    public Proveedor(String ruc, String razonSocial, String celular, String email, String direccion) {
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.celular = celular;
        this.email = email;
        this.direccion = direccion;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
