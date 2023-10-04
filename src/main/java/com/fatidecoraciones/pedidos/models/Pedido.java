package com.fatidecoraciones.pedidos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatidecoraciones.pedidos.enums.Estado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

    @Entity
    @Getter @Setter
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
        @JsonIgnore
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name="cliente_id")
        private Cliente cliente;

        public Pedido() {
        }

        public Pedido( String provedor, String via, String n_pedido, String n_factura, String n_remito, double monto, String responsable, String observaciones) {
            this.fecha_pedido = LocalDate.now();
            this.provedor = provedor;
            this.via = via;
            this.n_pedido = n_pedido;
            this.n_factura = n_factura;
            this.n_remito = n_remito;
            this.monto = monto;
            this.llego = false;
            this.estado = Estado.PEDIDO;
            this.responsable = responsable;
            this.observaciones = observaciones;
        }

    }
