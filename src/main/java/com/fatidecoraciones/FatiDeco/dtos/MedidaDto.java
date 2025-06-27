package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.enums.Apertura;
import com.fatidecoraciones.FatiDeco.enums.Comando;
import com.fatidecoraciones.FatiDeco.models.Medida;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MedidaDto {

    private Long id;
    private int ancho;
    private int alto;
    private String comando;
    private String apertura;
    private String accesorios;
    private String ambiente;
    private String observaciones;
    private LocalDate fecha;
    private boolean viejo;
    private boolean comprado;
    private boolean caida;
    private String cliente;
    private String sistema;

    public MedidaDto() {
    }

    public MedidaDto(Medida medida) {
        this.id = medida.getId();
        this.ancho = medida.getAncho();
        this.alto = medida.getAlto();
        this.comando = medida.getComando().toString();
        this.apertura = medida.getApertura().toString();
        this.accesorios = medida.getAccesorios();
        this.ambiente = medida.getAmbiente();
        this.observaciones = medida.getObservaciones();
        this.fecha = medida.getFecha();
        this.viejo = medida.isViejo();
        this.comprado = medida.isComprado();
        this.caida = medida.isCaida();
        this.cliente = medida.getCliente() != null ? medida.getCliente().getNombre() : "Desconocido";
        this.sistema = medida.getSistema() != null ? medida.getSistema().getSistema() : null;
    }
}