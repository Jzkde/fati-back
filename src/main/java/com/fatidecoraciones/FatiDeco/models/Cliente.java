package com.fatidecoraciones.FatiDeco.models;

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
    private String direccion;
    private String telefono;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Medida> medidas = new HashSet<>();

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Taller> talleres = new HashSet<>();

    public Cliente() {
    }

    public Cliente(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public void addMedida(Medida medida) {
        medida.setCliente(this);
        medidas.add(medida);
    }

    public void addTaller(Taller taller) {
        taller.setCliente(this);
        talleres.add(taller);
    }

}
