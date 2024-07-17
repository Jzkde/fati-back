package com.fatidecoraciones.pedidos.criteria;

import com.fatidecoraciones.pedidos.enums.Sistema;
import lombok.Data;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

@Data
public class RoyalCortCriteria {
    private LongFilter id;
    private StringFilter tela;
    private DoubleFilter precio;
    private SistemaFilter sistema;
    public RoyalCortCriteria() {
    }

    public RoyalCortCriteria(LongFilter id, StringFilter tela, DoubleFilter precio, SistemaFilter sistema) {
        this.id = id;
        this.tela = tela;
        this.precio = precio;
        this.sistema = sistema;
    }

    public static class SistemaFilter extends Filter<Sistema> {
    }
}