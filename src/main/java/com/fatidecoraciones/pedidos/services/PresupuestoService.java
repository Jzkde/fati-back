package com.fatidecoraciones.pedidos.services;

import com.fatidecoraciones.pedidos.dtos.PresupuestoDto;
import com.fatidecoraciones.pedidos.models.Presupuesto;
import com.fatidecoraciones.pedidos.repositories.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PresupuestoService {

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    public boolean existById (Long id) {
        return presupuestoRepository.existsById(id);
    }
    public Presupuesto getPresupuesto (Long id){  return presupuestoRepository.findById(id).orElse(null);    }
    public void save (Presupuesto presupuesto){  presupuestoRepository.save(presupuesto);    }
    public List<PresupuestoDto> getPresupuestoDto() {
        return presupuestoRepository.findAll().stream().map(presupuesto -> new PresupuestoDto(presupuesto)).collect(Collectors.toList());
    }
    public List<Presupuesto> getPresupuestos() {     return presupuestoRepository.findAll();    }
    public PresupuestoDto getPresupuestoDto(Long id) {        return new PresupuestoDto(this.getPresupuesto(id));    }
    public void delete (Long id){presupuestoRepository.deleteById(id);}

}


