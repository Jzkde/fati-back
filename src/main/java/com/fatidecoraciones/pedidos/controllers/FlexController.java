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

import javax.transaction.Transactional;
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
        if (list.isEmpty())
            return new ResponseEntity("No hay TELAS cargadas", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<FlexDto> uno(@PathVariable("id") Long id) {

        if (flexService.getFlex(id) == null) {
            return new ResponseEntity("La TELA no existe o fue BORRADA", HttpStatus.NOT_FOUND);
        }
        FlexDto uno = flexService.getFlexDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @GetMapping("/cotizar")
    public ResponseEntity<?> getFlexByTela(@RequestParam String telaN, int alto, int ancho) {
        if (flexService.findByTela(telaN) == null)
            return new ResponseEntity<>("La TELA no existe", HttpStatus.NOT_FOUND);

        Flex VTX32 = flexService.findByTela("SISTEMA VTX15 - 32MM");
        Flex VTX38 = flexService.findByTela("SISTEMA VTX15 -38MM");
        Flex VTX45 = flexService.findByTela("SISTEMA VTX20 -45MM");

        double sist;
        double precioSist;
        double precioTela;

        if (ancho > 59 && ancho <= 160) {
            sist = VTX32.getPrecio();
        } else if (ancho > 160 && ancho <= 250) {
            sist = VTX38.getPrecio();
        } else if (ancho > 250 && ancho <= 400) {
            sist = VTX45.getPrecio();
        } else
            return new ResponseEntity<>("Fuera de RANGO", HttpStatus.OK);

        Flex tela = flexService.findByTela(telaN);

        int mtTela = ancho * alto / 10000;
        if (mtTela > 1) {
            precioTela = tela.getPrecio() * mtTela;
        } else {
            precioTela = tela.getPrecio();
        }

        if (ancho < 100) {
            precioSist = sist ;
        } else {
            precioSist = sist * ancho / 100;
        }
        Double total = precioTela + precioSist;

        System.out.println("Precio sistema: " + precioSist);
        System.out.println("Ancho: " + ancho);
        System.out.println("Alto: " + alto);
        System.out.println("Precio tela: " + precioTela);
        System.out.println("Total: " + total);
        System.out.println("-------------------------");

        return new ResponseEntity<>(total, HttpStatus.OK);

    }

    @PostMapping("/filtro")
    public ResponseEntity<List<Flex>> filtro(@RequestBody BusquedaDto busquedaDto) {
        FlexCriteria flexCriteria = createCriteria(busquedaDto);
        List<Flex> list = flexService.findByCriteria(flexCriteria);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<?> nuevo(@RequestBody FlexDto nuevo) {

        if (StringUtils.isBlank(nuevo.getTela()))
            return new ResponseEntity<>("Falta el nombre de la TELA", HttpStatus.BAD_REQUEST);
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
    @Transactional
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody FlexDto editar) {

        if (!flexService.existById(id))
            return new ResponseEntity<>("No existe la TELA", HttpStatus.BAD_REQUEST);
        if (editar.getPrecio() == null)
            return new ResponseEntity<>("El MONTO de puede ser 0", HttpStatus.BAD_REQUEST);

        Flex flex = flexService.getFlex(id);

        flex.setPrecio(editar.getPrecio());

        flexService.save(flex);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("masivo")
    @Transactional
    public ResponseEntity<?> incrementarPrecios(@RequestParam double porcentaje) {
        List<FlexDto> list = flexService.getFlexsDto();
        if (list.isEmpty())
            return new ResponseEntity("No hay TELAS cargadas", HttpStatus.BAD_REQUEST);
        flexService.incrementarPrecios(porcentaje);
        return new ResponseEntity<>("Modificacion MASIVA exitosa", HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
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