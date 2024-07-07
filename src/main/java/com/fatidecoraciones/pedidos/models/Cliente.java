package com.fatidecoraciones.pedidos.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Pedido> pedidos = new HashSet<>();
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Presupuesto> presupuestos = new HashSet<>();

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String direccion, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public void addPedidos(Pedido pedido) {
        pedido.setCliente(this);
        pedidos.add(pedido);
        pedido.setClienteNombre(this.nombre + " " + this.apellido);
    }

    public void addPresupuesto(Presupuesto presupuesto) {
        presupuesto.setCliente(this);
        presupuestos.add(presupuesto);
        presupuesto.setClienteNombre(this.nombre + " " + this.apellido);
    }

}
