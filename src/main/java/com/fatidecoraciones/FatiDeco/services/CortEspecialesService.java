package com.fatidecoraciones.FatiDeco.services;

import com.fatidecoraciones.FatiDeco.criteria.CortEspecialesCriteria;
import com.fatidecoraciones.FatiDeco.dtos.CortEspecialesDto;
import com.fatidecoraciones.FatiDeco.models.CortEspeciales;
import com.fatidecoraciones.FatiDeco.models.CortEspeciales_;
import com.fatidecoraciones.FatiDeco.models.Marca_;
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

    public List<CortEspecialesDto> findByMarca(String marca) {
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

    public CortEspeciales getCortEspeciales(Long id) {
        return cortEspecialesRepository.findById(id).orElse(null);
    }

    public CortEspecialesDto getCortEspecialesDto(Long id) {
        return new CortEspecialesDto(this.getCortEspeciales(id));
    }

    // Por Lista
    public List<CortEspeciales> getCortEspecialess() {
        return cortEspecialesRepository.findAll();
    }

    public List<CortEspecialesDto> getCortEspecialessDto() {
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

    public CortEspeciales saveVarios(CortEspeciales cortEspeciales) {
        return cortEspecialesRepository.save(cortEspeciales);
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

    public CortEspeciales findByTelaAndSistemaAndMarca(String sistema, String tela, String marca) {
        return cortEspecialesRepository.findByTelaAndSistema_SistemaIgnoreCaseAndMarca_MarcaIgnoreCase(tela, sistema, marca).orElse(null);
    }

    public CortEspeciales findByTelaAndSistema(String sistema, String tela) {
        return cortEspecialesRepository.findByTelaAndSistema_SistemaIgnoreCase(tela, sistema).orElse(null);
    }

    public List<CortEspecialesDto> getSistemas(String sistema, String marca) {
        List<CortEspeciales> cortEspecialesList = cortEspecialesRepository.findByMarca_MarcaIgnoreCase(marca);
        return cortEspecialesList.stream()
                .filter(cortEspeciales -> !cortEspeciales.isEsTela() && cortEspeciales.getSistema().getSistema().equalsIgnoreCase(sistema))
                .sorted(Comparator.comparing(CortEspeciales::getTela))
                .map(CortEspecialesDto::new)
                .collect(Collectors.toList());
    }

    public List<CortEspecialesDto> getTelas(String sistema, String marca) {
        List<CortEspeciales> cortEspecialesList = cortEspecialesRepository.findByMarca_MarcaIgnoreCase(marca);
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