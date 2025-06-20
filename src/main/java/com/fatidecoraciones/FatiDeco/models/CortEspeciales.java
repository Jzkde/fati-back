package com.fatidecoraciones.FatiDeco.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CortEspeciales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tela;
    private double precio;
    private boolean esTela;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sistema_id")
    private Sistema sistema;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marca_id")
    private Marca marca;



    public CortEspeciales() {
    }

    public CortEspeciales(String tela, double precio, boolean esTela) {
        this.tela = tela;
        this.precio = precio;
        this.esTela = esTela;

    }
}