package com.fatidecoraciones.FatiDeco.services;

import com.fatidecoraciones.FatiDeco.dtos.ClienteDto;
import com.fatidecoraciones.FatiDeco.dtos.RangoDto;
import com.fatidecoraciones.FatiDeco.models.Rango;
import com.fatidecoraciones.FatiDeco.repositories.RangoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RangoService {

    private final RangoRepository rangoRepository;

    public RangoService(RangoRepository rangoRepository) {
        this.rangoRepository = rangoRepository;
    }

    public List<RangoDto> getRangosDto() {
        return rangoRepository.findAll().stream().map(RangoDto::new).collect(Collectors.toList());
    }

    public RangoDto obtenerRangoDto(int alto, int ancho, String marca, String sistema) {
        List<Rango> rangos = rangoRepository
                .findByCortEspeciales_Marca_MarcaIgnoreCaseAndCortEspeciales_Sistema_SistemaIgnoreCase(marca, sistema);

        return rangos.stream()
                .filter(r -> ancho >= r.getAnchoMin() && ancho <= r.getAnchoMax()
                        && alto >= r.getAltoMin() && alto <= r.getAltoMax())
                .findFirst()
                .map(RangoDto::new)
                .orElse(null);
    }

    public Rango obtenerRango(int alto, int ancho, String marca, String sistema) {
        List<Rango> rangos = rangoRepository
                .findByCortEspeciales_Marca_MarcaIgnoreCaseAndCortEspeciales_Sistema_SistemaIgnoreCase(marca, sistema);

        return rangos.stream()
                .filter(r -> ancho >= r.getAnchoMin() && ancho <= r.getAnchoMax()
                        && alto >= r.getAltoMin() && alto <= r.getAltoMax())
                .findFirst()
                .orElse(null);
    }


    public List<RangoDto> rangoXMarcaYSistema(String marca, String sistema) {
        List<Rango> rangos = rangoRepository.findByCortEspeciales_Marca_MarcaIgnoreCaseAndCortEspeciales_Sistema_SistemaIgnoreCase(
                marca, sistema);

        return rangos.stream().map(RangoDto::new).collect(Collectors.toList());
    }

    public void save(Rango rango) {
        rangoRepository.save(rango);
    }

    public Rango getRango(Long id) {
        return rangoRepository.findById(id).orElse(null);
    }

    public boolean existsById(Long id) {
        return rangoRepository.existsById(id);
    }

    public void delete(Long id) {
        rangoRepository.deleteById(id);
    }
}
