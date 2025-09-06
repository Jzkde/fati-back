package com.fatidecoraciones.FatiDeco.models;

import com.fatidecoraciones.FatiDeco.dB.enums.Estado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String clienteNombre;
    private String responsable;
    private String observaciones;

    public Pedido() {
    }

    public Pedido(LocalDate fecha_pedido, String provedor, String via, String n_pedido, String n_factura,
                  String n_remito, double monto, boolean llego, Estado estado, String clienteNombre,
                  String responsable, String observaciones) {

        this.fecha_pedido = fecha_pedido;
        this.provedor = provedor;
        this.via = via;
        this.n_pedido = n_pedido;
        this.n_factura = n_factura;
        this.n_remito = n_remito;
        this.monto = monto;
        this.llego = llego;
        this.estado = estado;
        this.clienteNombre = clienteNombre;
        this.responsable = responsable;
        this.observaciones = observaciones;
    }
}