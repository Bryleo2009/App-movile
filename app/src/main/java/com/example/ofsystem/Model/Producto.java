package com.example.ofsystem.Model;


import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Random;


@Data
public class Producto implements Serializable {
	public int idProduct;
	public String IUP;
	public String descripcionProduct;
	public String nombreProduct;
	public double precioUni;
	public boolean isPrecioDescProduct;
	public Double precioDescuProduct;
	public String imagen;
	public String rutaQr;
	public Categoria idCateg;
	public Marca idMarca;
	public TipoProducto idTipoProduc;
	public List<Etiquetas> idEtiqueta;
	public List<Talla> idTalla;
	public List<Color> idColor;
	public boolean isExistente;

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getIUP() {
		return IUP;
	}

	public void setIUP(String IUP) {
		this.IUP = IUP;
	}

	public String getDescripcionProduct() {
		return descripcionProduct;
	}

	public void setDescripcionProduct(String descripcionProduct) {
		this.descripcionProduct = descripcionProduct;
	}

	public String getNombreProduct() {
		return nombreProduct;
	}

	public void setNombreProduct(String nombreProduct) {
		this.nombreProduct = nombreProduct;
	}

	public double getPrecioUni() {
		return precioUni;
	}

	public void setPrecioUni(double precioUni) {
		this.precioUni = precioUni;
	}

	public boolean isPrecioDescProduct() {
		return isPrecioDescProduct;
	}

	public void setPrecioDescProduct(boolean precioDescProduct) {
		isPrecioDescProduct = precioDescProduct;
	}

	public Double getPrecioDescuProduct() {
		return precioDescuProduct;
	}

	public void setPrecioDescuProduct(Double precioDescuProduct) {
		this.precioDescuProduct = precioDescuProduct;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getRutaQr() {
		return rutaQr;
	}

	public void setRutaQr(String rutaQr) {
		this.rutaQr = rutaQr;
	}

	public Categoria getIdCateg() {
		return idCateg;
	}

	public void setIdCateg(Categoria idCateg) {
		this.idCateg = idCateg;
	}

	public String getIdMarca() {
		return concatenarMarca(idMarca);
	}

	public void setIdMarca(Marca idMarca) {
		this.idMarca = idMarca;
	}

	public TipoProducto getIdTipoProduc() {
		return idTipoProduc;
	}

	public void setIdTipoProduc(TipoProducto idTipoProduc) {
		this.idTipoProduc = idTipoProduc;
	}

	public String getIdEtiqueta() {
		return concatenarEtiqueta(idEtiqueta);
	}

	public void setIdEtiqueta(List<Etiquetas> idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}

	public String getIdTalla() {
		return concatenarTalla(idTalla);
	}

	public void setIdTalla(List<Talla> idTalla) {
		this.idTalla = idTalla;
	}

	public String getIdColor() {
		return concatenarColor(idColor);
	}

	public void setIdColor(List<Color> idColor) {
		this.idColor = idColor;
	}

	public boolean isExistente() {
		return isExistente;
	}

	public void setExistente(boolean existente) {
		isExistente = existente;
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

	public String concatenarEtiqueta(List<Etiquetas> lista) {
		StringBuilder sb = new StringBuilder();
		for (Etiquetas obje : lista) {
			if(obje.getVistaItem() == null){
				sb.append(' ' +", ");
			} else {
				sb.append(obje.getVistaItem() +", ");
			}
		}
		return sb.toString();
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

	public String concatenarMarca(Marca lista) {
		StringBuilder sb = new StringBuilder();
		if(lista.getVistaItem() == null){
			sb.append(' ');
		} else {
			sb.append(lista.getVistaItem());
		}
		return sb.toString();
	}

}
