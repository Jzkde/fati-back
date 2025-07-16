package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.models.Rango;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RangoDto {

    private Long id;
    private int altoMin;
    private int altoMax;
    private int anchoMin;
    private int anchoMax;
    private float sistemaMin;
    private float telaMin;


    public RangoDto() {
    }

    public RangoDto(Rango rango) {

        this.id = rango.getId();
        this.altoMin = rango.getAltoMin();
        this.altoMax = rango.getAltoMax();
        this.anchoMin = rango.getAnchoMin();
        this.anchoMax = rango.getAnchoMax();
        this.sistemaMin = rango.getSistemaMin();
        this.telaMin = rango.getTelaMin();


    }
}
