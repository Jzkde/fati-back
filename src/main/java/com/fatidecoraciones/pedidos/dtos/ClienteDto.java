package com.fatidecoraciones.pedidos.dtos;

import com.fatidecoraciones.pedidos.models.Cliente;
import com.fatidecoraciones.pedidos.models.Pedido;
import com.fatidecoraciones.pedidos.models.Presupuesto;
import com.fatidecoraciones.pedidos.repositories.ClienteRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Getter @Setter
public class ClienteDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private Set<Pedido> pedidos;
    private Set<Presupuesto> presupuestos;

    public ClienteDto() {
    }

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.direccion = cliente.getDireccion();
        this.telefono = cliente.getTelefono();
        this.pedidos = cliente.getPedidos();
        this.presupuestos = cliente.getPresupuestos();
    }
    public Set<PedidoDto> getPedidos() {
        return pedidos.stream().map(pedidos -> new PedidoDto(pedidos)).collect(toSet());
    }
    public Set<PresupuestoDto> getPresupuestos() {
        return presupuestos.stream().map(presupuesto ->  new PresupuestoDto(presupuesto)).collect(toSet());
    }


}
