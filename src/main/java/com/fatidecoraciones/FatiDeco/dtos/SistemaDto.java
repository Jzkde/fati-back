package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.models.Marca;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter

public class SistemaDto {
    private Long id;
    private boolean esSistema;
    private String sistema;
    private Set<String> marcas;

    public SistemaDto() {
    }

    public SistemaDto(Sistema sistema) {
        this.id = sistema.getId();
        this.esSistema = sistema.isEsSistema();
        this.sistema = sistema.getSistema();
        this.marcas = sistema.getMarcas().stream()
                .map(Marca::getMarca)
                .collect(Collectors.toSet());
    }
}