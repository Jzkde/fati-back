package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.dB.enums.Serv;
import com.fatidecoraciones.FatiDeco.models.Servicio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicioDto {

    private Long id;
    private String nombre;
    private Serv tipo;
    private double precio;

    public ServicioDto() {
    }

    public ServicioDto(Servicio servicio) {
        this.id = servicio.getId();
        this.nombre = servicio.getNombre();
        this.tipo = servicio.getTipo();
        this.precio = servicio.getPrecio();
    }
}