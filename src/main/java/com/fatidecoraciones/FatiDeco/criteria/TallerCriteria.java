package com.fatidecoraciones.FatiDeco.criteria;

import com.fatidecoraciones.FatiDeco.enums.Estado;
import lombok.Getter;
import lombok.Setter;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.StringFilter;

@Getter
@Setter
public class TallerCriteria {

    private StringFilter cliente;
    private LocalDateFilter fecha_pedido;
    private LocalDateFilter fecha_entrega;
    private BooleanFilter llego;
    private EstadoFilter estado;

    public TallerCriteria() {
    }

    public TallerCriteria(StringFilter cliente, LocalDateFilter fecha_pedido, LocalDateFilter fecha_entrega,
                          BooleanFilter llego, EstadoFilter estado) {
        this.cliente = cliente;
        this.fecha_pedido = fecha_pedido;
        this.fecha_entrega = fecha_entrega;
        this.llego = llego;
        this.estado = estado;
    }


    public static class EstadoFilter extends Filter<Estado> {
    }
}