package com.fatidecoraciones.FatiDeco.criteria;

import lombok.Data;
import tech.jhipster.service.filter.StringFilter;

@Data
public class ClienteCriteria {
    private StringFilter nombre;

    public ClienteCriteria() {
    }

    public ClienteCriteria(StringFilter nombre) {
        this.nombre = nombre;
    }
}
