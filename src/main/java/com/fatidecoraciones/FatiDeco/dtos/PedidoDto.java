package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.enums.Estado;
import com.fatidecoraciones.FatiDeco.models.Pedido;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PedidoDto {

    private Long id;
    private LocalDate fecha_pedido;
    private String provedor;
    private String via;
    private String n_pedido;
    private String n_factura;
    private String n_remito;
    private double monto;
    private boolean llego;
    private LocalDate fecha_llegada;
    private Estado estado;
    private String clienteNombre;
    private String responsable;
    private String observaciones;

    public PedidoDto() {
    }

    public PedidoDto(Pedido pedido) {
        this.id = pedido.getId();
        this.fecha_pedido = pedido.getFecha_pedido();
        this.provedor = pedido.getProvedor();
        this.via = pedido.getVia();
        this.n_pedido = pedido.getN_pedido();
        this.n_factura = pedido.getN_factura();
        this.n_remito = pedido.getN_remito();
        this.monto = pedido.getMonto();
        this.llego = pedido.isLlego();
        this.fecha_llegada = pedido.getFecha_llegada();
        this.estado = pedido.getEstado();
        this.clienteNombre = pedido.getClienteNombre();
        this.responsable = pedido.getResponsable();
        this.observaciones = pedido.getObservaciones();
    }
}