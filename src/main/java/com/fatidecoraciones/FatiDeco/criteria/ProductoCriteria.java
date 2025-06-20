package com.fatidecoraciones.FatiDeco.criteria;

import lombok.Data;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

@Data
public class ProductoCriteria {

    private LongFilter id;
    private StringFilter art;
    private StringFilter nombre;
    private DoubleFilter precio;

    public ProductoCriteria() {
    }

    public ProductoCriteria(LongFilter id, StringFilter art, StringFilter nombre, DoubleFilter precio) {
        this.id = id;
        this.art = art;
        this.nombre = nombre;
        this.precio = precio;
    }
}