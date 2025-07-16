package com.fatidecoraciones.FatiDeco.services;

import com.fatidecoraciones.FatiDeco.dtos.MarcaDto;
import com.fatidecoraciones.FatiDeco.dtos.SistemaDto;
import com.fatidecoraciones.FatiDeco.models.Marca;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import com.fatidecoraciones.FatiDeco.repositories.SistemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SistemaService {

    private final SistemaRepository sistemaRepository;

    public SistemaService(SistemaRepository sistemaRepository) {
        this.sistemaRepository = sistemaRepository;
    }

    public Sistema findBySistema(String sistema) {
        return sistemaRepository.findBySistemaIgnoreCase(sistema);
    }

    public SistemaDto findBySistemaDto(String sistemaN) {
        Sistema sistema = sistemaRepository.findBySistemaIgnoreCase(sistemaN);
        if (sistema != null) {
            return new SistemaDto(sistema);
        } else {
            return null;
        }
    }

    public boolean existById(Long id) {
        return sistemaRepository.existsById(id);
    }

    public Sistema getSistema(Long id) {
        return sistemaRepository.findById(id).orElse(null);
    }

    public List<Sistema> getSistemas() {
        return sistemaRepository.findAll();
    }

    public SistemaDto getSistemaDto(Long id) {
        return new SistemaDto(this.getSistema(id));
    }

    public List<SistemaDto> getSistemasDto() {
        return sistemaRepository.findAllByOrderBySistemaAsc().stream().map(SistemaDto::new).collect(Collectors.toList());
    }

    public Sistema save(Sistema sistema) {
        return sistemaRepository.save(sistema);
    }

    public void saveAll(List<Sistema> sistemas) {
        List<Sistema> sistemasUnicos = sistemas.stream()
                .filter(sistema -> !sistemaRepository.existsBySistema(sistema.getSistema().trim()))
                .collect(Collectors.toList());

        sistemaRepository.saveAll(sistemasUnicos);
    }

    public void delete(Long id) {
        sistemaRepository.deleteById(id);
    }

    public Sistema findById(Long id) {
        return sistemaRepository.findById(id).orElse(null);
    }

    public boolean tieneProductos(Long clienteId) {
        Sistema sistema = sistemaRepository.findById(clienteId).orElse(null);
        return
                sistema != null && sistema.getMedidas() != null && !sistema.getMedidas().isEmpty() ||
                        sistema != null && sistema.getCortEspecialess() != null && !sistema.getCortEspecialess().isEmpty();
    }

    public List<SistemaDto> getSistemasPorMarca(String marca) {
        return sistemaRepository
                .findByMarcas_MarcaIgnoreCaseAndEsSistemaTrue(marca)
                .stream()
                .map(SistemaDto::new)
                .collect(Collectors.toList());
    }

    public List<SistemaDto> sistemasPorTipo(boolean esSistema) {
        return sistemaRepository.findByEsSistema(esSistema).stream().map(SistemaDto::new).collect(Collectors.toList());
    }

}
