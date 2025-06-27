package com.fatidecoraciones.FatiDeco.controllers;


import com.fatidecoraciones.FatiDeco.criteria.PedidoCriteria;
import com.fatidecoraciones.FatiDeco.dtos.BusquedaDto;
import com.fatidecoraciones.FatiDeco.dtos.PedidoDto;
import com.fatidecoraciones.FatiDeco.enums.Estado;
import com.fatidecoraciones.FatiDeco.models.Pedido;
import com.fatidecoraciones.FatiDeco.services.PedidoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.StringFilter;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("pedido")
@CrossOrigin
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PedidoDto>> lista() {
        List<PedidoDto> list = pedidoService.getPedidosDto();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/uno/{id}")
    public ResponseEntity<PedidoDto> uno(@PathVariable Long id) {

        if (pedidoService.getPedido(id) == null) {
            return new ResponseEntity("El PEDIDO no existe o fue BORRADO", HttpStatus.NOT_FOUND);
        }
        PedidoDto uno = pedidoService.getPedidoDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @PostMapping("/filtro")
    public ResponseEntity<List<Pedido>> filtro(@RequestBody BusquedaDto busquedaDto) {
        PedidoCriteria pedidoCriteria = createCriteria(busquedaDto);
        List<Pedido> list = pedidoService.findByCriteria(pedidoCriteria);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<String> nuevo(@RequestBody PedidoDto nuevo) {

        if (StringUtils.isBlank(nuevo.getProvedor())) {
            return new ResponseEntity<>("Falta el PROVEDOR", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevo.getVia())) {
            return new ResponseEntity<>("Falta el MEDIO por que se realiza el pedido", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevo.getN_pedido())) {
            return new ResponseEntity<>("Falta el NUMERO de PEDIDO", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevo.getN_factura())) {
            return new ResponseEntity<>("Falta el NUMERO de FACTURA", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevo.getN_remito())) {
            return new ResponseEntity<>("Falta el NUMERO de REMITO", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(nuevo.getResponsable())) {
            return new ResponseEntity<>("Falta el RESPONSABLE del pedido", HttpStatus.BAD_REQUEST);
        }

        Pedido pedido = new Pedido(

                LocalDate.now(),
                nuevo.getProvedor(),
                nuevo.getVia(),
                nuevo.getN_pedido(),
                nuevo.getN_factura(),
                nuevo.getN_remito(),
                nuevo.getMonto(),
                false,
                Estado.PEDIDO,
                nuevo.getClienteNombre().trim(),
                nuevo.getResponsable(),
                nuevo.getObservaciones()
        );
        pedidoService.save(pedido);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    @Transactional
    public ResponseEntity<String> actualizar(@PathVariable Long id) {

        if (!pedidoService.existById(id)) {
            return new ResponseEntity<>("El PEDIDO no Existe", HttpStatus.NOT_FOUND);
        }
        Pedido pedido = pedidoService.getPedido(id);
        if (!pedido.isLlego()) {
            pedido.setLlego(true);
            pedido.setFecha_llegada(LocalDate.now());
            pedido.setEstado(Estado.LLEGO);
            pedidoService.save(pedido);
            return new ResponseEntity<>("El PEDIDO LLEGO", HttpStatus.OK);
        } else {
            pedido.setLlego(false);
            pedido.setFecha_llegada(null);
            pedido.setEstado(Estado.PEDIDO);
            pedidoService.save(pedido);
            return new ResponseEntity<>("El PEDIDO NO Llego", HttpStatus.OK);
        }
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody PedidoDto editar) {

        if (editar.getFecha_pedido() == null) {
            return new ResponseEntity<>("Falta la FECHA DEL PEDIDO", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(editar.getProvedor())) {
            return new ResponseEntity<>("Falta el PROVEDOR", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(editar.getVia())) {
            return new ResponseEntity<>("Falta el MEDIO por que se realiza el pedido", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(editar.getN_pedido())) {
            return new ResponseEntity<>("Falta el NUMERO de PEDIDO", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(editar.getN_factura())) {
            return new ResponseEntity<>("Falta el NUMERO de FACTURA", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(editar.getN_remito())) {
            return new ResponseEntity<>("Falta el NUMERO de REMITO", HttpStatus.BAD_REQUEST);
        }
        if (editar.getMonto() <= 0) {
            return new ResponseEntity<>("El MONTO de puede ser 0 o negativo", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(editar.getEstado().name())) {
            return new ResponseEntity<>("Falta el ESTADO del pedido", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(editar.getResponsable())) {
            return new ResponseEntity<>("Falta el RESPONSABLE del pedido", HttpStatus.BAD_REQUEST);
        }

        Pedido pedido = pedidoService.getPedido(id);

        pedido.setFecha_pedido(editar.getFecha_pedido());
        pedido.setVia(editar.getVia());
        pedido.setProvedor(editar.getProvedor());
        pedido.setN_pedido(editar.getN_pedido());
        pedido.setN_factura(editar.getN_factura());
        pedido.setN_remito(editar.getN_remito());
        pedido.setMonto(editar.getMonto());
        pedido.setEstado(editar.getEstado());
        pedido.setLlego(editar.isLlego());
        pedido.setClienteNombre(editar.getClienteNombre().trim());
        pedido.setResponsable(editar.getResponsable());
        pedido.setObservaciones(editar.getObservaciones());

        if (editar.getEstado() == Estado.PEDIDO || editar.getEstado() == Estado.EN_TRANSPORTE) {
            pedido.setFecha_llegada(null);
            pedido.setLlego(false);
        }
        if (editar.getEstado() == Estado.LLEGO || editar.getEstado() == Estado.ENTREGADO_COLOCADO && pedido.getFecha_llegada() == null) {
            pedido.setFecha_llegada(LocalDate.now());
            pedido.setLlego(true);
        }

        pedidoService.save(pedido);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (!pedidoService.existById(id)) {
            return new ResponseEntity<>("El PEDIDO no existe", HttpStatus.NOT_FOUND);
        }
        pedidoService.delete(id);
        return new ResponseEntity<>("PEDIDO borrado", HttpStatus.OK);
    }

    private PedidoCriteria createCriteria(BusquedaDto busqueda) {
        PedidoCriteria pedidoCriteria = new PedidoCriteria();
        if (busqueda != null) {
            //Fecha de Pedido
            if (busqueda.getFecha_pedidoDesde() != null || busqueda.getFecha_pedidoHasta() != null) {
                LocalDateFilter filter = new LocalDateFilter();
                if (busqueda.getFecha_pedidoDesde() != null) {
                    filter.setGreaterThanOrEqual(busqueda.getFecha_pedidoDesde());
                    pedidoCriteria.setFecha_pedido(filter);
                }
                if (busqueda.getFecha_pedidoHasta() != null) {
                    filter.setLessThanOrEqual(busqueda.getFecha_pedidoHasta());
                    pedidoCriteria.setFecha_pedido(filter);
                }
            }
            //Provedor
            if (!StringUtils.isBlank(busqueda.getProvedor())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getProvedor());
                pedidoCriteria.setProvedor(filter);
            }
            //Via
            if (!StringUtils.isBlank(busqueda.getVia())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getVia());
                pedidoCriteria.setVia(filter);
            }
            //Pedido
            if (!StringUtils.isBlank(busqueda.getN_pedido())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getN_pedido());
                pedidoCriteria.setN_pedido(filter);
            }
            //Factura
            if (!StringUtils.isBlank(busqueda.getN_factura())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getN_factura());
                pedidoCriteria.setN_factura(filter);
            }
            //Remito
            if (!StringUtils.isBlank(busqueda.getN_remito())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getN_remito());
                pedidoCriteria.setN_remito(filter);
            }
            //Llego
            if (!StringUtils.isBlank(busqueda.getLlego())) {
                BooleanFilter filter = new BooleanFilter();
                if (busqueda.getLlego().equals("true")) {
                    filter.setEquals(true);
                } else {
                    filter.setEquals(false);
                }
                pedidoCriteria.setLlego(filter);
            }
            //Fecha de Llegada
//            if (!StringUtils.isBlank(busquedaDto.getFecha_llegada())){
//                LocalDate localDate = LocalDate.parse(busquedaDto.getFecha_llegada());
//                LocalDateFilter filter = new LocalDateFilter();
//                filter.setGreaterThan(LocalDate.parse(busquedaDto.getFecha_llegada()));
//                pedidoCriteria.setFecha_llegada(filter);
//            }
            //Cliente
            if (!StringUtils.isBlank(busqueda.getCliente())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getCliente());
                pedidoCriteria.setClienteNombre(filter);
            }
//            if (!StringUtils.isBlank(busquedaDto.getLlego())){
//                BooleanFilter filter = new BooleanFilter();
//                switch (busquedaDto.getLlego()){
//                    case "true":
//                        filter.setEquals(true);
//                        break;
//                    case "false":
//                        filter.setEquals(false);
//                        break;
//                    default:
//                        filter.setEquals(false);
//                        break;
//                }
//                pedidoCriteria.setLlego(filter);
//            }
            //Estado
            if (!StringUtils.isBlank(busqueda.getEstado())) {
                PedidoCriteria.EstadoFilter filter = new PedidoCriteria.EstadoFilter();
                String estado = busqueda.getEstado().toUpperCase();
                filter.setEquals(Estado.valueOf(estado));
                pedidoCriteria.setEstado(filter);
            }
            //Responssable
            if (!StringUtils.isBlank(busqueda.getResponsable())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getResponsable());
                pedidoCriteria.setResponsable(filter);
            }
        }
        return pedidoCriteria;
    }
}