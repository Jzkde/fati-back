package com.fatidecoraciones.pedidos.controllers;


import com.fatidecoraciones.pedidos.criteria.FlexCriteria;
import com.fatidecoraciones.pedidos.dtos.BusquedaDto;
import com.fatidecoraciones.pedidos.dtos.FlexDto;
import com.fatidecoraciones.pedidos.models.Flex;
import com.fatidecoraciones.pedidos.services.FlexService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.service.filter.StringFilter;

import java.util.List;

@RestController
@RequestMapping("flex")
@CrossOrigin
public class FlexController {
    @Autowired
    private FlexService flexService;

    @GetMapping("/lista")
    public ResponseEntity<List<FlexDto>> lista() {
        List<FlexDto> list = flexService.getFlexsDto();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<FlexDto> uno(@PathVariable("id") Long id) {

        if (flexService.getFlex(id) == null) {
            return new ResponseEntity("La TELA no existe o fue BORRADA", HttpStatus.BAD_REQUEST);
        }

        FlexDto uno = flexService.getFlexDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @GetMapping("/filtro/{id}")
    public ResponseEntity<List<Flex>> filtroUno(@PathVariable("id") Long id) {
        BusquedaDto busquedaDto = new BusquedaDto();
        busquedaDto.setId(id);
        FlexCriteria flexCriteria = createCriteria(busquedaDto);
        List<Flex> list = flexService.findByCriteria(flexCriteria);
        return new ResponseEntity<List<Flex>>(list, HttpStatus.OK);
    }

    @PostMapping("/filtro")
    public ResponseEntity<List<Flex>> filtro(@RequestBody BusquedaDto busquedaDto) {
        FlexCriteria flexCriteria = createCriteria(busquedaDto);
        List<Flex> list = flexService.findByCriteria(flexCriteria);
        return new ResponseEntity<List<Flex>>(list, HttpStatus.OK);
    }

    @PostMapping("/nuevo/{id}")
    public ResponseEntity<?> nuevo(@PathVariable("id") Long id,
                                   @RequestBody FlexDto nuevo) {

        if (StringUtils.isBlank(nuevo.getTela()))
            return new ResponseEntity<>("Falta la TELA", HttpStatus.BAD_REQUEST);
        if (nuevo.getPrecio() == null)
            return new ResponseEntity<>("Falta el PRECIO", HttpStatus.BAD_REQUEST);

        Flex flex = new Flex(

                nuevo.getTela(),
                nuevo.getPrecio()

        );

        flexService.save(flex);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody FlexDto editar) {

        if (StringUtils.isBlank(editar.getTela()))
            return new ResponseEntity<>("Falta la TELA", HttpStatus.BAD_REQUEST);
        if (editar.getPrecio() <= 0)
            return new ResponseEntity<>("El MONTO de puede ser 0", HttpStatus.BAD_REQUEST);

        Flex flex = flexService.getFlex(id);


        flex.setTela(editar.getTela());
        flex.setPrecio(editar.getPrecio());

        flexService.save(flex);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("masivo")
    public void incrementarPrecios(@RequestParam double porcentaje) {
        flexService.incrementarPrecios(porcentaje);
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") Long id) {
        if (!flexService.existById(id))
            return new ResponseEntity<>("La TELA no existe", HttpStatus.NOT_FOUND);
        flexService.delete(id);
        return new ResponseEntity<>("TELA borrada", HttpStatus.OK);
    }

    private FlexCriteria createCriteria(BusquedaDto busqueda) {
        FlexCriteria flexCriteria = new FlexCriteria();
        if (busqueda != null) {

            //Tela
            if (!StringUtils.isBlank(busqueda.getTela())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getTela());
                flexCriteria.setTela(filter);
            }
        }
        return flexCriteria;
    }

}