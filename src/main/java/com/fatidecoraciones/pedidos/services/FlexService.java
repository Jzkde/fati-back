package com.fatidecoraciones.pedidos.services;

import com.fatidecoraciones.pedidos.criteria.FlexCriteria;
import com.fatidecoraciones.pedidos.dtos.FlexDto;
import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.Flex;
import com.fatidecoraciones.pedidos.models.Flex_;
import com.fatidecoraciones.pedidos.repositories.FlexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlexService extends QueryService<Flex> {

    @Autowired
    private FlexRepository flexRepository;

    public boolean existById(Long id) {
        return flexRepository.existsById(id);
    }

    public Flex getFlex(Long id) {
        return flexRepository.findById(id).orElse(null);
    }

    public List<Flex> getFlexs() {
        return flexRepository.findAll();
    }

    public FlexDto getFlexDto(Long id) {
        return new FlexDto(this.getFlex(id));
    }

    public List<FlexDto> getFlexsDto() {
        return flexRepository.findAll().stream().map(FlexDto::new).collect(Collectors.toList());
    }

    public void save(Flex flex) {
        flexRepository.save(flex);
    }

    public Flex saveVarios(Flex flex) {
        return flexRepository.save(flex);
    }

    public void delete(Long id) {
        flexRepository.deleteById(id);
    }

    public void incrementarPrecios(double porcentaje) {
        double factorIncremento = 1 + porcentaje / 100.0;
        flexRepository.actualizarPrecios(factorIncremento);
    }

    public Flex findByTela(String tela) {
        return flexRepository.findByTela(tela);
    }

    public List<Flex> getFlexBySistema(Sistema sistema) {
        return flexRepository.findBySistema(sistema);
    }

    public List<FlexDto> getSistemas(Sistema sistema) {
        List<Flex> flexList = flexRepository.findAll();
        return flexList.stream()
                .filter(flex -> !flex.isEsTela() && flex.getSistema() == sistema)
                .sorted(Comparator.comparing(Flex::getTela))
                .map(FlexDto::new)
                .collect(Collectors.toList());
    }

    public List<FlexDto> getTelas(Sistema sistema) {
        List<Flex> flexList = flexRepository.findAll();
        return flexList.stream()
                .filter(flex -> flex.isEsTela() && flex.getSistema() == sistema)
                .sorted(Comparator.comparing(Flex::getTela))
                .map(FlexDto::new)
                .collect(Collectors.toList());
    }

    public List<Flex> findByCriteria(FlexCriteria flexCriteria) {
        final Specification<Flex> specification = createSpecification(flexCriteria);
        return flexRepository.findAll(specification);
    }

    private Specification<Flex> createSpecification(FlexCriteria flexCriteria) {
        Specification<Flex> specification = Specification.where(null);
        if (flexCriteria != null) {
            if (flexCriteria.getTela() != null) {
                specification = specification.and(buildStringSpecification(flexCriteria.getTela(), Flex_.tela));
            }
            if (flexCriteria.getSistema() != null) {
                specification = specification.and(buildSpecification(flexCriteria.getSistema(), Flex_.sistema));
            }
        }
        return specification;
    }
}
