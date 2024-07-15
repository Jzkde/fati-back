package com.fatidecoraciones.pedidos.criteria;

import lombok.Data;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

@Data
public class RoyalCortCriteria {
    private LongFilter id;
    private StringFilter tela;
    private DoubleFilter precio;

    public RoyalCortCriteria() {
    }

    public RoyalCortCriteria(LongFilter id, StringFilter tela, DoubleFilter precio) {
        this.id = id;
        this.tela = tela;
        this.precio = precio;
    }
}