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

    @OneToMany(mappedBy = "sistema", fetch = FetchType.EAGER)
    private Set<CortEspeciales> cortEspeciales = new HashSet<>();

    @OneToMany(mappedBy = "sistema", fetch = FetchType.EAGER)
    private Set<Medida> medidas = new HashSet<>();

    public Sistema() {
    }

    public Sistema(String sistema) {
        this.sistema = sistema;
    }

    public void addCortEspeciales(CortEspeciales cortEspeciales) {
        cortEspeciales.setSistema(this);
        this.cortEspeciales.add(cortEspeciales);
    }

    public void addMedidas(Medida medida) {
        medida.setSistema(this);
        this.medidas.add(medida);
    }
}
