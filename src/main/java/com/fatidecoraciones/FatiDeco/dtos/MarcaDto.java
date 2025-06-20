package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.models.Marca;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarcaDto {

    private Long id;
    private String marca;

    public MarcaDto() {
    }

    public MarcaDto(Marca marca) {
        this.id = marca.getId();
        this.marca = marca.getMarca();
    }
}