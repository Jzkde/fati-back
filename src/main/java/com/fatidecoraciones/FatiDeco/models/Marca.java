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
    private String nombre;
    private boolean esSistema;

    @OneToMany(mappedBy = "marca", fetch = FetchType.EAGER)
    private Set<Producto> productos = new HashSet<>();

    @OneToMany(mappedBy = "marca", fetch = FetchType.EAGER)
    private Set<CortEspeciales> cortEspeciales = new HashSet<>();

    @OneToMany(mappedBy = "marca", fetch = FetchType.EAGER)
    private Set<Servicio> servicios = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "marca_sistema", joinColumns = @JoinColumn(name = "marca_id"), inverseJoinColumns = @JoinColumn(name = "sistema_id"))
    private Set<Sistema> sistemas = new HashSet<>();

    public Marca() {
    }

    public Marca(String marca, String nombre, boolean esSistema) {
        this.marca = marca;
        this.nombre = nombre;
        this.esSistema = esSistema;
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

    public void addSistema(Sistema sistema) {
        sistema.getMarcas().add(this);
        this.sistemas.add(sistema);
    }

    public void removeSistema(Sistema sistema) {
        sistema.getMarcas().remove(this);
        this.sistemas.remove(sistema);
    }
}