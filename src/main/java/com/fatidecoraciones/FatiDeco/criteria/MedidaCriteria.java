package com.fatidecoraciones.FatiDeco.criteria;

import lombok.Data;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.StringFilter;

@Data
public class MedidaCriteria {

    private StringFilter clienteNombre;
    private BooleanFilter viejo;
    private BooleanFilter comprado;

    public MedidaCriteria() {
    }

    public MedidaCriteria(StringFilter clienteNombre, BooleanFilter viejo, BooleanFilter comprado) {
        this.clienteNombre = clienteNombre;
        this.viejo = viejo;
        this.comprado = comprado;
    }
}