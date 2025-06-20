package com.fatidecoraciones.FatiDeco.dtos;

import com.fatidecoraciones.FatiDeco.models.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;

    public ClienteDto() {
    }

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nombre = cliente.getNombre();
        this.direccion = cliente.getDireccion();
        this.telefono = cliente.getTelefono();
    }
}
