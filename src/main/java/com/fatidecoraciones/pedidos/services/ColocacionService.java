package com.fatidecoraciones.pedidos.services;

import com.fatidecoraciones.pedidos.dtos.ColocacionDto;
import com.fatidecoraciones.pedidos.models.Colocacion;
import com.fatidecoraciones.pedidos.repositories.ColocacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColocacionService {

    @Autowired
    ColocacionRepository colocacionRepository;

    public boolean existById(Long id) {
        return colocacionRepository.existsById(id);
    }

    public Colocacion getColoc(Long id) {
        return colocacionRepository.findById(id).orElse(null);
    }

    public List<Colocacion> getColocs() {
        return colocacionRepository.findAll();
    }

    public ColocacionDto getColocDto(Long id) {
        return new ColocacionDto(this.getColoc(id));
    }

    public List<ColocacionDto> getColocsDto() {
        return colocacionRepository.findAll().stream().map(ColocacionDto::new).collect(Collectors.toList());
    }

    public void save(Colocacion colocacion) {
        colocacionRepository.save(colocacion);
    }

    public void delete(Long id) {
        colocacionRepository.deleteById(id);
    }

    public void incrementarPrecios(double porcentaje) {
        double factorIncremento = 1 + porcentaje / 100.0;
        colocacionRepository.actualizarPrecios(factorIncremento);
    }

    public Colocacion getTipo(String tipo) {
        return colocacionRepository.findByTipo(tipo);
    }

    public Colocacion saveVarios(Colocacion colocacion) {
        return colocacionRepository.save(colocacion);
    }
}
