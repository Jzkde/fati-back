package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.dtos.SistemaDto;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import com.fatidecoraciones.FatiDeco.services.SistemaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sistemas")
@CrossOrigin
public class SistemaController {

    private final SistemaService sistemaService;

    public SistemaController(SistemaService sistemaService) {
        this.sistemaService = sistemaService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<SistemaDto>> lista() {
        List<SistemaDto> list = sistemaService.getSistemasDto();
        if (list.isEmpty()) {
            return new ResponseEntity("No hay PROVEEDORES/MARCAS cargadas", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Sistema> buscar(@RequestParam String sistemaN) {
        Sistema sistema = sistemaService.findBySistema(sistemaN);
        if (sistema == null) {
            System.out.println("Buscado: " + sistemaN);
            System.out.println("MARCA no encontrada");
            return new ResponseEntity("MARCA no encontrada", HttpStatus.NOT_FOUND);
        }
        System.out.println("Buscado: " + sistemaN);
        System.out.println("Encontrado: " + sistema.getSistema());
        return new ResponseEntity<>(sistema, HttpStatus.OK);
    }

    @GetMapping("/uno/{id}")
    public ResponseEntity<SistemaDto> uno(@PathVariable Long id) {


        if (!sistemaService.existById(id)) {
            return new ResponseEntity("El PROVEEDOR no existe o fue BORRADO", HttpStatus.NOT_FOUND);
        }
        SistemaDto uno = sistemaService.getSistemaDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<?> nuevo(@RequestBody SistemaDto nuevo) {

        if (sistemaService.findBySistema(nuevo.getSistema().trim()) !=null){
            return new ResponseEntity<>("La MARCA ya existe", HttpStatus.FORBIDDEN);
        }
        if (StringUtils.isBlank(nuevo.getSistema())) {
            return new ResponseEntity<>("Falta el nombre del PROVEEDOR", HttpStatus.BAD_REQUEST);
        }
        Sistema sistema = new Sistema(
                nuevo.getSistema().trim()
        );
        sistemaService.save(sistema);
        return new ResponseEntity<>("PROVEEDOR creado con éxito", HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable Long id,
                                    @RequestBody SistemaDto editar) {

        if (!sistemaService.existById(id)) {
            return new ResponseEntity<>("No existe el PROVEEDOR", HttpStatus.NOT_FOUND);
        }
        if (sistemaService.findBySistema(editar.getSistema().trim()) !=null){
            return new ResponseEntity<>("La MARCA ya existe", HttpStatus.FORBIDDEN);
        }
        Sistema sistema = sistemaService.getSistema(id);

        sistema.setSistema(editar.getSistema().trim());

        sistemaService.save(sistema);
        return new ResponseEntity<>("PROVEEDOR actualizado", HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (!sistemaService.existById(id)) {
            return new ResponseEntity<>("El PROVEEDOR no existe", HttpStatus.NOT_FOUND);
        }

        // Validar si el cliente tiene presupuestos
        if (sistemaService.tieneProductos(id)) {
            return new ResponseEntity<>("No se puede eliminar la MARCA porque tiene elementos asociados", HttpStatus.BAD_REQUEST);
        }

        sistemaService.delete(id);
        return new ResponseEntity<>("MARCA eliminada", HttpStatus.OK);
    }
}

