package com.fatidecoraciones.FatiDeco.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Rango {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int anchoMin;
    private int anchoMax;
    private int altoMin;
    private int altoMax;
    private float sistemaMin;
    private float telaMin;

    @ManyToOne
    private CortEspeciales cortEspeciales;

    public Rango() {
    }

    public Rango(int anchoMin, int anchoMax, int altoMin, int altoMax, float sistemaMin, float telaMin) {
        this.anchoMin = anchoMin;
        this.anchoMax = anchoMax;
        this.altoMin = altoMin;
        this.altoMax = altoMax;
        this.sistemaMin = sistemaMin;
        this.telaMin = telaMin;
    }
}