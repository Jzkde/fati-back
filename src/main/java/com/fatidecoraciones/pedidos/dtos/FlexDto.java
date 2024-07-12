package com.fatidecoraciones.pedidos.dtos;

import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.Flex;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class FlexDto {
    private Long id;
    private String tela;
    private Double precio;
    private boolean esTela;
    @Enumerated(EnumType.STRING)
    private Sistema sistema;

    public FlexDto() {
    }

    public FlexDto(Flex flex) {
        this.id = flex.getId();
        this.tela = flex.getTela();
        this.precio = flex.getPrecio();
        this.esTela = flex.isEsTela();
        this.sistema = flex.getSistema();
    }
}
