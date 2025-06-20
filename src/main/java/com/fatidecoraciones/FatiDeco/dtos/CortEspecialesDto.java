package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.models.CortEspeciales;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CortEspecialesDto {

    private Long id;
    private String tela;
    private Double precio;
    private boolean esTela;
    private String marca;
    private String sistema;


    public CortEspecialesDto() {
    }

    public CortEspecialesDto(CortEspeciales cortEspeciales) {
        this.id = cortEspeciales.getId();
        this.tela = cortEspeciales.getTela();
        this.precio = cortEspeciales.getPrecio();
        this.esTela = cortEspeciales.isEsTela();
        this.marca = cortEspeciales.getMarca() != null ? cortEspeciales.getMarca().getMarca() : null;
        this.sistema = cortEspeciales.getSistema() != null ? cortEspeciales.getSistema().getSistema() : null;
    }
}