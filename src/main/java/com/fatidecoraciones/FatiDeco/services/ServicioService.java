package com.fatidecoraciones.FatiDeco.services;

import com.fatidecoraciones.FatiDeco.dtos.ServicioDto;
import com.fatidecoraciones.FatiDeco.dB.enums.Serv;
import com.fatidecoraciones.FatiDeco.models.Servicio;
import com.fatidecoraciones.FatiDeco.repositories.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public boolean existsById(Long id) {
        return servicioRepository.existsById(id);
    }

    public Servicio getColoc(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    public List<Servicio> getColocs() {
        return servicioRepository.findAll();
    }

    public ServicioDto getColocDto(Long id) {
        return new ServicioDto(this.getColoc(id));
    }

    public List<ServicioDto> getColocsDto() {
        return servicioRepository.findAll().stream().map(ServicioDto::new).collect(Collectors.toList());
    }

    public List<ServicioDto> getColocsFiltradoDto(String tipo) {
        try {
            Serv tipoEnum = Serv.valueOf(tipo.toUpperCase());
            return servicioRepository.findByTipo(tipoEnum).stream()
                    .map(ServicioDto::new)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipo inválido: " + tipo, e);
        }
    }


    public void save(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    public void delete(Long id) {
        servicioRepository.deleteById(id);
    }

    public void incrementarPrecios(double porcentaje) {
        double factorIncremento = 1 + porcentaje / 100.0;
        servicioRepository.actualizarPrecios(factorIncremento);
    }

    public Servicio findByNombre(String tipo) {
        return servicioRepository.findByNombreIgnoreCase(tipo);
    }

    public Servicio saveVarios(Servicio servicio) {
        return servicioRepository.save(servicio);
    }
}