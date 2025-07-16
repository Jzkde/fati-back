package com.fatidecoraciones.FatiDeco.services;

import com.fatidecoraciones.FatiDeco.criteria.CortEspecialesCriteria;
import com.fatidecoraciones.FatiDeco.dtos.CortEspecialesDto;
import com.fatidecoraciones.FatiDeco.models.*;
import com.fatidecoraciones.FatiDeco.repositories.CortEspecialesRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CortEspecialesService extends QueryService<CortEspeciales> {

    private final CortEspecialesRepository cortEspecialesRepository;

    public CortEspecialesService(CortEspecialesRepository cortEspecialesRepository) {
        this.cortEspecialesRepository = cortEspecialesRepository;
    }

    public List<CortEspecialesDto> listaXMarca(String marca) {
        return cortEspecialesRepository.findByMarca_MarcaIgnoreCase(marca)
                .stream()
                .map(CortEspecialesDto::new)
                .collect(Collectors.toList());
    }

    public boolean verificarMarca(String marca) {
        return (cortEspecialesRepository.existsByMarca_MarcaIgnoreCase(marca));
    }

    // Por uno
    public boolean existById(Long id) {
        return cortEspecialesRepository.existsById(id);
    }

    public CortEspeciales getCortEspecial(Long id) {
        return cortEspecialesRepository.findById(id).orElse(null);
    }
    public CortEspeciales getCortEspecialNombre(String nombre){
        return cortEspecialesRepository.findByTelaIgnoreCase(nombre);
    }

    public CortEspecialesDto unoDto(Long id) {
        return new CortEspecialesDto(this.getCortEspecial(id));
    }

    // Por Lista
    public List<CortEspeciales> getCortEspecialess() {
        return cortEspecialesRepository.findAll();
    }

    public List<CortEspecialesDto> getListaTotalDto() {
        return cortEspecialesRepository.findAll().stream().map(CortEspecialesDto::new).collect(Collectors.toList());
    }

    public void save(CortEspeciales cortEspeciales) {
        cortEspecialesRepository.save(cortEspeciales);
    }

    public void saveAll(List<CortEspeciales> cortEspeciales) {
        List<CortEspeciales> productosFiltrados = cortEspeciales.stream()
                .filter(cortEspecial -> !cortEspecialesRepository.existsByTelaIgnoreCase(cortEspecial.getTela()))
                .collect(Collectors.toList());

        cortEspecialesRepository.saveAll(productosFiltrados);
    }

    public void delete(Long id) {
        cortEspecialesRepository.deleteById(id);
    }

    public void incrementarPrecios(double porcentaje, Long marcaId) {
        double factorIncremento = 1 + porcentaje / 100.0;
        cortEspecialesRepository.actualizarPrecios(factorIncremento, marcaId);
    }

    public CortEspeciales findByTela(String tela) {
        return cortEspecialesRepository.findByTelaIgnoreCase(tela);
    }

    public Optional<CortEspeciales> getSistema(String sistema) {
        return cortEspecialesRepository.findByEsTelaAndSistema_SistemaIgnoreCase(false, sistema);
    }

    public boolean findByTelaAndSistemaAndMarca(String tela, String sistema, String marca) {
        return cortEspecialesRepository.existsByTelaIgnoreCaseAndSistema_SistemaIgnoreCaseAndMarca_MarcaIgnoreCase(tela, sistema, marca);
    }

    public CortEspeciales findByTelaAndSistema(String sistema, String tela) {
        return cortEspecialesRepository.findByTelaAndSistema_SistemaIgnoreCase(tela, sistema).orElse(null);
    }

    public List<CortEspecialesDto> getMecanismoXMarcaXSistema(String sistema, String marca, boolean adicional) {
        List<CortEspeciales> cortEspecialesList = cortEspecialesRepository.findByEsAdicionalAndMarca_MarcaIgnoreCaseAndSistema_Sistema(adicional, marca, sistema);
        return cortEspecialesList.stream()
                .filter(cortEspeciales -> !cortEspeciales.isEsTela() && cortEspeciales.getSistema().getSistema().equalsIgnoreCase(sistema))
                .sorted(Comparator.comparing(CortEspeciales::getTela))
                .map(CortEspecialesDto::new)
                .collect(Collectors.toList());
    }

    public List<CortEspecialesDto> getTelasXMarcaXSistema(boolean adicional, String marca,String sistema) {
        List<CortEspeciales> cortEspecialesList = cortEspecialesRepository.findByEsAdicionalAndMarca_MarcaIgnoreCaseAndSistema_Sistema(adicional, marca, sistema);
        return cortEspecialesList.stream()
                .filter(cortEspeciales -> cortEspeciales.isEsTela() && cortEspeciales.getSistema().getSistema().equalsIgnoreCase(sistema))
                .sorted(Comparator.comparing(CortEspeciales::getTela))
                .map(CortEspecialesDto::new)
                .collect(Collectors.toList());
    }

    public List<CortEspeciales> findByCriteria(CortEspecialesCriteria cortEspecialesCriteria) {
        final Specification<CortEspeciales> specification = createSpecification(cortEspecialesCriteria);
        return cortEspecialesRepository.findAll(specification);
    }

    private Specification<CortEspeciales> createSpecification(CortEspecialesCriteria cortEspecialesCriteria) {
        Specification<CortEspeciales> specification = Specification.where(null);
        if (cortEspecialesCriteria != null) {
            if (cortEspecialesCriteria.getTela() != null) {
                specification = specification.and(buildStringSpecification(cortEspecialesCriteria.getTela(), CortEspeciales_.tela));
            }
            if (cortEspecialesCriteria.getSistema() != null) {
                specification = specification.and(buildReferringEntitySpecification(cortEspecialesCriteria.getSistema(), CortEspeciales_.sistema, Sistema_.sistema));
            }
            if (cortEspecialesCriteria.getMarca() != null) {
                specification = specification.and(buildReferringEntitySpecification(cortEspecialesCriteria.getMarca(), CortEspeciales_.marca, Marca_.marca));
            }
            if (cortEspecialesCriteria.getEsTela() != null) {
                specification = specification.and(buildSpecification(cortEspecialesCriteria.getEsTela(), CortEspeciales_.esTela));
            }
        }
        return specification;
    }
}