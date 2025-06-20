package com.fatidecoraciones.FatiDeco.services;

import com.fatidecoraciones.FatiDeco.dtos.MarcaDto;
import com.fatidecoraciones.FatiDeco.models.Marca;
import com.fatidecoraciones.FatiDeco.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public boolean existById(Long id) {
        return marcaRepository.existsById(id);
    }

    public Marca getMarca(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }

    public List<Marca> getMarcas() {
        return marcaRepository.findAll();
    }

    public MarcaDto getMarcaDto(Long id) {
        return new MarcaDto(this.getMarca(id));
    }

    public List<MarcaDto> getMarcasDto() {
        return marcaRepository.findAll().stream().map(MarcaDto::new).collect(Collectors.toList());
    }

    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca saveVarios(Marca marca) {
        return marcaRepository.save(marca);
    }

    public void delete(Long id) {
        marcaRepository.deleteById(id);
    }

    public Marca findByMarca(String marca) {
        return marcaRepository.findByMarcaIgnoreCase(marca);
    }
    public MarcaDto findByMarcaDto(String marca) {
        Marca marcaEntidad = marcaRepository.findByMarcaIgnoreCase(marca);
        if (marcaEntidad != null) {
            return new MarcaDto(marcaEntidad);
        } else {
            return null;
        }
    }

    public Marca findById(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }


    public boolean tieneProductos(Long clienteId) {
        Marca marca = marcaRepository.findById(clienteId).orElse(null);
        return
                marca != null && marca.getProductos() != null && !marca.getProductos().isEmpty() ||
                        marca != null && marca.getServicios() != null && !marca.getServicios().isEmpty() ||
                        marca != null && marca.getCortEspeciales() != null && !marca.getCortEspeciales().isEmpty();
    }

}