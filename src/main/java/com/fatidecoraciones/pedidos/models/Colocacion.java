package com.fatidecoraciones.pedidos.models;

import com.fatidecoraciones.pedidos.enums.Sistema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
public class Colocacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private double precio;

    public Colocacion() {
    }

    public Colocacion(String servic, double precio) {
        this.tipo = servic;
        this.precio = precio;
    }


}
