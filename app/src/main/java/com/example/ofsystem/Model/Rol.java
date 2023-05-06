package com.example.ofsystem.Model;

import lombok.Data;
import lombok.Getter;

@Data
public class Rol {
     int idRol;
     String identItem;
     String nombreItem;
     String abreviItem;
     String vistaItem;

     public int getIdRol() {
          return idRol;
     }

     public void setIdRol(int idRol) {
          this.idRol = idRol;
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
