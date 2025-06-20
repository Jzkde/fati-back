package com.fatidecoraciones.FatiDeco.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String art;
    private String nombre;
    private double precio;
    private boolean esTela;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marca_id")
    private Marca marca;

    public Producto() {
    }

    public Producto(String art, String nombre, double precio, boolean esTela) {
        this.art = art;
        this.nombre = nombre;
        this.precio = precio;
        this.esTela = esTela;
    }
}