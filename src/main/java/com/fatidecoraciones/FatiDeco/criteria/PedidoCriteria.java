package com.fatidecoraciones.FatiDeco.criteria;

import com.fatidecoraciones.FatiDeco.enums.Estado;
import lombok.Data;
import tech.jhipster.service.filter.*;

@Data
public class PedidoCriteria {

    private LongFilter id;
    private LocalDateFilter fecha_pedido;
    private StringFilter provedor;
    private StringFilter via;
    private StringFilter n_pedido;
    private StringFilter n_factura;
    private StringFilter n_remito;
    private DoubleFilter monto;
    private BooleanFilter llego;
    private LocalDateFilter fecha_llegada;
    private EstadoFilter estado;
    private StringFilter clienteNombre;
    private StringFilter responsable;

    public PedidoCriteria() {
    }

    public PedidoCriteria(LongFilter id, LocalDateFilter fecha_pedido, StringFilter provedor, StringFilter via,
                          StringFilter n_pedido, StringFilter n_factura, StringFilter n_remito, DoubleFilter monto,
                          BooleanFilter llego, LocalDateFilter fecha_llegada, EstadoFilter estado,
                          StringFilter clienteNombre, StringFilter responsable) {
        this.id = id;
        this.fecha_pedido = fecha_pedido;
        this.provedor = provedor;
        this.via = via;
        this.n_pedido = n_pedido;
        this.n_factura = n_factura;
        this.n_remito = n_remito;
        this.monto = monto;
        this.llego = llego;
        this.fecha_llegada = fecha_llegada;
        this.estado = estado;
        this.clienteNombre = clienteNombre;
        this.responsable = responsable;
    }

    public static class EstadoFilter extends Filter<Estado> {
    }
}