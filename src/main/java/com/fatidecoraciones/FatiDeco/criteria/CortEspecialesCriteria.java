package com.fatidecoraciones.FatiDeco.criteria;

import lombok.Data;
import tech.jhipster.service.filter.*;

@Data
public class CortEspecialesCriteria {

    private LongFilter id;
    private StringFilter tela;
    private DoubleFilter precio;
    private StringFilter marca;
    private BooleanFilter esTela;

    public CortEspecialesCriteria() {
    }

    public CortEspecialesCriteria(LongFilter id, StringFilter tela, DoubleFilter precio,
                                  StringFilter marca, BooleanFilter esTela) {
        this.id = id;
        this.tela = tela;
        this.precio = precio;
        this.marca = marca;
        this.esTela = esTela;
    }

}