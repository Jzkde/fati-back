package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.models.Sistema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SistemaDto {
    private Long id;
    private String sistema;

    public SistemaDto() {
    }

    public SistemaDto(Sistema sistema) {
        this.id = sistema.getId();
        this.sistema = sistema.getSistema();
    }
}