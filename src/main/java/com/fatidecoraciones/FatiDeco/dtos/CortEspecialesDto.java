package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.models.CortEspeciales;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class CortEspecialesDto {

    private Long id;
    private String tela;
    private Double precio;
    private boolean esTela;
    private boolean esAdicional;
    private String marca;
    private String sistema;
    private List <RangoDto> rangos;


    public CortEspecialesDto() {
    }

    public CortEspecialesDto(CortEspeciales cortEspeciales) {
        this.id = cortEspeciales.getId();
        this.tela = cortEspeciales.getTela();
        this.precio = cortEspeciales.getPrecio();
        this.esTela = cortEspeciales.isEsTela();
        this.esAdicional = cortEspeciales.isEsAdicional();
        this.marca = cortEspeciales.getMarca() != null ? cortEspeciales.getMarca().getMarca() : null;
        this.sistema = cortEspeciales.getSistema() != null ? cortEspeciales.getSistema().getSistema() : null;
        this.rangos = cortEspeciales.getRangos().stream()
                .map(RangoDto::new)
                .sorted(Comparator.comparingInt(RangoDto::getAnchoMin))
                .collect(Collectors.toList());

    }
}