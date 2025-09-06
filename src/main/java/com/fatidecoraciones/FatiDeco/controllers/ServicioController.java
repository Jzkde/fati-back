package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.dtos.ServicioDto;
import com.fatidecoraciones.FatiDeco.dB.enums.Serv;
import com.fatidecoraciones.FatiDeco.models.Marca;
import com.fatidecoraciones.FatiDeco.models.Servicio;
import com.fatidecoraciones.FatiDeco.services.MarcaService;
import com.fatidecoraciones.FatiDeco.services.ServicioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("fati")
@CrossOrigin
public class ServicioController {


    private final ServicioService servicioService;
    private final MarcaService marcaService;

    public ServicioController(ServicioService servicioService,
                              MarcaService marcaService) {
        this.servicioService = servicioService;
        this.marcaService = marcaService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ServicioDto>> lista() {
        List<ServicioDto> list = servicioService.getColocsDto();
        if (list.isEmpty()) {
            return new ResponseEntity("No hay COLOCACIONES cargadas", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/tipo")
    public ResponseEntity<List<ServicioDto>> listaColocaciones(@RequestParam String tipo) {
        List<ServicioDto> list = servicioService.getColocsFiltradoDto(tipo);
        if (list.isEmpty()) {
            return new ResponseEntity("No hay " + tipo.toUpperCase() + " cargada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/uno/{id}")
    public ResponseEntity<ServicioDto> uno(@PathVariable Long id) {
        if (!servicioService.existsById(id)) {
            return new ResponseEntity("La COLOCACION no existe o fue BORRADA", HttpStatus.NOT_FOUND);
        }

        ServicioDto uno = servicioService.getColocDto(id);

        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<?> nuevo(@RequestBody ServicioDto nuevo) {

        if (StringUtils.isBlank(nuevo.getNombre())) {
            return new ResponseEntity<>("Falta el nombre del SERVICIO", HttpStatus.BAD_REQUEST);
        }
        if (nuevo.getPrecio() < 0) {
            return new ResponseEntity<>("Falta el PRECIO del SERVICIO no puede ser NEGATIVO", HttpStatus.BAD_REQUEST);
        }
        Marca fati  = marcaService.findByMarca("fati");

        if (fati == null) {
            fati = new Marca(
                    "FATI",
                    "Fati Decoraciones",
                    false);

            marcaService.save(fati);
        }

        Servicio servicio = new Servicio(
                nuevo.getNombre(),
                nuevo.getTipo(),
                nuevo.getPrecio()

        );
        fati.addMarcaServ(servicio);
        servicioService.save(servicio);
        return new ResponseEntity<>("COLOCACION creada con éxito", HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable Long id,
                                    @RequestBody ServicioDto editar) {

        if (!servicioService.existsById(id)) {
            return new ResponseEntity<>("No existe la COLOCACION", HttpStatus.NOT_FOUND);
        }
        if (editar.getPrecio() < 0) {
            return new ResponseEntity<>("El PRECIO no puede ser NEGATIVO", HttpStatus.BAD_REQUEST);
        }
        if (editar.getNombre() == null) {
            return new ResponseEntity<>("Falta el NOMBRE del servicio", HttpStatus.BAD_REQUEST);
        }
        String tipo = editar.getTipo().toString().toUpperCase();
        if (editar.getTipo() == null) {
            return new ResponseEntity<>("Falta el TIPO del servicio", HttpStatus.BAD_REQUEST);
        }
        Servicio servicio = servicioService.getColoc(id);

        servicio.setNombre(editar.getNombre());
        servicio.setTipo(Serv.valueOf(tipo));
        servicio.setPrecio(editar.getPrecio());

        servicioService.save(servicio);
        return new ResponseEntity<>("COLOCACION actualizada con éxito", HttpStatus.OK);
    }

    @PutMapping("masivo")
    @Transactional
    public ResponseEntity<?> incrementarPrecios(@RequestParam double porcentaje) {
        List<ServicioDto> list = servicioService.getColocsDto();
        if (list.isEmpty()) {
            return new ResponseEntity<>("No hay COLOCACIONES cargadas", HttpStatus.BAD_REQUEST);
        }
        servicioService.incrementarPrecios(porcentaje);
        return new ResponseEntity<>("Modificación MASIVA exitosa", HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (!servicioService.existsById(id)) {
            return new ResponseEntity<>("La COLOCACION no existe", HttpStatus.NOT_FOUND);
        }
        servicioService.delete(id);
        return new ResponseEntity<>("COLOCACION borrada", HttpStatus.OK);
    }
}