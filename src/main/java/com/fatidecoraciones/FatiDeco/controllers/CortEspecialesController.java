package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.criteria.CortEspecialesCriteria;
import com.fatidecoraciones.FatiDeco.dtos.BusquedaDto;
import com.fatidecoraciones.FatiDeco.dtos.CortEspecialesDto;
import com.fatidecoraciones.FatiDeco.dtos.RangoDto;
import com.fatidecoraciones.FatiDeco.models.CortEspeciales;
import com.fatidecoraciones.FatiDeco.models.Marca;
import com.fatidecoraciones.FatiDeco.models.Rango;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import com.fatidecoraciones.FatiDeco.services.CortEspecialesService;
import com.fatidecoraciones.FatiDeco.services.MarcaService;
import com.fatidecoraciones.FatiDeco.services.SistemaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.StringFilter;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cortespeciales")
@CrossOrigin
public class CortEspecialesController {

    private final CortEspecialesService cortEspecialesService;
    private final MarcaService marcaService;
    private final SistemaService sistemaService;

    public CortEspecialesController(
            CortEspecialesService cortEspecialesService,
            MarcaService marcaService,
            SistemaService sistemaService
    ) {

        this.cortEspecialesService = cortEspecialesService;
        this.marcaService = marcaService;
        this.sistemaService = sistemaService;
    }


    @GetMapping("/lista/total")
    public ResponseEntity<List<CortEspecialesDto>> listaTotal() {
        List<CortEspecialesDto> list = cortEspecialesService.getListaTotalDto();
        if (list.isEmpty()) {
            return new ResponseEntity("No hay SISTEMAS ESPECIALES cargadas", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/{marca}")
    public ResponseEntity<List<CortEspecialesDto>> listaXMarca(@PathVariable String marca) {
        List<CortEspecialesDto> list = cortEspecialesService.listaXMarca(marca);
        if (list.isEmpty()) {
            return new ResponseEntity("No hay PRODUCTOS cargados con esa MARCA", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/uno/{id}")
    public ResponseEntity<CortEspecialesDto> uno(@PathVariable Long id) {

        if (!cortEspecialesService.existById(id)) {
            return new ResponseEntity("La TELA no existe o fue BORRADA", HttpStatus.NOT_FOUND);
        }

        CortEspecialesDto uno = cortEspecialesService.unoDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @GetMapping("/sistemas")
    public ResponseEntity<List<CortEspecialesDto>> mecanismosXMarcaXSistema(@RequestParam String marca, String sistema) {

        List<CortEspecialesDto> list = cortEspecialesService.getMecanismoXMarcaXSistema(sistema, marca, false);
        if (list.isEmpty()) {
            return new ResponseEntity("No hay SISTEMAS cargados", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/telas")
    public ResponseEntity<List<CortEspecialesDto>> telasXMarcaXSistema(@RequestParam String marca, String sistema, boolean adicional) {

        List<CortEspecialesDto> list = cortEspecialesService.getTelasXMarcaXSistema(adicional, marca, sistema);
        if (list.isEmpty()) {
            return new ResponseEntity("No hay TELAS cargadas", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping("/filtro")
    public ResponseEntity<List<CortEspecialesDto>> filtro(@RequestBody BusquedaDto busquedaDto) {
        CortEspecialesCriteria cortEspecialesCriteria = createCriteria(busquedaDto);
        List<CortEspeciales> lista = cortEspecialesService.findByCriteria(cortEspecialesCriteria);

        List<CortEspecialesDto> listaDto = lista.stream().map(c -> {
            CortEspecialesDto dto = new CortEspecialesDto();
            dto.setId(c.getId());
            dto.setTela(c.getTela());
            dto.setPrecio(c.getPrecio());
            dto.setEsTela(c.isEsTela());
            dto.setSistema(c.getSistema() != null ? c.getSistema().getSistema() : null);
            dto.setMarca(c.getMarca() != null ? c.getMarca().getMarca() : null);

            List<RangoDto> rangoDtos = c.getRangos().stream().map(r -> {
                RangoDto rangoDto = new RangoDto();
                rangoDto.setId(r.getId());
                rangoDto.setAltoMin(r.getAltoMin());
                rangoDto.setAltoMax(r.getAltoMax());
                rangoDto.setAnchoMin(r.getAnchoMin());
                rangoDto.setAnchoMax(r.getAnchoMax());
                rangoDto.setSistemaMin(r.getSistemaMin());
                rangoDto.setTelaMin(r.getTelaMin());
                return rangoDto;
            }).sorted(Comparator.comparingInt(RangoDto::getAnchoMin)).collect(Collectors.toList());

            dto.setRangos(rangoDtos);

            return dto;
        }).collect(Collectors.toList());

        listaDto.sort(
                Comparator.comparing(CortEspecialesDto::getMarca, Comparator.nullsLast(String::compareToIgnoreCase))
                        .thenComparing(CortEspecialesDto::getSistema, Comparator.nullsLast(String::compareToIgnoreCase))
                        .thenComparing(CortEspecialesDto::getTela, Comparator.nullsLast(String::compareToIgnoreCase))
        );

        return new ResponseEntity<>(listaDto, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody CortEspecialesDto nuevo) {

        Marca marca = marcaService.findByMarca(nuevo.getMarca());
        Sistema sistema = sistemaService.findBySistema(nuevo.getSistema());
        if (marca == null) {
            return new ResponseEntity<>("MARCA no Encontrada", HttpStatus.BAD_REQUEST);
        }
        if (sistema == null) {
            return new ResponseEntity<>("SISTEMA no Encontrado", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevo.getTela())) {
            return new ResponseEntity<>("Falta el nombre de la TELA", HttpStatus.BAD_REQUEST);
        }
        if (nuevo.getRangos().isEmpty()) {
            return new ResponseEntity<>("El MECANISMO bede tener un rango", HttpStatus.BAD_REQUEST);
        }

        CortEspeciales cortEspeciales = new CortEspeciales(

                nuevo.getTela(),
                nuevo.getPrecio(),
                false,
                false
        );

        marca.addMarcaEsp(cortEspeciales);
        sistema.addCortEspeciales(cortEspeciales);

        for (RangoDto rangoDto : nuevo.getRangos()) {
            Rango rango = new Rango();
            rango.setAltoMin(rangoDto.getAltoMin());
            rango.setAltoMax(rangoDto.getAltoMax());
            rango.setAnchoMin(rangoDto.getAnchoMin());
            rango.setAnchoMax(rangoDto.getAnchoMax());
            rango.setSistemaMin(rangoDto.getSistemaMin());
            rango.setTelaMin(rangoDto.getTelaMin());

            rango.setCortEspeciales(cortEspeciales);
            cortEspeciales.getRangos().add(rango);
        }


        cortEspecialesService.save(cortEspeciales);
        return new ResponseEntity<>("PRODUCTO creado Nº " + cortEspeciales.getId(), HttpStatus.OK);

    }

    @Transactional
    @PostMapping("/varios")
    public ResponseEntity<?> guardarVarios(@RequestBody List<CortEspecialesDto> nuevos) {

        List<String> errores = new ArrayList<>();

        for (CortEspecialesDto nuevo : nuevos) {
            boolean tela = cortEspecialesService.findByTelaAndSistemaAndMarca(nuevo.getTela(), nuevo.getSistema(), nuevo.getMarca());
            Marca marca = marcaService.findByMarca(nuevo.getMarca());
            Sistema sistema = sistemaService.findBySistema(nuevo.getSistema());

            if (tela) {
                errores.add("La TELA/SISTEMA ya existe: " + nuevo.getTela());
                continue;
            }
            if (marca == null) {
                errores.add("MARCA no encontrada para: " + nuevo.getMarca());
                continue;
            }
            if (sistema == null) {
                errores.add("SISTEMA no encontrado para: " + nuevo.getSistema());
                continue;
            }
            if (StringUtils.isBlank(nuevo.getTela())) {
                errores.add("Falta el nombre de la TELA para un producto");
                continue;
            }

            if (!marca.getSistemas().contains(sistema)) {
                errores.add("La marca '" + marca.getMarca() + "' no está relacionada con el sistema '" + sistema.getSistema() + "'");
                continue;
            }

            CortEspeciales cortEspeciales = new CortEspeciales(
                    nuevo.getTela(),
                    nuevo.getPrecio(),
                    true,
                    nuevo.isEsAdicional()
            );

            marca.addMarcaEsp(cortEspeciales);
            sistema.addCortEspeciales(cortEspeciales);
            cortEspecialesService.save(cortEspeciales);
        }

        if (!errores.isEmpty()) {
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Todos los productos fueron guardados correctamente.", HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id,
                                    @RequestBody CortEspecialesDto editar) {

        Marca marca = marcaService.findByMarca(editar.getMarca());
        Sistema sistema = sistemaService.findBySistema(editar.getSistema());

        if (!cortEspecialesService.existById(id)) {
            return new ResponseEntity<>("No existe la TELA/SISTEMA", HttpStatus.BAD_REQUEST);
        }
        if (marca == null) {
            return new ResponseEntity<>("La MARCA NO existe", HttpStatus.BAD_REQUEST);
        }
        if (sistema == null) {
            return new ResponseEntity<>("El SISTEMA NO existe", HttpStatus.BAD_REQUEST);
        }
        if (editar.getPrecio() < 0) {
            return new ResponseEntity<>("El MONTO no puede ser negativo", HttpStatus.BAD_REQUEST);
        }
        if (editar.getRangos().isEmpty() && !editar.isEsTela()) {
            return new ResponseEntity<>("El MECANISMO bede tener un rango", HttpStatus.BAD_REQUEST);
        }

        CortEspeciales cortEspeciales = cortEspecialesService.getCortEspecial(id);

        cortEspeciales.setTela(editar.getTela());
        cortEspeciales.setPrecio(editar.getPrecio());
        cortEspeciales.setEsTela(editar.isEsTela());
        cortEspeciales.setEsAdicional(editar.isEsAdicional());

        cortEspeciales.setSistema(sistema);
        cortEspeciales.setMarca(marca);

        cortEspeciales.getRangos().clear();

        for (RangoDto rangoDto : editar.getRangos()) {
            Rango rango = new Rango();
            rango.setAltoMin(rangoDto.getAltoMin());
            rango.setAltoMax(rangoDto.getAltoMax());
            rango.setAnchoMin(rangoDto.getAnchoMin());
            rango.setAnchoMax(rangoDto.getAnchoMax());
            rango.setSistemaMin(rangoDto.getSistemaMin());
            rango.setTelaMin(rangoDto.getTelaMin());

            rango.setCortEspeciales(cortEspeciales);
            cortEspeciales.getRangos().add(rango);
        }

        cortEspecialesService.save(cortEspeciales);

        return new ResponseEntity<>("PRODUCTO modificado", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (!cortEspecialesService.existById(id))
            return new ResponseEntity<>("La TELA/SISTEMA no existe", HttpStatus.NOT_FOUND);
        cortEspecialesService.delete(id);
        return new ResponseEntity<>("TELA/SISTEMA borrada", HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/masivo")
    public ResponseEntity<String> incrementarPrecios(@RequestParam String marca, double porcentaje) {
        if (marcaService.findByMarca(marca) == null) {
            return new ResponseEntity("MARCA no encontrada", HttpStatus.NOT_FOUND);
        }
        Long marcaId = marcaService.findByMarca(marca).getId();
        List<CortEspecialesDto> list = cortEspecialesService.getListaTotalDto();
        if (list.isEmpty()) {
            return new ResponseEntity("No hay TELAS/SISTEMAS cargadas", HttpStatus.BAD_REQUEST);
        }
        cortEspecialesService.incrementarPrecios(porcentaje, marcaId);
        return new ResponseEntity<>("Modificación MASIVA exitosa", HttpStatus.OK);
    }

    private CortEspecialesCriteria createCriteria(BusquedaDto busqueda) {
        CortEspecialesCriteria cortEspecialesCriteria = new CortEspecialesCriteria();
        if (busqueda != null) {

            //Tela
            if (!StringUtils.isBlank(busqueda.getTela())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getTela());
                cortEspecialesCriteria.setTela(filter);
            }

            //Marca
            if (!StringUtils.isBlank(busqueda.getMarca())) {
                StringFilter filter = new StringFilter();
                filter.setEquals(busqueda.getMarca().toUpperCase());
                cortEspecialesCriteria.setMarca(filter);
            }

            //Sistema
            if (!StringUtils.isBlank(busqueda.getSistema())) {
                StringFilter filter = new StringFilter();
                filter.setEquals(busqueda.getSistema().toUpperCase());
                cortEspecialesCriteria.setSistema(filter);
            }

            //Es Tela o Sistema
            if (!StringUtils.isBlank(busqueda.getEsTela())) {
                BooleanFilter filter = new BooleanFilter();
                if (busqueda.getEsTela().equals("false")) {
                    filter.setEquals(false);
                } else {
                    filter.setEquals(true);
                }
                cortEspecialesCriteria.setEsTela(filter);
            }
        }
        return cortEspecialesCriteria;
    }
}