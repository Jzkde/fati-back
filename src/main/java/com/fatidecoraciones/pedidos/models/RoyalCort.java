package com.fatidecoraciones.pedidos.models;

import com.fatidecoraciones.pedidos.enums.Sistema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class RoyalCort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tela;
    private double precio;
    private boolean esTela;
    @Enumerated(EnumType.STRING)
    private Sistema sistema;

    public RoyalCort() {
    }

    public RoyalCort(String tela, double precio, boolean esTela, Sistema sistema) {
        this.tela = tela;
        this.precio = precio;
        this.esTela = esTela;
        this.sistema = sistema;
    }
}
