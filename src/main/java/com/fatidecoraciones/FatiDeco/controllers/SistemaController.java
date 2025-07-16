package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.dtos.SistemaDto;
import com.fatidecoraciones.FatiDeco.models.Marca;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import com.fatidecoraciones.FatiDeco.services.MarcaService;
import com.fatidecoraciones.FatiDeco.services.SistemaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("sistemas")
@CrossOrigin
public class SistemaController {

    private final SistemaService sistemaService;
    private final MarcaService marcaService;

    public SistemaController(SistemaService sistemaService,
                             MarcaService marcaService) {
        this.sistemaService = sistemaService;
        this.marcaService = marcaService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<SistemaDto>> lista() {
        List<SistemaDto> lista = sistemaService.getSistemasDto();
        if (lista.isEmpty()) {
            return new ResponseEntity("No hay SISTEMAS cargados", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/lista/tipo")
    public ResponseEntity<List<SistemaDto>> listaPorTipo(@RequestParam boolean esSistema) {
        List<SistemaDto> lista = sistemaService.sistemasPorTipo(esSistema);
        if (lista.isEmpty()) {
            return new ResponseEntity("No hay SISTEMAS cargados", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<SistemaDto> buscar(@RequestParam String sistemaN) {

        SistemaDto sistema = sistemaService.findBySistemaDto(sistemaN);

        if (sistema == null) {
            return new ResponseEntity("SISTEMA no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sistema, HttpStatus.OK);
    }

    @GetMapping("/uno/{id}")
    public ResponseEntity<SistemaDto> uno(@PathVariable Long id) {

        if (!sistemaService.existById(id)) {
            return new ResponseEntity("El SISTEMA no existe o fue BORRADO", HttpStatus.NOT_FOUND);
        }
        SistemaDto uno = sistemaService.getSistemaDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody SistemaDto nuevo) {

        if (sistemaService.findBySistema(nuevo.getSistema().trim()) != null) {
            return new ResponseEntity<>("El SISTEMA ya existe", HttpStatus.FORBIDDEN);
        }
        if (StringUtils.isBlank(nuevo.getSistema())) {
            return new ResponseEntity<>("Falta el nombre del SISTEMA", HttpStatus.BAD_REQUEST);
        }
        if (nuevo.getMarcas().isEmpty()) {
            return new ResponseEntity<>("El SISTEMA debe tener una MARCA asignada", HttpStatus.NOT_FOUND);
        }
        Sistema sistema = new Sistema(
                nuevo.getSistema().toUpperCase().trim(),
                nuevo.isEsSistema()
        );
        for (String marcaN : nuevo.getMarcas()) {

            if (marcaN.isBlank()) {
                return new ResponseEntity<>("El SISTEMA debe tener una MARCA asignada", HttpStatus.NOT_FOUND);
            }
            if (marcaService.findByMarca(marcaN.trim()) == null) {
                return new ResponseEntity<>("La MARCA no existe", HttpStatus.NOT_FOUND);
            }

            Marca marca = marcaService.findByMarca(marcaN.trim());

            sistema.addMarca(marca);

        }
        sistemaService.save(sistema);
        return new ResponseEntity<>("SISTEMA agregado con éxito", HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id,
                                    @RequestBody SistemaDto editar) {

        if (!sistemaService.existById(id)) {
            return new ResponseEntity<>("El SISTEMA no existe", HttpStatus.NOT_FOUND);
        }
        if (sistemaService.findBySistema(editar.getSistema().trim()) != null) {
            return new ResponseEntity<>("La SISTEMA ya existe: " + editar.getSistema(), HttpStatus.FORBIDDEN);
        }

        Sistema sistema = sistemaService.getSistema(id);

        sistema.setSistema(editar.getSistema().trim());
        sistema.setEsSistema(editar.isEsSistema());

        for (Marca marca : new HashSet<>(sistema.getMarcas())) {
            sistema.removeMarca(marca);
        }

        for (String marcaN : editar.getMarcas()) {

            if (marcaN.isBlank()) {
                return new ResponseEntity<>("El SISTEMA debe tener una MARCA asignada", HttpStatus.NOT_FOUND);
            }
            if (marcaService.findByMarca(marcaN.trim()) == null) {
                return new ResponseEntity<>("La MARCA no existe", HttpStatus.NOT_FOUND);
            }

            Marca marca = marcaService.findByMarca(marcaN.trim());

            sistema.addMarca(marca);

        }

        sistemaService.save(sistema);
        return new ResponseEntity<>("SISTEMA actualizado", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (!sistemaService.existById(id)) {
            return new ResponseEntity<>("El SISTEMA no existe", HttpStatus.NOT_FOUND);
        }

        // Validar si el cliente tiene presupuestos
        if (sistemaService.tieneProductos(id)) {
            return new ResponseEntity<>("No se puede eliminar el SISTEMA porque tiene elementos asociados", HttpStatus.BAD_REQUEST);
        }

        sistemaService.delete(id);
        return new ResponseEntity<>("SISTEMA eliminada", HttpStatus.OK);
    }

    @PutMapping("/agregar-marcas/{id}")
    public ResponseEntity<?> agregarMarcasASistema(@PathVariable Long id, @RequestParam Long marcaId) {

        Sistema sistema = sistemaService.findById(id);
        if (sistema == null) {
            return new ResponseEntity<>("El SISTEMA no existe", HttpStatus.NOT_FOUND);
        }

        Marca marca = marcaService.findById(marcaId);
        if (marca == null) {
            return new ResponseEntity<>("La MARCA no existe", HttpStatus.NOT_FOUND);
        }

        if (sistema.getMarcas().contains(marca)) {
            return new ResponseEntity<>("La MARCA y el SISTEMA ya estan asociados", HttpStatus.BAD_REQUEST);
        }

        sistema.addMarca(marca);
        sistemaService.save(sistema);

        return new ResponseEntity<>("SISTEMA actualizado", HttpStatus.OK);
    }

    @GetMapping("/por/{marca}")
    public ResponseEntity<?> obtenerSistemasPorMarca(@PathVariable String marca) {

        List<SistemaDto> sistemas = sistemaService.getSistemasPorMarca(marca);

        if (sistemas.isEmpty()) {
            return new ResponseEntity<>("No hay SISTEMAS cargados con esa MARCA", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(sistemas, HttpStatus.OK);
    }
}

