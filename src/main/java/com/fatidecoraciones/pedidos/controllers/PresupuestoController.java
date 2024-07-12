package com.fatidecoraciones.pedidos.controllers;

import com.fatidecoraciones.pedidos.criteria.PresupuestoCriteria;
import com.fatidecoraciones.pedidos.dtos.BusquedaDto;
import com.fatidecoraciones.pedidos.dtos.PresupuestoDto;
import com.fatidecoraciones.pedidos.enums.Apertura;
import com.fatidecoraciones.pedidos.enums.Comando;
import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.Cliente;
import com.fatidecoraciones.pedidos.models.Presupuesto;
import com.fatidecoraciones.pedidos.repositories.ClienteRepository;
import com.fatidecoraciones.pedidos.repositories.PresupuestoRepository;
import com.fatidecoraciones.pedidos.services.ClienteService;
import com.fatidecoraciones.pedidos.services.PresupuestoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.service.filter.StringFilter;

import java.util.List;

@RestController
@RequestMapping("presupuesto")
@CrossOrigin
public class PresupuestoController {

    @Autowired
    private PresupuestoRepository presupuestoRepository;
    @Autowired
    private PresupuestoService presupuestoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/lista")
    public ResponseEntity<List<PresupuestoDto>> lista() {
        List<PresupuestoDto> list = presupuestoService.getPresupuestoDto();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<PresupuestoDto> uno(@PathVariable("id") Long id) {

        if (!presupuestoService.existById(id)) {
            return new ResponseEntity("El CLIENTE no existe", HttpStatus.BAD_REQUEST);
        }

        PresupuestoDto uno = presupuestoService.getPresupuestoDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @GetMapping("/filtro/{id}")
    public ResponseEntity<List<Presupuesto>> filtroUno(@PathVariable("id") Long id) {
        BusquedaDto busquedaDto = new BusquedaDto();
        busquedaDto.setClienteNombre(clienteService.getCliente(id).getNombre() + " " + clienteService.getCliente(id).getApellido());
        PresupuestoCriteria presupuestoCriteria = createCriteria(busquedaDto);
        List<Presupuesto> list = presupuestoService.findByCriteria(presupuestoCriteria);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/filtro")
    public ResponseEntity<List<Presupuesto>> filtro(@RequestBody BusquedaDto busquedaDto) {
        PresupuestoCriteria presupuestoCriteria = createCriteria(busquedaDto);
        List<Presupuesto> list = presupuestoService.findByCriteria(presupuestoCriteria);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/nuevo/{id}")
    public ResponseEntity<?> nuevo(@PathVariable("id") Long id,
                                   @RequestBody PresupuestoDto nuevo) {

        if (nuevo.getSistema() == null)
            return new ResponseEntity<>("Falta el tipo de SISTEMA", HttpStatus.BAD_REQUEST);
        if (nuevo.getAlto() == 0)
            return new ResponseEntity<>("Falta el ALTO", HttpStatus.BAD_REQUEST);
        if (nuevo.getAncho() == 0)
            return new ResponseEntity<>("Falta el ANCHO", HttpStatus.BAD_REQUEST);
        if (nuevo.getComando() == null)
            return new ResponseEntity<>("Falta el lado del COMANDO", HttpStatus.BAD_REQUEST);
        if (nuevo.getApertura() == null)
            return new ResponseEntity<>("Falta el tipo de APERTURA", HttpStatus.BAD_REQUEST);
        if (
                (nuevo.getSistema() == Sistema.DUBAI ||
                        nuevo.getSistema() == Sistema.PERCIANA ||
                        nuevo.getSistema() == Sistema.ROLLER ||
                        nuevo.getSistema() == Sistema.ORIENTAL) && nuevo.getApertura() != Apertura.NO_POSEE)

            return new ResponseEntity<>("Este SISTEMA NO tiene APERTURA", HttpStatus.BAD_REQUEST);
        if (
               nuevo.getSistema() == Sistema.TELA && nuevo.getComando() != Comando.NO_POSEE)

            return new ResponseEntity<>("Este SISTEMA NO tiene COMANDO", HttpStatus.BAD_REQUEST);


        Cliente cliente = clienteService.getCliente(id);

        if (
                nuevo.getSistema() == Sistema.DUBAI ||
                        nuevo.getSistema() == Sistema.PERCIANA ||
                        nuevo.getSistema() == Sistema.ROLLER ||
                        nuevo.getSistema() == Sistema.ORIENTAL) {

            Presupuesto presupuesto = new Presupuesto(
                    nuevo.getSistema(),
                    nuevo.getAncho(),
                    nuevo.getAlto(),
                    nuevo.getComando(),
                    Apertura.NO_POSEE,
                    nuevo.getAccesorios(),
                    nuevo.getAmbiente(),
                    nuevo.getObservaciones()
            );

            cliente.addPresupuesto(presupuesto);
            clienteRepository.save(cliente);
            presupuestoRepository.save(presupuesto);
        }
        if (
                nuevo.getSistema() == Sistema.TELA) {

            Presupuesto presupuesto = new Presupuesto(
                    nuevo.getSistema(),
                    nuevo.getAncho(),
                    nuevo.getAlto(),
                    Comando.NO_POSEE,
                    nuevo.getApertura(),
                    nuevo.getAccesorios(),
                    nuevo.getAmbiente(),
                    nuevo.getObservaciones()
            );

            cliente.addPresupuesto(presupuesto);
            clienteRepository.save(cliente);
            presupuestoRepository.save(presupuesto);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody PresupuestoDto editar) {

        if (editar.getSistema() == null)
            return new ResponseEntity<>("Falta el tipo de SISTEMA", HttpStatus.BAD_REQUEST);
        if (editar.getAlto() == 0)
            return new ResponseEntity<>("Falta el ALTO", HttpStatus.BAD_REQUEST);
        if (editar.getAncho() == 0)
            return new ResponseEntity<>("Falta el ANCHO", HttpStatus.BAD_REQUEST);
        if (editar.getComando() == null)
            return new ResponseEntity<>("Falta el lado del COMANDO", HttpStatus.BAD_REQUEST);
        if (editar.getApertura() == null)
            return new ResponseEntity<>("Falta el tipo de APERTURA", HttpStatus.BAD_REQUEST);

        if (
                (editar.getSistema() == Sistema.DUBAI ||
                        editar.getSistema() == Sistema.PERCIANA ||
                        editar.getSistema() == Sistema.ROLLER ||
                        editar.getSistema() == Sistema.ORIENTAL) && editar.getApertura() != Apertura.NO_POSEE)

            return new ResponseEntity<>("Este SISTEMA NO tiene APERTURA", HttpStatus.BAD_REQUEST);
        if (
                editar.getSistema() == Sistema.TELA && editar.getComando() != Comando.NO_POSEE)

            return new ResponseEntity<>("Este SISTEMA NO tiene COMANDO", HttpStatus.BAD_REQUEST);


        Presupuesto presupuesto = presupuestoService.getPresupuesto(id);

        presupuesto.setSistema(editar.getSistema());
        presupuesto.setAncho(editar.getAncho());
        presupuesto.setAlto(editar.getAlto());
        presupuesto.setComando(editar.getComando());
        presupuesto.setApertura(editar.getApertura());
        presupuesto.setAccesorios(editar.getAccesorios());
        presupuesto.setAmbiente(editar.getAmbiente());
        presupuesto.setObservaciones(editar.getObservaciones());


        presupuestoRepository.save(presupuesto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") Long id) {
        if (!presupuestoService.existById(id))
            return new ResponseEntity<>("El PRESUPUESTO no existe", HttpStatus.NOT_FOUND);
        presupuestoRepository.deleteById(id);
        return new ResponseEntity<>("PRESUPUESTO borrado", HttpStatus.OK);
    }

    private PresupuestoCriteria createCriteria(BusquedaDto busqueda) {
        PresupuestoCriteria presupuestoCriteria = new PresupuestoCriteria();
        if (busqueda != null) {

            //Cliente
            if (!StringUtils.isBlank(busqueda.getClienteNombre())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getClienteNombre());
                presupuestoCriteria.setClienteNombre(filter);
            }
        }
        return presupuestoCriteria;
    }

}
