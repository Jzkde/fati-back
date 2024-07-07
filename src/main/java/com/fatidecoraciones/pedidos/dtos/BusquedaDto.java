package com.fatidecoraciones.pedidos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BusquedaDto {

    private Long id;
    private LocalDate fecha_pedidoDesde;
    private LocalDate fecha_pedidoHasta;
    private String provedor;
    private String via;
    private String n_pedido;
    private String n_factura;
    private String n_remito;
    private String llego;
    private LocalDate fecha_llegada;
    private String estado;
    private String clienteNombre;
    private String responsable;
    private String tela;

    public BusquedaDto() {
    }

    public BusquedaDto(Long id, LocalDate fecha_pedidoDesde, LocalDate fecha_pedidoHasta, String provedor, String via,
                       String n_pedido, String n_factura, String n_remito, String llego, LocalDate fecha_llegada,
                       String estado, String clienteNombre, String responsable, String tela) {
        this.id = id;
        this.fecha_pedidoDesde = fecha_pedidoDesde;
        this.fecha_pedidoHasta = fecha_pedidoHasta;
        this.provedor = provedor;
        this.via = via;
        this.n_pedido = n_pedido;
        this.n_factura = n_factura;
        this.n_remito = n_remito;
        this.llego = llego;
        this.fecha_llegada = fecha_llegada;
        this.estado = estado;
        this.clienteNombre = clienteNombre;
        this.responsable = responsable;
        this.tela = tela;
    }

}
