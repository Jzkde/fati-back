package com.fatidecoraciones.pedidos.dtos;

import com.fatidecoraciones.pedidos.enums.Apertura;
import com.fatidecoraciones.pedidos.enums.Comando;
import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.Presupuesto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
public class PresupuestoDto {

    private Long id;
    private Sistema sistema;
    private int ancho;
    private int alto;
    private Comando comando;
    private Apertura apertura;
    private String accesorios;
    private String ambiente;
    private String observaciones;

    public PresupuestoDto() {
    }

    public PresupuestoDto(Presupuesto presupuesto) {
        this.id = presupuesto.getId();
        this.sistema =presupuesto.getSistema();
        this.ancho = presupuesto.getAncho();
        this.alto = presupuesto.getAlto();
        this.comando = presupuesto.getComando();
        this.apertura = presupuesto.getApertura();
        this.accesorios = presupuesto.getAccesorios();
        this.ambiente = presupuesto.getAmbiente();
        this.observaciones = presupuesto.getObservaciones();
    }
}
