package com.fatidecoraciones.pedidos.controllers;

import com.fatidecoraciones.pedidos.dtos.ColocacionDto;
import com.fatidecoraciones.pedidos.models.Colocacion;
import com.fatidecoraciones.pedidos.services.ColocacionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("fati")
@CrossOrigin
public class ColocacionController {

    @Autowired
    ColocacionService colocacionService;

    @GetMapping("/lista")
    public ResponseEntity<List<ColocacionDto>> lista() {
        List<ColocacionDto> list = colocacionService.getColocsDto();
        if (list.isEmpty()) {
            return new ResponseEntity("No hay SERVICIOS cargados", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<ColocacionDto> uno(@PathVariable("id") Long id) {

        ColocacionDto uno = colocacionService.getColocDto(id);
        if (uno == null) {
            return new ResponseEntity("El SERVICIO no existe o fue BORRADO", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<?> nuevo(@RequestBody ColocacionDto nuevo) {

        if (StringUtils.isBlank(nuevo.getTipo())) {
            return new ResponseEntity<>("Falta el nombre de la TELA", HttpStatus.BAD_REQUEST);
        }
        if (nuevo.getPrecio() == null) {
            return new ResponseEntity<>("Falta el PRECIO", HttpStatus.BAD_REQUEST);
        }
        Colocacion colocacion = new Colocacion(
                nuevo.getTipo(),
                nuevo.getPrecio()
        );
        colocacionService.save(colocacion);
        return new ResponseEntity<>("Colocación creada con éxito", HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody ColocacionDto editar) {

        if (!colocacionService.existById(id)) {
            return new ResponseEntity<>("No existe la TELA", HttpStatus.NOT_FOUND);
        }
        if (editar.getPrecio() == null) {
            return new ResponseEntity<>("El PRECIO no puede ser nulo", HttpStatus.BAD_REQUEST);
        }

        Colocacion colocacion = colocacionService.getColoc(id);

        colocacion.setTipo(editar.getTipo());
        colocacion.setPrecio(editar.getPrecio());

        colocacionService.save(colocacion);
        return new ResponseEntity<>("Colocación actualizada con éxito", HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable("id") Long id) {
        if (!colocacionService.existById(id)) {
            return new ResponseEntity<>("La TELA no existe", HttpStatus.NOT_FOUND);
        }
        colocacionService.delete(id);
        return new ResponseEntity<>("TELA borrada", HttpStatus.OK);
    }

    @PostMapping("/varios")
    @Transactional
    public ResponseEntity<List<Colocacion>> guardarVarios(@RequestBody List<Colocacion> dataEntries) {
        List<Colocacion> colocaciones = dataEntries.stream()
                .map(colocacionService::saveVarios)
                .collect(Collectors.toList());
        return ResponseEntity.ok(colocaciones);
    }

    @PutMapping("masivo")
    @Transactional
    public ResponseEntity<?> incrementarPrecios(@RequestParam double porcentaje) {
        List<ColocacionDto> list = colocacionService.getColocsDto();
        if (list.isEmpty()) {
            return new ResponseEntity<>("No hay TELAS cargadas", HttpStatus.BAD_REQUEST);
        }
        colocacionService.incrementarPrecios(porcentaje);
        return new ResponseEntity<>("Modificación MASIVA exitosa", HttpStatus.OK);
    }
}
