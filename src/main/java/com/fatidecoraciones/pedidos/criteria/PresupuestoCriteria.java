package com.fatidecoraciones.pedidos.criteria;

import lombok.Data;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.StringFilter;

@Data
public class PresupuestoCriteria {
    private StringFilter clienteNombre;
    private BooleanFilter viejo;
    private BooleanFilter comprado;

    public PresupuestoCriteria() {
    }

    public PresupuestoCriteria(StringFilter clienteNombre, BooleanFilter viejo, BooleanFilter comprado) {
        this.clienteNombre = clienteNombre;
        this.viejo = viejo;
        this.comprado = comprado;
    }
}
