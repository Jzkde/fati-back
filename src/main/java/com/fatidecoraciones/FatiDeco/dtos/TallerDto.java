package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.enums.Apertura;
import com.fatidecoraciones.FatiDeco.enums.Estado;
import com.fatidecoraciones.FatiDeco.models.Taller;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TallerDto {

    private Long id;
    private int ancho;
    private int alto;
    private Apertura apertura;
    private String accesorios;
    private String ambiente;
    private String observaciones;
    private LocalDate fecha_pedido;
    private LocalDate fecha_entrega;
    private Estado estado;
    private Boolean llego;
    private String cliente;

    public TallerDto() {
    }

    public TallerDto(Taller taller) {
        this.id = taller.getId();
        this.ancho = taller.getAncho();
        this.alto = taller.getAlto();
        this.apertura = taller.getApertura();
        this.accesorios = taller.getAccesorios();
        this.ambiente = taller.getAmbiente();
        this.observaciones = taller.getObservaciones();
        this.fecha_pedido = taller.getFecha_pedido();
        this.fecha_entrega = taller.getFecha_entrega();
        this.llego = taller.getLlego();
        this.cliente = taller.getCliente() != null ? taller.getCliente().getNombre() : "Desconocido";
    }
}