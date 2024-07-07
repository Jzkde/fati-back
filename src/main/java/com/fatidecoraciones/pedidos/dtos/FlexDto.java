package com.fatidecoraciones.pedidos.dtos;

import com.fatidecoraciones.pedidos.models.Flex;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlexDto {
    private Long id;
    private String tela;
    private Double precio;

    public FlexDto() {
    }

    public FlexDto(Flex flex) {
        this.id = flex.getId();
        this.tela = flex.getTela();
        this.precio = flex.getPrecio();
    }
}
