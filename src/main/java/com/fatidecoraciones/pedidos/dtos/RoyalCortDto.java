package com.fatidecoraciones.pedidos.dtos;

import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.RoyalCort;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class RoyalCortDto {
    private Long id;
    private String tela;
    private Double precio;
    private boolean esTela;
    @Enumerated(EnumType.STRING)
    private Sistema sistema;

    public RoyalCortDto() {
    }

    public RoyalCortDto(RoyalCort royalCort) {
        this.id = royalCort.getId();
        this.tela = royalCort.getTela();
        this.precio = royalCort.getPrecio();
        this.esTela = royalCort.isEsTela();
        this.sistema = royalCort.getSistema();
    }
}