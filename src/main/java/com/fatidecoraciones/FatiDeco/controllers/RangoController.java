package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.dtos.RangoDto;
import com.fatidecoraciones.FatiDeco.models.CortEspeciales;
import com.fatidecoraciones.FatiDeco.models.Rango;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import com.fatidecoraciones.FatiDeco.services.CortEspecialesService;
import com.fatidecoraciones.FatiDeco.services.RangoService;
import com.fatidecoraciones.FatiDeco.services.SistemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("rangos")
@CrossOrigin
public class RangoController {

    private final RangoService rangoService;
    private final SistemaService sistemaService;
    private final CortEspecialesService cortEspecialesService;

    public RangoController(RangoService rangoService,
                           SistemaService sistemaService, CortEspecialesService cortEspecialesService) {
        this.rangoService = rangoService;
        this.sistemaService = sistemaService;
        this.cortEspecialesService = cortEspecialesService;
    }


    @GetMapping("/{marca}")
    public ResponseEntity<?> obtenerRango(@RequestParam int alto, int ancho, String sistemaN, @PathVariable String marca) {


        Sistema sistema = sistemaService.findBySistema(sistemaN);
        if (sistema == null) {
            return new ResponseEntity<>("SISTEMA no válido: " + sistemaN, HttpStatus.BAD_REQUEST);
        }

        if (!cortEspecialesService.verificarMarca(marca)) {
            return new ResponseEntity<>("La MARCA no existe: " + marca, HttpStatus.NOT_FOUND);
        }

        RangoDto rango = rangoService.obtenerRangoDto(alto, ancho, marca, sistemaN);
        return new ResponseEntity<>(rango, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> todos() {
        List<RangoDto> rangos = rangoService.getRangosDto();
        return new ResponseEntity<>(rangos, HttpStatus.OK);
    }

    @GetMapping("/todos/{marca}")
    public ResponseEntity<?> todosXMarcaYSistema(@RequestParam String sistemaN, @PathVariable String marca) {
        List<RangoDto> rangos = rangoService.rangoXMarcaYSistema(marca, sistemaN);
        return new ResponseEntity<>(rangos, HttpStatus.OK);
    }

    @PostMapping("/nuevo/{id}")
    @Transactional
    public ResponseEntity<?> nuevo(@RequestBody RangoDto nuevo,
                                   @PathVariable Long id) {

        if (!cortEspecialesService.existById(id)) {
            return new ResponseEntity<>("El SISTEMA no existe", HttpStatus.NOT_FOUND);
        }

        CortEspeciales cortEspeciales = cortEspecialesService.getCortEspecial(id);

        if (!cortEspeciales.isEsTela()) {
            return new ResponseEntity<>("El RANGO solo puede ser asignado a una SISTEMA", HttpStatus.BAD_REQUEST);
        }
        Rango rango = new Rango(
                nuevo.getAnchoMin(),
                nuevo.getAnchoMax(),
                nuevo.getAltoMin(),
                nuevo.getAltoMax(),
                nuevo.getSistemaMin(),
                nuevo.getTelaMin()
        );

        rangoService.save(rango);

        cortEspeciales.addRango(rango);

        cortEspecialesService.save(cortEspeciales);

        return new ResponseEntity<>("RANGO creado para el SISTEMA" + cortEspeciales.getTela(), HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable Long id,
                                    @RequestBody RangoDto editar) {

//        CortEspeciales cortEspeciales = cortEspecialesService.getCortEspecialNombre(editar.getSistema());

        if (!cortEspecialesService.existById(id)) {
            return new ResponseEntity<>("No existe la TELA/SISTEMA", HttpStatus.BAD_REQUEST);
        }

        Rango rango = rangoService.getRango(id);

        rango.setAnchoMin(editar.getAnchoMin());
        rango.setAnchoMax(editar.getAnchoMax());
        rango.setAltoMin(editar.getAltoMin());
        rango.setAltoMax(editar.getAltoMax());
        rango.setSistemaMin(editar.getSistemaMin());
        rango.setTelaMin(editar.getTelaMin());

//        cortEspecialesService.save(cortEspeciales);

        return new ResponseEntity<>("RANGO modificado", HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (!rangoService.existById(id))
            return new ResponseEntity<>("El RANGO de medidas no existe", HttpStatus.NOT_FOUND);
        rangoService.delete(id);
        return new ResponseEntity<>("RANGO borrado", HttpStatus.OK);
    }

}
