package com.fatidecoraciones.pedidos.criteria;

import lombok.Data;
import tech.jhipster.service.filter.StringFilter;

@Data
public class PresupuestoCriteria {
    private StringFilter clienteNombre;

    public PresupuestoCriteria() {
    }

    public PresupuestoCriteria(StringFilter clienteNombre) {
        this.clienteNombre = clienteNombre;
    }
}
