package com.fatidecoraciones.pedidos.models;

import com.fatidecoraciones.pedidos.enums.Apertura;
import com.fatidecoraciones.pedidos.enums.Comando;
import com.fatidecoraciones.pedidos.enums.Sistema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Presupuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Sistema sistema;
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
    public Presupuesto() {
    }

    public Presupuesto(Sistema sistema, int ancho, int alto, Comando comando, Apertura apertura, String accesorios, String ambiente, String observaciones, String clienteNombre, LocalDate fecha, boolean viejo, boolean comprado) {
        this.sistema = sistema;
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
        this. comprado = comprado;
    }
}
