package com.fatidecoraciones.FatiDeco.models;

import com.fatidecoraciones.FatiDeco.dB.enums.Apertura;
import com.fatidecoraciones.FatiDeco.dB.enums.Estado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ancho;
    private int alto;
    @Enumerated(EnumType.STRING)
    private Apertura apertura;
    private String accesorios;
    private String ambiente;
    private String observaciones;
    private String clienteNombre;
    private LocalDate fecha_pedido;
    private LocalDate fecha_entrega;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private Boolean llego;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Taller() {
    }

    public Taller(int ancho, int alto, Apertura apertura, String accesorios, String ambiente, String observaciones,
                  String clienteNombre, LocalDate fecha_pedido, Estado estado, Boolean llego) {

        this.ancho = ancho;
        this.alto = alto;
        this.apertura = apertura;
        this.accesorios = accesorios;
        this.ambiente = ambiente;
        this.observaciones = observaciones;
        this.clienteNombre = clienteNombre;
        this.fecha_pedido = fecha_pedido;
        this.estado = estado;
        this.llego = llego;
    }
}