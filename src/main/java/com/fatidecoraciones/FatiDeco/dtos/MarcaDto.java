package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.models.Marca;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class MarcaDto {

    private Long id;
    private String marca;
    private String nombre;
    private boolean esSistema;
    private Set<String> sistemas;

    public MarcaDto() {
    }

    public MarcaDto(Marca marca) {
        this.id = marca.getId();
        this.marca = marca.getMarca();
        this.nombre = marca.getNombre();
        this.esSistema = marca.isEsSistema();
        this.sistemas = marca.getSistemas().stream()
                .map(Sistema::getSistema)
                .collect(Collectors.toSet());
    }
}