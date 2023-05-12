package com.example.ofsystem.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Random;



@Data
@AllArgsConstructor
@NoArgsConstructor
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

	public boolean isExistente;


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
		System.out.println(lista);
		StringBuilder sb = new StringBuilder();
		if(lista.getVistaItem() == null){
			sb.append(' ');
		} else {
			sb.append(lista.getVistaItem());
		}
		return sb.toString();
	}


	public void setIUP() {
		this.IUP = generarIUP(nombreProduct,idCateg.getIdCateg(),idMarca.getIdMarca(),idTipoProduc.getIdTipoProduc());
	}

	public String generarIUP(String nombreProduct, int idCateg, int idMarca, int idTipoProduc) {
		Random random = new Random();
		int randomNumber = random.nextInt(999); // genera un número aleatorio entre 0 y 999
		String nombreProductoSinEspacios = nombreProduct.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
		int mitad = nombreProductoSinEspacios.length() / 2;
		char primeraLetra = nombreProductoSinEspacios.charAt(0);
		char letraMitad = nombreProductoSinEspacios.charAt(mitad);
		char terceraLetra = nombreProductoSinEspacios.charAt(nombreProductoSinEspacios.length() - 2);
		char ultimaLetra = nombreProductoSinEspacios.charAt(nombreProductoSinEspacios.length() - 1);
		String iup = primeraLetra + "-" + 'C' + idCateg + 'M' + idMarca + "TP" + idTipoProduc + letraMitad + '-' + terceraLetra + '-' + String.format("%03d", randomNumber) + '-' + ultimaLetra;
		return iup;
	}


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

	public Marca getIdMarca() {
		return idMarca;
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

	public List<Etiquetas> getIdEtiqueta() {
		return idEtiqueta;
	}

	public void setIdEtiqueta(List<Etiquetas> idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}

	public boolean isExistente() {
		return isExistente;
	}

	public void setExistente(boolean existente) {
		isExistente = existente;
	}
}
