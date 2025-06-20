package com.fatidecoraciones.FatiDeco.services;

import com.fatidecoraciones.FatiDeco.criteria.MedidaCriteria;
import com.fatidecoraciones.FatiDeco.dtos.MedidaDto;
import com.fatidecoraciones.FatiDeco.models.Medida;
import com.fatidecoraciones.FatiDeco.models.Medida_;

import com.fatidecoraciones.FatiDeco.models.Sistema;
import com.fatidecoraciones.FatiDeco.repositories.MedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedidaService extends QueryService<Medida> {

    private final MedidaRepository medidaRepository;

    public MedidaService(MedidaRepository medidaRepository) {
        this.medidaRepository = medidaRepository;
    }

    public boolean existById(Long id) {
        return medidaRepository.existsById(id);
    }

    public Medida getMedida(Long id) {
        return medidaRepository.findById(id).orElse(null);
    }

    public void save(Medida medida) {
        medidaRepository.save(medida);
    }

    public List<MedidaDto> getMedidaDto() {
        return medidaRepository.findAll().stream().map(MedidaDto::new).collect(Collectors.toList());
    }

    public List<Medida> getMedidas() {
        return medidaRepository.findAll();
    }

    public MedidaDto getMedidaDto(Long id) {
        return new MedidaDto(this.getMedida(id));
    }

    public void delete(Long id) {
        medidaRepository.deleteById(id);
    }

//    public boolean comparaSistema(List<Medida> medidas, Sistema sistEnum) {
//        return medidas.stream().allMatch(p -> p.getSistema().equals(sistEnum));
//    }

    public boolean compararCliente(List<MedidaDto> medidas) {
        String cliente = medidas.get(0).getCliente();
        return medidas.stream().allMatch(p -> p.getCliente().equalsIgnoreCase(cliente));
    }

    public void actualizarMedidasViejas() {
        LocalDate hoy = LocalDate.now();
        List<Medida> medidas = medidaRepository.findAll();
        List<Medida> medidasViejas = new ArrayList<>();

        for (Medida medida : medidas) {
            if (medida.getFecha() != null && medida.getFecha().plusDays(120).isBefore(hoy)) {
                medida.setViejo(true);
                medidasViejas.add(medida);
            }
        }
        medidaRepository.saveAll(medidasViejas);
    }

    public List<Medida> findByCriteria(MedidaCriteria medidaCriteria) {
        final Specification<Medida> specification = createSpecification(medidaCriteria);
        return medidaRepository.findAll(specification);
    }

    private Specification<Medida> createSpecification(MedidaCriteria medidaCriteria) {
        Specification<Medida> specification = Specification.where(null);
        if (medidaCriteria != null) {
            if (medidaCriteria.getClienteNombre() != null) {
                specification = specification.and(buildStringSpecification(medidaCriteria.getClienteNombre(), Medida_.clienteNombre));
            }
            if (medidaCriteria.getViejo() != null) {
                specification = specification.and(buildSpecification(medidaCriteria.getViejo(), Medida_.viejo));
            }
            if (medidaCriteria.getComprado() != null) {
                specification = specification.and(buildSpecification(medidaCriteria.getComprado(), Medida_.comprado));
            }
        }
        return specification;
    }

    public void saveAll(List<Medida> medidas) {
        medidaRepository.saveAll(medidas);
    }
}