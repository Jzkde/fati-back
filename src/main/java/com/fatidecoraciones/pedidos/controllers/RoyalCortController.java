package com.fatidecoraciones.pedidos.controllers;

import com.fatidecoraciones.pedidos.criteria.FlexCriteria;
import com.fatidecoraciones.pedidos.criteria.RoyalCortCriteria;
import com.fatidecoraciones.pedidos.dtos.BusquedaDto;
import com.fatidecoraciones.pedidos.dtos.RoyalCortDto;
import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.RoyalCort;
import com.fatidecoraciones.pedidos.services.RoyalCortService;
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
@RequestMapping("royal")
@CrossOrigin
public class RoyalCortController {


    @Autowired
    private RoyalCortService royalCortService;

    @GetMapping("/lista/total")
    public ResponseEntity<List<RoyalCortDto>> lista() {
        List<RoyalCortDto> list = royalCortService.getRCsDto();
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

        List<RoyalCortDto> list = royalCortService.getSistemas(sistemaEnum);
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

        List<RoyalCortDto> list = royalCortService.getTelas(telasEnum);
        if (list.isEmpty())
            return new ResponseEntity<>("No hay TELAS cargadas", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<RoyalCortDto> uno(@PathVariable("id") Long id) {

        if (royalCortService.getRC(id) == null) {
            return new ResponseEntity("La TELA no existe o fue BORRADA", HttpStatus.NOT_FOUND);
        }
        RoyalCortDto uno = royalCortService.getRCDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @GetMapping("/cotizar")
    public ResponseEntity<?> getRotalCortByTela(@RequestParam String telaN, int alto, int ancho, String sistema) {

        Sistema sistemaEmun;
        try {
            sistemaEmun = Sistema.valueOf(sistema.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Sistema no válido", HttpStatus.BAD_REQUEST);
        }

        if (royalCortService.findByTela(telaN) == null)
            return new ResponseEntity<>("La TELA no existe", HttpStatus.NOT_FOUND);

        RoyalCort VTX32 = royalCortService.findByTela("RC Sistema Roller 32Mm");
        RoyalCort VTX38 = royalCortService.findByTela("RC Sistema Roller 38Mm");
        RoyalCort VTX45 = royalCortService.findByTela("RC Sistema Roller 45Mm");

        RoyalCort tela = royalCortService.findByTela(telaN);

        double sist;
        double precioSist;
        double precioTela;

        double mtTela = ancho * alto / 10000.0;

        switch (sistemaEmun) {
            case ROLLER:
                if ((ancho >= 60 && ancho <= 180 && alto <= 240) ||
                        (ancho > 180 && ancho <= 210 && alto <= 200) ||
                        (ancho > 210 && ancho <= 240 && alto <= 100)) {
                    sist = VTX32.getPrecio() * ancho;
                } else if ((ancho <= 60 && alto > 180 && alto <= 280) ||
                        (ancho > 60 && ancho <= 90 && alto > 240 && alto <= 280) ||
                        (ancho > 90 && ancho <= 180 && alto > 240 && alto <= 320) ||
                        (ancho > 180 && ancho <= 210 && alto > 220 && alto <= 320) ||
                        (ancho > 210 && ancho <= 240 && alto > 100 && alto <= 320) ||
                        (ancho > 240 && ancho <= 270 && alto <= 220) ||
                        (ancho > 270 && ancho <= 300 && alto <= 120)) {
                    sist = VTX38.getPrecio() * ancho;
                } else if ((ancho >= 90 && ancho <= 240 && alto > 320 && alto <= 380) ||
                        (ancho > 240 && ancho <= 270 && alto > 240 && alto <= 380) ||
                        (ancho > 270 && ancho <= 300 && alto > 140 && alto <= 260) ||
                        (ancho > 300 && ancho <= 330 && alto <= 240) ||
                        (ancho > 360 && ancho <= 360 && alto <= 120)) {
                    sist = VTX45.getPrecio() * ancho;
                } else {
                    return new ResponseEntity<>("Fuera de RANGO", HttpStatus.BAD_REQUEST);
                }

                if (mtTela > 1) {
                    precioTela = tela.getPrecio() * mtTela;
                } else {
                    precioTela = tela.getPrecio();
                }

                break;
            case ZEBRA:
                if (ancho > 59 && ancho < 150) {
                    sist = royalCortService.getSistemas(sistemaEmun).get(0).getPrecio() * 150;
                } else if (ancho >= 150 && ancho <= 400) {
                    sist = royalCortService.getSistemas(sistemaEmun).get(0).getPrecio() * ancho;
                } else {
                    return new ResponseEntity<>("Fuera de RANGO", HttpStatus.OK);
                }

                if (mtTela > 1.5) {
                    precioTela = tela.getPrecio() * mtTela;
                } else {
                    precioTela = tela.getPrecio() * 1.5;
                }
                break;
            default:
                return new ResponseEntity<>("Sistema no válido", HttpStatus.BAD_REQUEST);
        }

        precioSist = sist / 100;
        Double total = precioTela + precioSist;

        System.out.println("sistema: " + precioSist / ancho * 100);
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
    public ResponseEntity<List<RoyalCort>> filtro(@RequestBody BusquedaDto busquedaDto) {
        RoyalCortCriteria royalCortCriteria = createCriteria(busquedaDto);
        List<RoyalCort> list = royalCortService.findByCriteria(royalCortCriteria);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<?> nuevo(@RequestBody RoyalCortDto nuevo) {

        if (StringUtils.isBlank(nuevo.getTela()))
            return new ResponseEntity<>("Falta el nombre de la TELA", HttpStatus.BAD_REQUEST);
        if (nuevo.getPrecio() == null)
            return new ResponseEntity<>("Falta el PRECIO", HttpStatus.BAD_REQUEST);

        RoyalCort royalCort = new RoyalCort(

                nuevo.getTela(),
                nuevo.getPrecio(),
                nuevo.isEsTela(),
                nuevo.getSistema()
        );
        royalCortService.save(royalCort);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/varios")
    @Transactional
    public ResponseEntity<List<RoyalCort>> saveDataEntries(@RequestBody List<RoyalCort> dataEntries) {
        List<RoyalCort> telasRoyal = dataEntries.stream()
                .map(royalCortService::saveVarios)
                .collect(Collectors.toList());
        return ResponseEntity.ok(telasRoyal);
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody RoyalCortDto editar) {

        if (!royalCortService.existById(id))
            return new ResponseEntity<>("No existe la TELA", HttpStatus.BAD_REQUEST);
        if (editar.getPrecio() == null)
            return new ResponseEntity<>("El MONTO de puede ser 0", HttpStatus.BAD_REQUEST);

        RoyalCort royalCort = royalCortService.getRC(id);

        royalCort.setTela(editar.getTela());
        royalCort.setPrecio(editar.getPrecio());
        royalCort.setEsTela(editar.isEsTela());
        royalCort.setSistema(editar.getSistema());

        royalCortService.save(royalCort);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("masivo")
    @Transactional
    public ResponseEntity<?> incrementarPrecios(@RequestParam double porcentaje) {
        List<RoyalCortDto> list = royalCortService.getRCsDto();
        if (list.isEmpty())
            return new ResponseEntity("No hay TELAS cargadas", HttpStatus.BAD_REQUEST);
        royalCortService.incrementarPrecios(porcentaje);
        return new ResponseEntity<>("Modificación MASIVA exitosa", HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable("id") Long id) {
        if (!royalCortService.existById(id))
            return new ResponseEntity<>("La TELA no existe", HttpStatus.NOT_FOUND);
        royalCortService.delete(id);
        return new ResponseEntity<>("TELA borrada", HttpStatus.OK);
    }

    private RoyalCortCriteria createCriteria(BusquedaDto busqueda) {
        RoyalCortCriteria royalCortCriteria = new RoyalCortCriteria();
        if (busqueda != null) {

            //Tela
            if (!StringUtils.isBlank(busqueda.getTela())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getTela());
                royalCortCriteria.setTela(filter);
            }
            if (!StringUtils.isBlank(busqueda.getSistema())) {
                RoyalCortCriteria.SistemaFilter filter = new RoyalCortCriteria.SistemaFilter();
                String sistema = busqueda.getSistema().toUpperCase();
                filter.setEquals(Sistema.valueOf(sistema));
                royalCortCriteria.setSistema(filter);
            }
        }
        return royalCortCriteria;
    }
}