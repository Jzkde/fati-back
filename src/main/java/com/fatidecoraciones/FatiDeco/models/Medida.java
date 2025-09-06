package com.fatidecoraciones.FatiDeco.models;

import com.fatidecoraciones.FatiDeco.dB.enums.Apertura;
import com.fatidecoraciones.FatiDeco.dB.enums.Comando;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Medida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ancho;
    private int alto;
    @Enumerated(EnumType.STRING)
    private Comando comando;
    @Enumerated(EnumType.STRING)
    private Apertura apertura;
    private String accesorios;
    private String ambiente;
    private String observaciones;
    private String clienteNombre;
    private LocalDate fecha;
    private boolean viejo;
    private boolean comprado;
    private boolean caida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sistema_id")
    private Sistema sistema;

    public Medida() {
    }

    public Medida(int ancho, int alto, Comando comando, Apertura apertura, String accesorios,
                  String ambiente, String observaciones, String clienteNombre, LocalDate fecha, boolean viejo,
                  boolean comprado, boolean caida) {

        this.ancho = ancho;
        this.alto = alto;
        this.comando = comando;
        this.apertura = apertura;
        this.accesorios = accesorios;
        this.ambiente = ambiente;
        this.observaciones = observaciones;
        this.clienteNombre = clienteNombre;
        this.fecha = fecha;
        this.viejo = viejo;
        this.comprado = comprado;
        this.caida = caida;
    }
}