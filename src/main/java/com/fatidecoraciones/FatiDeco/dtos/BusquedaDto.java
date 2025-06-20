package com.fatidecoraciones.FatiDeco.dtos;

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
    private String cliente;
    private String responsable;
    private String tela;
    private String sistema;
    private String viejo;
    private String comprado;
    private LocalDate fecha_tallerDesde;
    private LocalDate fecha_tallerHasta;
    private String nombre;
    private String art;
    private String marca;
    private String esTela;


    public BusquedaDto() {
    }

    public BusquedaDto(Long id, LocalDate fecha_pedidoDesde, LocalDate fecha_pedidoHasta, String provedor, String via,
                       String n_pedido, String n_factura, String n_remito, String llego, LocalDate fecha_llegada,
                       String estado, String cliente, String responsable, String tela, String sistema,
                       String viejo, String comprado, LocalDate fecha_tallerDesde, LocalDate fecha_tallerHasta,
                       String nombre, String art, String marca, String esTela) {
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
        this.cliente = cliente;
        this.responsable = responsable;
        this.tela = tela;
        this.sistema = sistema;
        this.viejo = viejo;
        this.comprado = comprado;
        this.fecha_tallerHasta = fecha_tallerDesde;
        this.fecha_tallerDesde = fecha_tallerHasta;
        this.nombre = nombre;
        this.art = art;
        this.marca = marca;
        this.esTela = esTela;
    }

}