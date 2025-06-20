package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.models.Producto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDto {

    private Long id;
    private String art;
    private String nombre;
    private double precio;
    private boolean esTela;
    private String marca;

    public ProductoDto() {
    }

    public ProductoDto(Producto producto) {
        this.id = producto.getId();
        this.art = producto.getArt();
        this.nombre = producto.getNombre();
        this.precio = producto.getPrecio();
        this.esTela = producto.isEsTela();
        this.marca = producto.getMarca() != null ? producto.getMarca().getMarca() : null;
    }
}