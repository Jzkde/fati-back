package com.fatidecoraciones.pedidos.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Flex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tela;
    private double precio;

    public Flex() {
    }

    public Flex (String tela, double precio) {

        this.tela = tela;
        this.precio = precio;
    }
}
