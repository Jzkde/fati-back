package com.fatidecoraciones.pedidos.services;

import com.fatidecoraciones.pedidos.criteria.PresupuestoCriteria;
import com.fatidecoraciones.pedidos.dtos.PresupuestoDto;
import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.Presupuesto;
import com.fatidecoraciones.pedidos.models.Presupuesto_;
import com.fatidecoraciones.pedidos.repositories.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PresupuestoService extends QueryService<Presupuesto> {

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    public boolean existById(Long id) {
        return presupuestoRepository.existsById(id);
    }

    public Presupuesto getPresupuesto(Long id) {
        return presupuestoRepository.findById(id).orElse(null);
    }

    public void save(Presupuesto presupuesto) {
        presupuestoRepository.save(presupuesto);
    }

    public List<PresupuestoDto> getPresupuestoDto() {
        return presupuestoRepository.findAll().stream().map(presupuesto -> new PresupuestoDto(presupuesto)).collect(Collectors.toList());
    }

    public List<Presupuesto> getPresupuestos() {
        return presupuestoRepository.findAll();
    }

    public PresupuestoDto getPresupuestoDto(Long id) {
        return new PresupuestoDto(this.getPresupuesto(id));
    }

    public void delete(Long id) {
        presupuestoRepository.deleteById(id);
    }

    public boolean comparaSistema(List<Presupuesto> presupuestos, Sistema sistema) {
        return presupuestos.stream().allMatch(p -> p.getSistema().equals(sistema));
    }

    public boolean compararCliente(List<Presupuesto> presupuestos) {
        String cliente = presupuestos.get(0).getClienteNombre();
        return presupuestos.stream().allMatch(p -> p.getClienteNombre().equals(cliente));
    }

    public void actualizarPresupuestosViejos() {
        LocalDate hoy = LocalDate.now();
        List<Presupuesto> presupuestos = presupuestoRepository.findAll();

        for (Presupuesto presupuesto : presupuestos) {
            if (presupuesto.getFecha() != null && presupuesto.getFecha().plusDays(365).isBefore(hoy)) {
                presupuesto.setViejo(true);
                presupuestoRepository.save(presupuesto);
            }
        }
    }

    public List<Presupuesto> findByCriteria(PresupuestoCriteria presupuestoCriteria) {
        final Specification<Presupuesto> specification = createSpecification(presupuestoCriteria);
        List<Presupuesto> presupuestos = presupuestoRepository.findAll(specification);
        return presupuestos;
    }

    private Specification<Presupuesto> createSpecification(PresupuestoCriteria presupuestoCriteria) {
        Specification<Presupuesto> specification = Specification.where(null);
        if (presupuestoCriteria != null) {
            if (presupuestoCriteria.getClienteNombre() != null) {
                specification = specification.and(buildStringSpecification(presupuestoCriteria.getClienteNombre(), Presupuesto_.clienteNombre));
            }
            if (presupuestoCriteria.getViejo() != null) {
                specification = specification.and(buildSpecification(presupuestoCriteria.getViejo(), Presupuesto_.viejo));
            }
            if (presupuestoCriteria.getComprado() != null) {
                specification = specification.and(buildSpecification(presupuestoCriteria.getComprado(), Presupuesto_.comprado));
            }
        }
        return specification;
    }
}


