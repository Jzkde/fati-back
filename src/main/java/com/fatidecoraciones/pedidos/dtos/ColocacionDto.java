package com.fatidecoraciones.pedidos.dtos;

import com.fatidecoraciones.pedidos.models.Colocacion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColocacionDto {
    private Long id;
    private String tipo;
    private Double precio;

    public ColocacionDto() {
    }

    public ColocacionDto(Colocacion colocacion) {
        this.id = colocacion.getId();
        this.tipo = colocacion.getTipo();
        this.precio = colocacion.getPrecio();
    }
}
