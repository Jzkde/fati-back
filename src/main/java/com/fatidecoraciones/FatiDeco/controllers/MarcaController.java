package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.dtos.MarcaDto;
import com.fatidecoraciones.FatiDeco.models.Marca;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import com.fatidecoraciones.FatiDeco.services.MarcaService;
import com.fatidecoraciones.FatiDeco.services.SistemaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("marca")
@CrossOrigin
public class MarcaController {

    private final MarcaService marcaService;
    private final SistemaService sistemaService;

    public MarcaController(MarcaService marcaService,
                           SistemaService sistemaService) {
        this.marcaService = marcaService;
        this.sistemaService = sistemaService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<MarcaDto>> lista() {
        List<MarcaDto> list = marcaService.getMarcasDto();
        if (list.isEmpty()) {
            return new ResponseEntity("No hay PROVEEDORES/MARCAS cargadas", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/sistema")
    public ResponseEntity<List<MarcaDto>> sistemas(@RequestParam boolean esSistema) {

        List<MarcaDto> list = marcaService.getMarcasDtoSistemas(esSistema);
        if (list.isEmpty()) {
            return new ResponseEntity("No hay PROVEEDORES/MARCAS cargadas", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/uno/{id}")
    public ResponseEntity<MarcaDto> uno(@PathVariable Long id) {


        if (!marcaService.existById(id)) {
            return new ResponseEntity("El PROVEEDOR no existe o fue BORRADO", HttpStatus.NOT_FOUND);
        }
        MarcaDto uno = marcaService.getMarcaDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<?> nuevo(@RequestBody MarcaDto nuevo) {

        if (marcaService.findByMarca(nuevo.getMarca().trim()) != null) {
            return new ResponseEntity<>("La MARCA ya existe", HttpStatus.FORBIDDEN);
        }
        if (StringUtils.isBlank(nuevo.getMarca())) {
            return new ResponseEntity<>("Falta el nombre del PROVEEDOR", HttpStatus.BAD_REQUEST);
        }
        Marca marca = new Marca(
                nuevo.getMarca().trim().toUpperCase(),
                nuevo.getNombre().trim(),
                nuevo.isEsSistema()
        );
        marcaService.save(marca);
        return new ResponseEntity<>("PROVEEDOR creado con éxito: " + marca.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable Long id,
                                    @RequestBody MarcaDto editar) {

        if (!marcaService.existById(id)) {
            return new ResponseEntity<>("No existe el PROVEEDOR", HttpStatus.NOT_FOUND);
        }
        if (marcaService.findByMarca(editar.getMarca().trim()) != null) {
            return new ResponseEntity<>("La MARCA ya existe", HttpStatus.FORBIDDEN);
        }
        Marca marca = marcaService.getMarca(id);

        marca.setMarca(editar.getMarca().toUpperCase().trim());
        marca.setNombre(editar.getNombre().trim());
        marca.setEsSistema(editar.isEsSistema());

        marcaService.save(marca);
        return new ResponseEntity<>("PROVEEDOR actualizado", HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (!marcaService.existById(id)) {
            return new ResponseEntity<>("El PROVEEDOR no existe", HttpStatus.NOT_FOUND);
        }

        // Validar si el cliente tiene presupuestos
        if (marcaService.tieneProductos(id)) {
            return new ResponseEntity<>("No se puede eliminar la MARCA porque tiene elementos asociados", HttpStatus.BAD_REQUEST);
        }

        marcaService.delete(id);
        return new ResponseEntity<>("MARCA eliminada", HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<MarcaDto> buscar(@RequestParam String marcaN) {
        MarcaDto marca = marcaService.findByMarcaDto(marcaN);
        if (marca == null) {
            return new ResponseEntity("MARCA no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(marca, HttpStatus.OK);
    }

    @PutMapping("/agregar-sistema/{id}")
    public ResponseEntity<?> agregarSistemasAMarca(@PathVariable Long id, @RequestParam Long sistemaId) {
        Marca marca = marcaService.findById(id);
        if (marca == null) {
            return new ResponseEntity<>("La MARCA no existe", HttpStatus.NOT_FOUND);
        }

        Sistema sistema = sistemaService.findById(sistemaId);

        if (marca.getSistemas() .contains(sistema)) {
            return new ResponseEntity<>("La MARCA y el SISTEMA ya estan asociados", HttpStatus.BAD_REQUEST);
        }

        marca.addSistema(sistema);

        marcaService.save(marca);
        return new ResponseEntity<>("MARCA actualizada", HttpStatus.OK);
    }

}