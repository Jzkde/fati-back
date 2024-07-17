package com.fatidecoraciones.pedidos.services;

import com.fatidecoraciones.pedidos.criteria.RoyalCortCriteria;
import com.fatidecoraciones.pedidos.dtos.RoyalCortDto;
import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.RoyalCort;
import com.fatidecoraciones.pedidos.models.RoyalCort_;
import com.fatidecoraciones.pedidos.repositories.RoyalCortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoyalCortService extends QueryService<RoyalCort> {

    @Autowired
    RoyalCortRepository royalCortRepository;

    public boolean existById(Long id) {
        return royalCortRepository.existsById(id);
    }

    public RoyalCort getRC(Long id) {
        return royalCortRepository.findById(id).orElse(null);
    }

    public void save(RoyalCort royalCort) {
        royalCortRepository.save(royalCort);
    }

    public RoyalCort saveVarios(RoyalCort royalCort) {
        return royalCortRepository.save(royalCort);
    }

    public List<RoyalCortDto> getRCsDto() {
        return royalCortRepository.findAll().stream().map(RoyalCortDto::new).collect(Collectors.toList());
    }

    public List<RoyalCort> getRC() {
        return royalCortRepository.findAll();
    }

    public RoyalCortDto getRCDto(Long id) {
        return new RoyalCortDto(this.getRC(id));
    }

    public void delete(Long id) {
        royalCortRepository.deleteById(id);
    }

    public void incrementarPrecios(double porcentaje) {
        double factorIncremento = 1 + porcentaje / 100.0;
        royalCortRepository.actualizarPrecios(factorIncremento);
    }

    public RoyalCort findByTela(String tela) {
        return royalCortRepository.findByTela(tela);
    }

    public List<RoyalCortDto> getSistemas(Sistema sistema) {
        List<RoyalCort> flexList = royalCortRepository.findAll();
        return flexList.stream()
                .filter(royalCort -> !royalCort.isEsTela() && royalCort.getSistema() == sistema)
                .sorted(Comparator.comparing(RoyalCort::getTela))
                .map(RoyalCortDto::new)
                .collect(Collectors.toList());
    }

    public List<RoyalCortDto> getTelas(Sistema sistema) {
        List<RoyalCort> flexList = royalCortRepository.findAll();
        return flexList.stream()
                .filter(royalCort -> royalCort.isEsTela() && royalCort.getSistema() == sistema)
                .sorted(Comparator.comparing(RoyalCort::getTela))
                .map(RoyalCortDto::new)
                .collect(Collectors.toList());
    }

    public List<RoyalCort> findByCriteria(RoyalCortCriteria royalCortCriteria) {
        final Specification<RoyalCort> specification = createSpecification(royalCortCriteria);
        return royalCortRepository.findAll(specification);
    }

    private Specification<RoyalCort> createSpecification(RoyalCortCriteria royalCortCriteria) {
        Specification<RoyalCort> specification = Specification.where(null);
        if (royalCortCriteria != null) {
            if (royalCortCriteria.getTela() != null) {
                specification = specification.and(buildStringSpecification(royalCortCriteria.getTela(), RoyalCort_.tela));
            }
            if (royalCortCriteria.getSistema() != null) {
                specification = specification.and(buildSpecification(royalCortCriteria.getSistema(), RoyalCort_.sistema));
            }
        }
        return specification;
    }
}
