package com.fatidecoraciones.FatiDeco.services;

import com.fatidecoraciones.FatiDeco.criteria.TallerCriteria;
import com.fatidecoraciones.FatiDeco.dtos.TallerDto;
import com.fatidecoraciones.FatiDeco.models.Taller;
import com.fatidecoraciones.FatiDeco.models.Taller_;
import com.fatidecoraciones.FatiDeco.repositories.TallerRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TallerService extends QueryService<Taller> {

    private final TallerRepository tallerRepository;

    public TallerService(TallerRepository tallerRepository) {
        this.tallerRepository = tallerRepository;
    }

    public boolean existsById(Long id) {
        return tallerRepository.existsById(id);
    }

    public Taller getTaller(Long id) {
        return tallerRepository.findById(id).orElse(null);
    }

    public void save(Taller taller) {
        tallerRepository.save(taller);
    }

    public List<TallerDto> getTallerDtos() {
        return tallerRepository.findAll().stream().map(taller -> new TallerDto(taller)).collect(Collectors.toList());
    }

    public List<Taller> getTaller() {
        return tallerRepository.findAll();
    }

    public TallerDto getTallerDto(Long id) {
        return new TallerDto(this.getTaller(id));
    }

    public void delete(Long id) {
        tallerRepository.deleteById(id);
    }

    public List<Taller> findByCriteria(TallerCriteria tallerCriteria) {
        final Specification<Taller> specification = createSpecification(tallerCriteria);
        return tallerRepository.findAll(specification);
    }

    private Specification<Taller> createSpecification(TallerCriteria tallerCriteria) {
        Specification<Taller> specification = Specification.where(null);
        if (tallerCriteria != null) {
            if (tallerCriteria.getCliente() != null) {
                specification = specification.and(buildStringSpecification(tallerCriteria.getCliente(), Taller_.clienteNombre));
            }
            if (tallerCriteria.getFecha_pedido() != null) {
                specification = specification.and(buildRangeSpecification(tallerCriteria.getFecha_pedido(), Taller_.fecha_pedido));
            }
            if (tallerCriteria.getFecha_entrega() != null) {
                specification = specification.and(buildRangeSpecification(tallerCriteria.getFecha_entrega(), Taller_.fecha_pedido));
            }
            if (tallerCriteria.getLlego() != null) {
                specification = specification.and(buildSpecification(tallerCriteria.getLlego(), Taller_.llego));
            }
        }

        return specification;
    }

    public void saveAll(List<Taller> talleres) {
        tallerRepository.saveAll(talleres);
    }
}