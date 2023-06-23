package com.example.ofsystem.Model;


import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trabajador implements Serializable {
	public int id;
	public String imagen;
	public Usuario idUserTrabajador;
	public boolean isTrabajador;

	public String nombre; //Juan Alkexander
	public String apellido; // Velazquez Soria
	public Date fechaNac;
	public String telefono; //987474234
	public String direccion; //Av.alarcon cercado de lima 45567
	public String ubigueo;
	public String correo;
	public String numDocumento;
	public Usuario idUserCliente;
	public TipoDoc idTipoDoc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Usuario getIdUserTrabajador() {
		return idUserTrabajador;
	}

	public void setIdUserTrabajador(Usuario idUserTrabajador) {
		this.idUserTrabajador = idUserTrabajador;
	}

	public boolean isTrabajador() {
		return isTrabajador;
	}

	public void setTrabajador(boolean trabajador) {
		isTrabajador = trabajador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUbigueo() {
		return ubigueo;
	}

	public void setUbigueo(String ubigueo) {
		this.ubigueo = ubigueo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public Usuario getIdUserCliente() {
		return idUserCliente;
	}

	public void setIdUserCliente(Usuario idUserCliente) {
		this.idUserCliente = idUserCliente;
	}

	public TipoDoc getIdTipoDoc() {
		return idTipoDoc;
	}

	public void setIdTipoDoc(TipoDoc idTipoDoc) {
		this.idTipoDoc = idTipoDoc;
	}
}
