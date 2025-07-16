package com.fatidecoraciones.FatiDeco.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private boolean esAdicional;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sistema_id")
    private Sistema sistema;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @OneToMany(mappedBy = "cortEspeciales", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rango> rangos = new HashSet<>();

    public CortEspeciales() {
    }

    public CortEspeciales(String tela, double precio, boolean esTela, boolean esAdicional) {
        this.tela = tela;
        this.precio = precio;
        this.esTela = esTela;
        this.esAdicional = esAdicional;

    }

    public void addRango(Rango rango) {
        rango.setCortEspeciales(this);
        this.rangos.add(rango);
    }
}