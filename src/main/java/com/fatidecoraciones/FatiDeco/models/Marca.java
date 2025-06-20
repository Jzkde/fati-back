package com.fatidecoraciones.FatiDeco.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;

    @OneToMany(mappedBy = "marca", fetch = FetchType.EAGER)
    private Set<Producto> productos = new HashSet<>();

    @OneToMany(mappedBy = "marca", fetch = FetchType.EAGER)
    private Set<CortEspeciales> cortEspeciales = new HashSet<>();

    @OneToMany(mappedBy = "marca", fetch = FetchType.EAGER)
    private Set<Servicio> servicios = new HashSet<>();

    public Marca() {
    }

    public Marca(String marca) {
        this.marca = marca;
    }

    public void addMarcaProd(Producto producto) {
        producto.setMarca(this);
        productos.add(producto);
    }

    public void addMarcaEsp(CortEspeciales cortEspeciales) {
        cortEspeciales.setMarca(this);
        this.cortEspeciales.add(cortEspeciales);
    }

    public void addMarcaServ(Servicio servicio) {
        servicio.setMarca(this);
        this.servicios.add(servicio);
    }
}