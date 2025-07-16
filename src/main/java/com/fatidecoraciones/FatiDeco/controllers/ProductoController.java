package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.criteria.ProductoCriteria;
import com.fatidecoraciones.FatiDeco.dtos.BusquedaDto;
import com.fatidecoraciones.FatiDeco.dtos.ProductoDto;
import com.fatidecoraciones.FatiDeco.models.Marca;
import com.fatidecoraciones.FatiDeco.models.Producto;
import com.fatidecoraciones.FatiDeco.services.MarcaService;
import com.fatidecoraciones.FatiDeco.services.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.service.filter.StringFilter;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("prod")
@CrossOrigin
public class ProductoController {

    private final ProductoService productoService;
    private final MarcaService marcaService;

    public ProductoController(ProductoService productoService,
                              MarcaService marcaService) {

        this.productoService = productoService;
        this.marcaService = marcaService;
    }

    @GetMapping("/lista/total")
    public ResponseEntity<List<ProductoDto>> lista() {
        List<ProductoDto> list = productoService.getTelasDto();
        if (list.isEmpty()) {
            return new ResponseEntity("No hay PRODUCTOS cargados", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/prod")
    public ResponseEntity<List<ProductoDto>> productos(@RequestParam boolean esTela) {

        List<ProductoDto> list = productoService.getByEsTela(esTela);
        if (list.isEmpty()) {
            return new ResponseEntity("No hay TELAS cargadas", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/uno/{id}")
    public ResponseEntity<ProductoDto> uno(@PathVariable Long id) {

        ProductoDto uno = productoService.getTelaDto(id);
        if (uno == null) {
            return new ResponseEntity("La TELA no existe o fue BORRADA", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }


    @PostMapping("/filtro")
    public ResponseEntity<List<ProductoDto>> filtro(@RequestBody BusquedaDto busquedaDto) {
        ProductoCriteria productoCriteria = createCriteria(busquedaDto);
        List<Producto> list = productoService.findByCriteria(productoCriteria);

        List<ProductoDto> listaDto = list.stream().map(producto -> {
            ProductoDto productoDto = new ProductoDto();

            productoDto.setId(producto.getId());
            productoDto.setArt(producto.getArt());
            productoDto.setNombre(producto.getNombre());
            productoDto.setPrecio(producto.getPrecio());
            productoDto.setEsTela(producto.isEsTela());
            productoDto.setMarca(producto.getMarca().getMarca());

            return  productoDto;

        }).collect(Collectors.toList());

        return new ResponseEntity<>(listaDto, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<?> nuevo(@RequestBody ProductoDto nuevo,
                                   @RequestParam String marcaN) {

        Marca marca = marcaService.findByMarca(marcaN);
        if (marca == null) {
            return new ResponseEntity<>("ERROR en la MARCA", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(nuevo.getNombre())) {
            return new ResponseEntity<>("Falta el nombre del ARTICULO", HttpStatus.BAD_REQUEST);
        }
        if (nuevo.getPrecio() == 0) {
            {
                return new ResponseEntity<>("Falta el PRECIO", HttpStatus.BAD_REQUEST);
            }
        }
        if (StringUtils.isBlank(nuevo.getArt())) {
            {
                return new ResponseEntity<>("Falta el numero de ARTICULO", HttpStatus.BAD_REQUEST);
            }
        }

        Producto producto = new Producto(

                nuevo.getArt(),
                nuevo.getNombre(),
                nuevo.getPrecio(),
                nuevo.isEsTela()
        );

        marca.addMarcaProd(producto);

        productoService.save(producto);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @PostMapping("/varios")
    @Transactional
    public ResponseEntity<?> saveVarios(@RequestBody List<ProductoDto> lista) {

        List<Producto> productos = new ArrayList<>();

        for (ProductoDto dto : lista) {
            Producto producto = new Producto();

            // Verificar si dto.getMarca() es null
            if (dto.getMarca() == null) {
                return new ResponseEntity<>("FALTA MARCA en uno de los productos", HttpStatus.BAD_REQUEST);
            }

            // Verificar si la marca existe en la base de datos
            Marca marca = marcaService.findByMarca(dto.getMarca());
            if (marca == null) {
                return new ResponseEntity<>("MARCA no ingesada: " + dto.getMarca(), HttpStatus.BAD_REQUEST);
            }

            producto.setArt(dto.getArt());
            producto.setNombre(dto.getNombre());
            producto.setPrecio(dto.getPrecio());
            producto.setEsTela(dto.isEsTela());

            marca.addMarcaProd(producto);
            productos.add(producto);
        }

        productoService.saveAll(productos);
        return new ResponseEntity<>("PRODUCTOS cargados", HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable Long id,
                                    @RequestParam String marca,
                                    @RequestBody ProductoDto editar) {

        if (!productoService.existById(id)) {
            return new ResponseEntity<>("El PRODUCTO no existe", HttpStatus.BAD_REQUEST);
        }
        if (editar.getArt() == null || editar.getArt().isBlank()) {
            return new ResponseEntity<>("El ARTICULO del PRODUCTO no puede estar en blanco", HttpStatus.BAD_REQUEST);
        }
        if (editar.getNombre() == null) {
            return new ResponseEntity<>("El NOMBRE del PRODUCTO no puede estar en blanco", HttpStatus.BAD_REQUEST);
        }
        if (editar.getPrecio() <= 0) {
            return new ResponseEntity<>("El MONTO de puede ser 0 o negativo", HttpStatus.BAD_REQUEST);
        }
        if (marcaService.findByMarca(marca) == null) {
            return new ResponseEntity<>("La MARCA " + marca + " no existe", HttpStatus.BAD_REQUEST);
        }

        Producto producto = productoService.getTela(id);

        producto.setArt(editar.getArt());
        producto.setNombre(editar.getNombre());
        producto.setPrecio(editar.getPrecio());
        producto.setEsTela(editar.isEsTela());


        Marca eMarca = marcaService.findByMarca(marca);

        producto.setMarca(eMarca);

        productoService.save(producto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/masivo")
    @Transactional
    public ResponseEntity<String> incrementarPrecios(@RequestParam double porcentaje, String marca) {
        if (marcaService.findByMarca(marca.trim()) == null) {
            return new ResponseEntity("MARCA no encontrada", HttpStatus.NOT_FOUND);
        }
        Long marcaId = marcaService.findByMarca(marca.trim()).getId();
        List<ProductoDto> list = productoService.getTelasDto();
        if (list.isEmpty())
            return new ResponseEntity("No hay TELAS cargadas", HttpStatus.BAD_REQUEST);
        productoService.incrementarPrecios(porcentaje, marcaId);
        return new ResponseEntity<>("Modificación MASIVA exitosa", HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (!productoService.existById(id))
            return new ResponseEntity<>("La TELA no existe", HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity<>("TELA borrada", HttpStatus.OK);
    }

    private ProductoCriteria createCriteria(BusquedaDto busqueda) {
        ProductoCriteria productoCriteria = new ProductoCriteria();

        if (busqueda != null) {

            //Tela x nombre
            if (!StringUtils.isBlank(busqueda.getNombre())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getNombre());
                productoCriteria.setNombre(filter);
            }
            //Tela x articulo
            if (!StringUtils.isBlank(busqueda.getArt())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getArt());
                productoCriteria.setArt(filter);
            }
        }
        return productoCriteria;
    }
}