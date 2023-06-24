package com.example.ofsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComprobanteFilter implements Serializable {
    public Cliente cliente;
    public List<ProductoFilter> productoStorageList;//
    public double montoProducto; //
    public double igv;
    public double ammount; //
    public String direccionComp;//
    public String ubigeoComp;//
    public boolean idTc;//
    public String ruc;//
    public String razonSocial;//
    public Trabajador trabajador;
    public String nombreRecojo;
    public String apellidoRecojo;
    public String celularRecojo;
    public String correoRecojo;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ProductoFilter> getProductoStorageList() {
        return productoStorageList;
    }

    public void setProductoStorageList(List<ProductoFilter> productoStorageList) {
        this.productoStorageList = productoStorageList;
    }

    public double getMontoProducto() {
        return montoProducto;
    }

    public void setMontoProducto(double montoProducto) {
        this.montoProducto = montoProducto;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public String getDireccionComp() {
        return direccionComp;
    }

    public void setDireccionComp(String direccionComp) {
        this.direccionComp = direccionComp;
    }

    public String getUbigeoComp() {
        return ubigeoComp;
    }

    public void setUbigeoComp(String ubigeoComp) {
        this.ubigeoComp = ubigeoComp;
    }

    public boolean isIdTc() {
        return idTc;
    }

    public void setIdTc(boolean idTc) {
        this.idTc = idTc;
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

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public String getNombreRecojo() {
        return nombreRecojo;
    }

    public void setNombreRecojo(String nombreRecojo) {
        this.nombreRecojo = nombreRecojo;
    }

    public String getApellidoRecojo() {
        return apellidoRecojo;
    }

    public void setApellidoRecojo(String apellidoRecojo) {
        this.apellidoRecojo = apellidoRecojo;
    }

    public String getCelularRecojo() {
        return celularRecojo;
    }

    public void setCelularRecojo(String celularRecojo) {
        this.celularRecojo = celularRecojo;
    }

    public String getCorreoRecojo() {
        return correoRecojo;
    }

    public void setCorreoRecojo(String correoRecojo) {
        this.correoRecojo = correoRecojo;
    }
}
