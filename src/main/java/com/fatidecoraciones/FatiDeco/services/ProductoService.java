package com.fatidecoraciones.FatiDeco.services;

import com.fatidecoraciones.FatiDeco.criteria.ProductoCriteria;
import com.fatidecoraciones.FatiDeco.dtos.ProductoDto;
import com.fatidecoraciones.FatiDeco.models.Producto;
import com.fatidecoraciones.FatiDeco.models.Producto_;
import com.fatidecoraciones.FatiDeco.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService extends QueryService<Producto> {

    private final    ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public boolean existsById(Long id) {
        return productoRepository.existsById(id);
    }

    public Producto getTela(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> getTelas() {
        return productoRepository.findAll();
    }

    public ProductoDto getTelaDto(Long id) {
        return new ProductoDto(this.getTela(id));
    }

    public List<ProductoDto> getTelasDto() {
        return productoRepository.findAll().stream().map(ProductoDto::new).collect(Collectors.toList());
    }

    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    public void saveAll(List<Producto> productos){
        List<Producto> productosFiltrados = productos.stream()
                .filter(producto -> !productoRepository.existsByNombre(producto.getNombre().trim()))
                .collect(Collectors.toList());

        productoRepository.saveAll(productosFiltrados);
    }

    public Producto saveVarios(Producto producto) {
        return productoRepository.save(producto);
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    public void incrementarPrecios(double porcentaje, Long marcaId) {
        double factorIncremento = 1 + porcentaje / 100.0;
        productoRepository.actualizarPrecios(factorIncremento, marcaId);
    }

    public Producto findByNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    public Producto findByArt(Long art) {
        return productoRepository.findByArt(art);
    }

//    public List<ProductoDto> getSoloSistemas() {
//        return productoRepository.findSoloSistemas()
//                .stream()
//                .map(ProductoDto::new)
//                .collect(Collectors.toList());
//    }

    public Producto findByNombreAndEsTela(boolean esTela, String tela) {
        return productoRepository.findByNombreAndEsTela(tela, esTela).orElse(null);
    }

    public List<ProductoDto> getByEsTela(boolean esTela) {
        return productoRepository.findByEsTela(esTela)
                .stream()
                .map(ProductoDto::new)
                .collect(Collectors.toList());
    }


    public List<Producto> findByCriteria(ProductoCriteria productoCriteria) {
        final Specification<Producto> specification = createSpecification(productoCriteria);
        return productoRepository.findAll(specification);
    }

    private Specification<Producto> createSpecification(ProductoCriteria productoCriteria) {
        Specification<Producto> specification = Specification.where(null);
        if (productoCriteria != null) {
            if (productoCriteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(productoCriteria.getNombre(), Producto_.nombre));
            }
            if (productoCriteria.getArt() != null) {
                specification = specification.and(buildStringSpecification(productoCriteria.getArt(), Producto_.art));
            }
        }
        return specification;
    }
}