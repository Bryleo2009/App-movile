package com.example.ofsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class RegistroProductFilter {
    private Producto producto;
    private List<TallaColorFilter> tallaColorFilters;

    public RegistroProductFilter() {
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<TallaColorFilter> getTallaColorFilters() {
        return tallaColorFilters;
    }

    public void setTallaColorFilters(List<TallaColorFilter> tallaColorFilters) {
        this.tallaColorFilters = tallaColorFilters;
    }
}
