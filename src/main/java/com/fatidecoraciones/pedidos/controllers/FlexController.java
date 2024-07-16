package com.fatidecoraciones.pedidos.controllers;

import com.fatidecoraciones.pedidos.criteria.FlexCriteria;
import com.fatidecoraciones.pedidos.criteria.PedidoCriteria;
import com.fatidecoraciones.pedidos.dtos.BusquedaDto;
import com.fatidecoraciones.pedidos.dtos.FlexDto;
import com.fatidecoraciones.pedidos.enums.Estado;
import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.Flex;
import com.fatidecoraciones.pedidos.models.RoyalCort;
import com.fatidecoraciones.pedidos.services.FlexService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.service.filter.StringFilter;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("flex")
@CrossOrigin
public class FlexController {
    @Autowired
    private FlexService flexService;

    @GetMapping("/lista/total")
    public ResponseEntity<List<FlexDto>> lista() {
        List<FlexDto> list = flexService.getFlexsDto();
        if (list.isEmpty())
            return new ResponseEntity("No hay TELAS cargadas", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/sistemas")
    public ResponseEntity<?> lista(@RequestParam String sistema) {
        Sistema sistemaEnum;
        try {
            sistemaEnum = Sistema.valueOf(sistema.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Sistema no válido", HttpStatus.BAD_REQUEST);
        }

        List<FlexDto> list = flexService.getSistemas(sistemaEnum);
        if (list.isEmpty())
            return new ResponseEntity<>("No hay SISTEMAS cargados", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/telas")
    public ResponseEntity<?> telas(@RequestParam String sistema) {
        Sistema telasEnum;
        try {
            telasEnum = Sistema.valueOf(sistema.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Tela no válida", HttpStatus.BAD_REQUEST);
        }

        List<FlexDto> list = flexService.getTelas(telasEnum);
        if (list.isEmpty())
            return new ResponseEntity<>("No hay TELAS cargadas", HttpStatus.NOT_FOUND);
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
    public ResponseEntity<?> getFlexByTela(@RequestParam String telaN, int alto, int ancho, String sistema) {

        Sistema sistemaEmun;
        try {
            sistemaEmun = Sistema.valueOf(sistema.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Sistema no válido", HttpStatus.BAD_REQUEST);
        }


        if (flexService.findByTela(telaN) == null)
            return new ResponseEntity<>("La TELA no existe", HttpStatus.NOT_FOUND);

        Flex VTX32 = flexService.findByTela("SISTEMA VTX15 - 32MM");
        Flex VTX38 = flexService.findByTela("SISTEMA VTX15 -38MM");
        Flex VTX45 = flexService.findByTela("SISTEMA VTX20 -45MM");

        Flex tela = flexService.findByTela(telaN);

        double sist;
        double precioSist;
        double precioTela;

        double mtTela = ancho * alto / 10000.0;

        switch (sistemaEmun) {
            case ROLLER:
                if (ancho > 59 && ancho < 100) {
                    sist = VTX32.getPrecio()*100;
                } else if (ancho >= 100 && ancho <= 160) {
                    sist = VTX32.getPrecio() * ancho;
                } else if (ancho > 160 && ancho <= 250) {
                    sist = VTX38.getPrecio() * ancho;
                } else if (ancho > 250 && ancho <= 400) {
                    sist = VTX45.getPrecio() * ancho;
                } else {
                    return new ResponseEntity<>("Fuera de RANGO", HttpStatus.OK);
                }

                if (mtTela > 1) {
                    precioTela = tela.getPrecio() * mtTela;
                } else {
                    precioTela = tela.getPrecio();
                }

                break;
            case VERTICALES:
                if (ancho > 59 && ancho < 150) {
                    sist = flexService.getSistemas(sistemaEmun).get(0).getPrecio()*150;
                } else if (ancho >= 150 && ancho <= 400) {
                    sist = flexService.getSistemas(sistemaEmun).get(0).getPrecio() * ancho;
                } else {
                    return new ResponseEntity<>("Fuera de RANGO", HttpStatus.OK);
                }

                if (mtTela > 1.5) {
                    precioTela = tela.getPrecio() * mtTela;
                } else {
                    precioTela = tela.getPrecio()*1.5;
                }
                break;
            case PERSIANA:
            case DUBAI:
            case ROMANA:
                if (ancho > 59 && ancho < 100) {
                    sist = flexService.getSistemas(sistemaEmun).get(0).getPrecio()*100;
                } else if (ancho >= 100 && ancho <= 400) {
                    sist = flexService.getSistemas(sistemaEmun).get(0).getPrecio() * ancho;
                } else {
                    return new ResponseEntity<>("Fuera de RANGO", HttpStatus.OK);
                }

                if (mtTela > 1) {
                    precioTela = tela.getPrecio() * mtTela;
                } else {
                    precioTela = tela.getPrecio();
                }
                break;
            default:
                return new ResponseEntity<>("Sistema no válido", HttpStatus.BAD_REQUEST);
        }


        precioSist = sist  / 100;

        Double total = precioTela + precioSist;

        System.out.println("Precio sistema: " + precioSist);
        System.out.println("Ancho: " + ancho);
        System.out.println("Alto: " + alto);
        System.out.println("Tela: " + tela.getTela() + " $" + tela.getPrecio());
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
                nuevo.getPrecio(),
                nuevo.isEsTela(),
                nuevo.getSistema()
        );
        flexService.save(flex);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/varios")
    @Transactional
    public ResponseEntity<List<Flex>> saveDataEntries(@RequestBody List<Flex> dataEntries) {
        List<Flex> telasFlex = dataEntries.stream()
                .map(flexService::saveVarios)
                .collect(Collectors.toList());
        return ResponseEntity.ok(telasFlex);
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody FlexDto editar) {

        if (!flexService.existById(id))
            return new ResponseEntity<>("No existe la TELA", HttpStatus.BAD_REQUEST);
        if (editar.getPrecio() == null)
            return new ResponseEntity<>("El MONTO de puede ser 0", HttpStatus.BAD_REQUEST);

        Flex flex = flexService.getFlex(id);

        flex.setTela(editar.getTela());
        flex.setPrecio(editar.getPrecio());
        flex.setEsTela(editar.isEsTela());
        flex.setSistema(editar.getSistema());

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
        return new ResponseEntity<>("Modificación MASIVA exitosa", HttpStatus.OK);
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
            if (!StringUtils.isBlank(busqueda.getSistema())) {
                FlexCriteria.SistemaFilter filter = new FlexCriteria.SistemaFilter();
                String sistema = busqueda.getSistema().toUpperCase();
                filter.setEquals(Sistema.valueOf(sistema));
                flexCriteria.setSistema(filter);
            }
        }
        return flexCriteria;
    }
}