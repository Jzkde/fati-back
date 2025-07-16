package com.fatidecoraciones.FatiDeco.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Sistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sistema;
    private boolean esSistema;

    @OneToMany(mappedBy = "sistema", fetch = FetchType.EAGER)
    private Set<CortEspeciales> cortEspecialess = new HashSet<>();

    @OneToMany(mappedBy = "sistema", fetch = FetchType.EAGER)
    private Set<Medida> medidas = new HashSet<>();

    @ManyToMany(mappedBy = "sistemas")
    private Set<Marca> marcas = new HashSet<>();

    public Sistema() {
    }

    public Sistema(String sistema, boolean esSistema) {
        this.sistema = sistema;
        this.esSistema = esSistema;
    }

    public void addCortEspeciales(CortEspeciales cortEspeciales) {
        cortEspeciales.setSistema(this);
        this.cortEspecialess.add(cortEspeciales);
    }

    public void addMedidas(Medida medida) {
        medida.setSistema(this);
        this.medidas.add(medida);
    }

    public void addMarca(Marca marca) {
        marca.getSistemas().add(this);
        this.marcas.add(marca);
    }

    public void removeMarca(Marca marca) {
        this.marcas.remove(marca);
        marca.getSistemas().remove(this);
    }

}
