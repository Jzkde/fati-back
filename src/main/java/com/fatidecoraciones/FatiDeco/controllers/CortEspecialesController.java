package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.criteria.CortEspecialesCriteria;
import com.fatidecoraciones.FatiDeco.dtos.BusquedaDto;
import com.fatidecoraciones.FatiDeco.dtos.CortEspecialesDto;
import com.fatidecoraciones.FatiDeco.models.CortEspeciales;
import com.fatidecoraciones.FatiDeco.models.Marca;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import com.fatidecoraciones.FatiDeco.services.CortEspecialesService;
import com.fatidecoraciones.FatiDeco.services.MarcaService;
import com.fatidecoraciones.FatiDeco.services.SistemaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.StringFilter;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cortespeciales")
@CrossOrigin
public class CortEspecialesController {

    private final CortEspecialesService cortEspecialesService;
    private final MarcaService marcaService;
    private final SistemaService sistemaService;

    public CortEspecialesController(
            CortEspecialesService cortEspecialesService,
            MarcaService marcaService,
            SistemaService sistemaService) {

        this.cortEspecialesService = cortEspecialesService;
        this.marcaService = marcaService;
        this.sistemaService = sistemaService;
    }


    @GetMapping("/lista/total")
    public ResponseEntity<List<CortEspecialesDto>> listaTotal() {
        List<CortEspecialesDto> list = cortEspecialesService.getCortEspecialessDto();
        if (list.isEmpty()) {
            return new ResponseEntity("No hay SISTEMAS ESPECIALES cargadas", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/{marca}")
    public ResponseEntity<List<CortEspecialesDto>> listaPorMarca(@PathVariable String marca) {
        List<CortEspecialesDto> list = cortEspecialesService.findByMarca(marca);
        if (list.isEmpty()) {
            return new ResponseEntity("No hay PRODUCTOS cargados con esa MARCA", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/uno/{id}")
    public ResponseEntity<CortEspecialesDto> uno(@PathVariable Long id) {

        if (!cortEspecialesService.existById(id)) {
            return new ResponseEntity("La TELA no existe o fue BORRADA", HttpStatus.NOT_FOUND);
        }

        CortEspecialesDto uno = cortEspecialesService.getCortEspecialesDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @GetMapping("/sistemas")
    public ResponseEntity<List<CortEspecialesDto>> sistemaPorMarca(@RequestParam String marca, String sistema) {

        List<CortEspecialesDto> list = cortEspecialesService.getSistemas(sistema, marca);
        if (list.isEmpty()) {
            return new ResponseEntity("No hay SISTEMAS cargados", HttpStatus.NOT_FOUND);
        }
        System.out.println(marca + "  " + sistema);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/telas")
    public ResponseEntity<List<CortEspecialesDto>> telasPorMarca(@RequestParam String marca, String sistema) {

        List<CortEspecialesDto> list = cortEspecialesService.getTelas(sistema, marca);
        if (list.isEmpty()) {
            return new ResponseEntity("No hay TELAS cargadas", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/filtro")
    public ResponseEntity<List<CortEspecialesDto>> filtro(@RequestBody BusquedaDto busquedaDto) {
        CortEspecialesCriteria cortEspecialesCriteria = createCriteria(busquedaDto);
        List<CortEspeciales> lista = cortEspecialesService.findByCriteria(cortEspecialesCriteria);

        List<CortEspecialesDto> listaDto = lista.stream().map(c -> {
            CortEspecialesDto dto = new CortEspecialesDto();
            dto.setTela(c.getTela());
            dto.setPrecio(c.getPrecio());
            dto.setEsTela(c.isEsTela());
            dto.setSistema(c.getSistema() != null ? c.getSistema().getSistema() : null);
            dto.setMarca(c.getMarca() != null ? c.getMarca().getMarca() : null);
            return dto;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(listaDto, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<?> nuevo(@RequestBody CortEspecialesDto nuevo) {

        Marca marca = marcaService.findByMarca(nuevo.getMarca());
        Sistema sistema = sistemaService.findBySistema(nuevo.getSistema());
        if (marca == null) {
            return new ResponseEntity<>("MARCA no Encontrada", HttpStatus.BAD_REQUEST);
        }
        if (sistema == null) {
            return new ResponseEntity<>("SISTEMA no Encontrado", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevo.getTela())) {
            return new ResponseEntity<>("Falta el nombre de la TELA", HttpStatus.BAD_REQUEST);
        }
        if (nuevo.getPrecio() == null || nuevo.getPrecio() <= 0) {
            return new ResponseEntity<>("Falta el PRECIO y no puede ser 0 o negativo", HttpStatus.BAD_REQUEST);
        }

        CortEspeciales cortEspeciales = new CortEspeciales(

                nuevo.getTela(),
                nuevo.getPrecio(),
                nuevo.isEsTela()
        );

        marca.addMarcaEsp(cortEspeciales);
        sistema.addCortEspeciales(cortEspeciales);

        cortEspecialesService.save(cortEspeciales);
        return new ResponseEntity<>("PRODUCTO creado Nº " + cortEspeciales.getId(), HttpStatus.OK);
    }

    @PostMapping("/varios")
    @Transactional
    public ResponseEntity<?> guardarVarios(@RequestBody List<CortEspecialesDto> nuevos) {

        List<String> errores = new ArrayList<>();

        for (CortEspecialesDto nuevo : nuevos) {

            Marca marca = marcaService.findByMarca(nuevo.getMarca());
            Sistema sistema = sistemaService.findBySistema(nuevo.getSistema());

            if (marca == null) {
                errores.add("MARCA no encontrada para: " + nuevo.getMarca());
                continue;
            }
            if (sistema == null) {
                errores.add("SISTEMA no encontrado para: " + nuevo.getSistema());
                continue;
            }
            if (StringUtils.isBlank(nuevo.getTela())) {
                errores.add("Falta el nombre de la TELA para un producto");
                continue;
            }
            if (nuevo.getPrecio() == null || nuevo.getPrecio() <= 0) {
                errores.add("Precio inválido para tela: " + nuevo.getTela());
                continue;
            }

            CortEspeciales cortEspeciales = new CortEspeciales(
                    nuevo.getTela(),
                    nuevo.getPrecio(),
                    nuevo.isEsTela()
            );

            marca.addMarcaEsp(cortEspeciales);
            sistema.addCortEspeciales(cortEspeciales);
            cortEspecialesService.save(cortEspeciales);
        }

        if (!errores.isEmpty()) {
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Todos los productos fueron guardados correctamente.", HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable Long id,
                                    @RequestBody CortEspecialesDto editar) {

        Marca marca = marcaService.findByMarca(editar.getMarca());
        Sistema sistema = sistemaService.findBySistema(editar.getSistema());

        if (!cortEspecialesService.existById(id)) {
            return new ResponseEntity<>("No existe la TELA/SISTEMA", HttpStatus.BAD_REQUEST);
        }
        if (marca == null) {
            return new ResponseEntity<>("La MARCA NO existe", HttpStatus.BAD_REQUEST);
        }
        if (sistema == null) {
            return new ResponseEntity<>("El SISTEMA NO existe", HttpStatus.BAD_REQUEST);
        }
        if (editar.getPrecio() <= 0) {
            return new ResponseEntity<>("El MONTO no puede ser 0 o negativo", HttpStatus.BAD_REQUEST);
        }

        CortEspeciales cortEspeciales = cortEspecialesService.getCortEspeciales(id);

        cortEspeciales.setTela(editar.getTela());
        cortEspeciales.setPrecio(editar.getPrecio());
        cortEspeciales.setEsTela(editar.isEsTela());

        cortEspeciales.setSistema(sistema);
        cortEspeciales.setMarca(marca);

        cortEspecialesService.save(cortEspeciales);

        return new ResponseEntity<>("PRODUCTO modificado", HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (!cortEspecialesService.existById(id))
            return new ResponseEntity<>("La TELA/SISTEMA no existe", HttpStatus.NOT_FOUND);
        cortEspecialesService.delete(id);
        return new ResponseEntity<>("TELA/SISTEMA borrada", HttpStatus.OK);
    }





    @PutMapping("/masivo")
    @Transactional
    public ResponseEntity<String> incrementarPrecios(@RequestParam String marca, double porcentaje) {
        if (marcaService.findByMarca(marca) == null) {
            return new ResponseEntity("MARCA no encontrada", HttpStatus.NOT_FOUND);
        }
        Long marcaId = marcaService.findByMarca(marca).getId();
        List<CortEspecialesDto> list = cortEspecialesService.getCortEspecialessDto();
        if (list.isEmpty()) {
            return new ResponseEntity("No hay TELAS/SISTEMAS cargadas", HttpStatus.BAD_REQUEST);
        }
        cortEspecialesService.incrementarPrecios(porcentaje, marcaId);
        return new ResponseEntity<>("Modificación MASIVA exitosa", HttpStatus.OK);
    }

    private CortEspecialesCriteria createCriteria(BusquedaDto busqueda) {
        CortEspecialesCriteria cortEspecialesCriteria = new CortEspecialesCriteria();
        if (busqueda != null) {

            //Tela
            if (!StringUtils.isBlank(busqueda.getTela())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getTela());
                cortEspecialesCriteria.setTela(filter);
            }

            //Marca
            if (!StringUtils.isBlank(busqueda.getMarca())) {
                StringFilter filter = new StringFilter();
                filter.setEquals(busqueda.getMarca().toUpperCase());
                cortEspecialesCriteria.setMarca(filter);
            }

            //Es Tela o Sistema
            if (!StringUtils.isBlank(busqueda.getEsTela())) {
                BooleanFilter filter = new BooleanFilter();
                if (busqueda.getEsTela().equals("false")) {
                    filter.setEquals(false);
                } else {
                    filter.setEquals(true);
                }
                cortEspecialesCriteria.setEsTela(filter);
            }
        }
        return cortEspecialesCriteria;
    }
}