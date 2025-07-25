package com.fatidecoraciones.FatiDeco.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultadoDto {
    private List<String> errores = new ArrayList<>();
    private int cantidadCargada;
}
