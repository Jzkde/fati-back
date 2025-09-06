package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.criteria.TallerCriteria;
import com.fatidecoraciones.FatiDeco.dtos.BusquedaDto;
import com.fatidecoraciones.FatiDeco.dtos.TallerDto;
import com.fatidecoraciones.FatiDeco.dB.enums.Apertura;
import com.fatidecoraciones.FatiDeco.dB.enums.Estado;
import com.fatidecoraciones.FatiDeco.models.Cliente;
import com.fatidecoraciones.FatiDeco.models.Taller;
import com.fatidecoraciones.FatiDeco.services.ClienteService;
import com.fatidecoraciones.FatiDeco.services.TallerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.StringFilter;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("taller")
@CrossOrigin
public class TallerController {

    private final TallerService tallerService;
    private final ClienteService clienteService;

    public TallerController(TallerService tallerService, ClienteService clienteService) {
        this.tallerService = tallerService;
        this.clienteService = clienteService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<TallerDto>> lista() {
        List<TallerDto> list = tallerService.getTallerDtos();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<TallerDto> uno(@PathVariable Long id) {

        if (tallerService.getTaller(id) == null) {
            return new ResponseEntity("El PEDIDO de CONFECCION no existe o fue BORRADO", HttpStatus.NOT_FOUND);
        }
        TallerDto uno = tallerService.getTallerDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @PostMapping("/filtro")
    public ResponseEntity<List<TallerDto>> filtro(@RequestBody BusquedaDto busquedaDto) {
        TallerCriteria tallerCriteria = createCriteria(busquedaDto);
        List<Taller> lista = tallerService.findByCriteria(tallerCriteria);

        List<TallerDto> listaDto = lista.stream().map(taller -> {
            TallerDto tallerDto = new TallerDto();
            tallerDto.setId(taller.getId());
            tallerDto.setAncho(taller.getAncho());
            tallerDto.setAlto(taller.getAlto());
            tallerDto.setApertura(taller.getApertura());
            tallerDto.setAccesorios(taller.getAccesorios());
            tallerDto.setAmbiente(taller.getAmbiente());
            tallerDto.setObservaciones(taller.getObservaciones());
            tallerDto.setFecha_pedido(taller.getFecha_pedido());
            tallerDto.setFecha_entrega(taller.getFecha_entrega());
            tallerDto.setEstado(taller.getEstado());
            tallerDto.setLlego(taller.getLlego());
            tallerDto.setCliente(taller.getCliente() != null ? taller.getCliente().getNombre() : null);
            return tallerDto;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(listaDto, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<String> nuevo(@RequestBody TallerDto nuevo) {

        Cliente cliente;

        if (clienteService.existByNombre(nuevo.getCliente())) {
            cliente = clienteService.findByNombre(nuevo.getCliente());
        } else {
            cliente = new Cliente();
            cliente.setNombre(nuevo.getCliente());
            cliente = clienteService.save(cliente);
        }

        if (nuevo.getAncho() <= 0) {
            return new ResponseEntity<>("El ANCHO no puede ser 0 o nagativo", HttpStatus.BAD_REQUEST);
        }
        if (nuevo.getAlto() <= 0) {
            return new ResponseEntity<>("El ALTO no puede ser 0 o nagativo", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevo.getCliente())) {
            return new ResponseEntity<>("Falta el NOMBRE del CLIENTE", HttpStatus.BAD_REQUEST);
        }
        Taller taller = new Taller(nuevo.getAncho(), nuevo.getAlto(), Apertura.NO_POSEE, nuevo.getAccesorios(), nuevo.getAmbiente(), nuevo.getObservaciones(), nuevo.getCliente(), LocalDate.now(), Estado.PEDIDO, false);
        cliente.addTaller(taller);
        tallerService.save(taller);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/mover")
    @Transactional
    public ResponseEntity<String> mover(@RequestBody List<TallerDto> listaMover) {

        List<Taller> listaTalleres = new ArrayList<>();

        for (TallerDto mover : listaMover) {

            if (StringUtils.isBlank(mover.getCliente())) {
                return new ResponseEntity<>("Falta el NOMBRE del CLIENTE", HttpStatus.BAD_REQUEST);
            }

            if (mover.getAncho() <= 0) {
                return new ResponseEntity<>("El ANCHO no puede ser 0 o negativo", HttpStatus.BAD_REQUEST);
            }

            if (mover.getAlto() <= 0) {
                return new ResponseEntity<>("El ALTO no puede ser 0 o negativo", HttpStatus.BAD_REQUEST);
            }

            // Verificamos o creamos cliente
            Cliente cliente;
            if (clienteService.existByNombre(mover.getCliente())) {
                cliente = clienteService.findByNombre(mover.getCliente());
            } else {
                cliente = new Cliente();
                cliente.setNombre(mover.getCliente());
                cliente = clienteService.save(cliente);
            }

            Taller taller = new Taller(
                    mover.getAncho(),
                    mover.getAlto(),
                    mover.getApertura(),
                    mover.getAccesorios(),
                    mover.getAmbiente(),
                    mover.getObservaciones(),
                    mover.getCliente(),
                    LocalDate.now(),
                    Estado.PEDIDO,
                    false
            );

            cliente.addTaller(taller);
            listaTalleres.add(taller);
        }

        tallerService.saveAll(listaTalleres);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/actualizar/{id}")
    @Transactional
    public ResponseEntity<String> actualizar(@PathVariable Long id) {
        if (!tallerService.existsById(id)) {
            return new ResponseEntity<>("No Existe", HttpStatus.NOT_FOUND);
        }

        Taller taller = tallerService.getTaller(id);
        if (!taller.getLlego()) {
            taller.setLlego(true);
            taller.setFecha_entrega(LocalDate.now());
            taller.setEstado(Estado.ENTREGADO_COLOCADO);
            tallerService.save(taller);
            return new ResponseEntity<>("La Cortina LLEGO", HttpStatus.OK);
        } else {
            taller.setLlego(false);
            taller.setFecha_entrega(null);
            taller.setEstado(Estado.PEDIDO);
            tallerService.save(taller);
            return new ResponseEntity<>("La Cortina NO LLEGO", HttpStatus.OK);
        }
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody TallerDto editar) {

        if (editar.getAncho() <= 0) {
            return new ResponseEntity<>("El ANCHO no puede ser 0 o nagativo", HttpStatus.BAD_REQUEST);
        }
        if (editar.getAlto() <= 0) {
            return new ResponseEntity<>("El ALTO no puede ser 0 o nagativo", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(editar.getCliente())) {
            return new ResponseEntity<>("Falta el NOMBRE del CLIENTE", HttpStatus.BAD_REQUEST);
        }
        if (editar.getFecha_pedido() == null) {
            return new ResponseEntity<>("Falta la FECHA de la CONFECCION", HttpStatus.BAD_REQUEST);
        }

        Taller taller = tallerService.getTaller(id);

        if (taller == null) {
            return new ResponseEntity<>("El presupuesto no existe", HttpStatus.NOT_FOUND);
        }

        if (taller.getCliente() == null || !taller.getCliente().getNombre().equals(editar.getCliente())) {

            Cliente cliente;

            if (clienteService.existByNombre(editar.getCliente())) {
                cliente = clienteService.findByNombre(editar.getCliente());
            } else {
                cliente = new Cliente();
                cliente.setNombre(editar.getCliente());
                cliente = clienteService.save(cliente);
            }

            cliente.setNombre(editar.getCliente());
            cliente.addTaller(taller);
        }

        taller.setAncho(editar.getAncho());
        taller.setAlto(editar.getAlto());
        taller.setApertura(editar.getApertura());
        taller.setAccesorios(editar.getAccesorios());
        taller.setAmbiente(editar.getAmbiente());
        taller.setObservaciones(editar.getObservaciones());
        taller.setFecha_pedido(editar.getFecha_pedido());
        taller.setFecha_entrega(editar.getFecha_entrega());
        taller.setEstado(editar.getEstado());
        taller.setLlego(editar.getLlego());

        if (editar.getEstado() == Estado.PEDIDO || editar.getEstado() == Estado.EN_TRANSPORTE) {
            taller.setFecha_entrega(null);
            taller.setLlego(false);
        }
        if (editar.getEstado() == Estado.LLEGO || editar.getEstado() == Estado.ENTREGADO_COLOCADO && taller.getFecha_entrega() == null) {
            taller.setFecha_entrega(LocalDate.now());
            taller.setLlego(true);
        }

        tallerService.save(taller);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (!tallerService.existsById(id)) {
            return new ResponseEntity<>("La CONFECCION no existe", HttpStatus.NOT_FOUND);
        }
        tallerService.delete(id);
        return new ResponseEntity<>("CONFECCION borrada", HttpStatus.OK);
    }

    private TallerCriteria createCriteria(BusquedaDto busqueda) {
        TallerCriteria tallerCriteria = new TallerCriteria();
        if (busqueda != null) {
            //Fecha de Taller
            if (busqueda.getFecha_tallerDesde() != null || busqueda.getFecha_tallerHasta() != null) {
                LocalDateFilter filter = new LocalDateFilter();
                if (busqueda.getFecha_tallerDesde() != null) {
                    filter.setGreaterThanOrEqual(busqueda.getFecha_tallerDesde());
                    tallerCriteria.setFecha_pedido(filter);
                }
                if (busqueda.getFecha_tallerHasta() != null) {
                    filter.setLessThanOrEqual(busqueda.getFecha_tallerHasta());
                    tallerCriteria.setFecha_pedido(filter);
                }
            }
            //Entregado
            if (!StringUtils.isBlank(busqueda.getLlego())) {
                BooleanFilter filter = new BooleanFilter();
                if (busqueda.getLlego().equals("true")) {
                    filter.setEquals(true);
                } else {
                    filter.setEquals(false);
                }
                tallerCriteria.setLlego(filter);
            }
            //Cliente
            if (!StringUtils.isBlank(busqueda.getCliente())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getCliente());
                tallerCriteria.setCliente(filter);
            }
            //Estado
            if (!StringUtils.isBlank(busqueda.getEstado())) {
                TallerCriteria.EstadoFilter filter = new TallerCriteria.EstadoFilter();
                String estado = busqueda.getEstado().toUpperCase();
                filter.setEquals(Estado.valueOf(estado));
                tallerCriteria.setEstado(filter);
            }
        }
        return tallerCriteria;
    }
}