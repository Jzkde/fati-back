package com.fatidecoraciones.pedidos.controllers;

import com.fatidecoraciones.pedidos.dtos.ClienteDto;
import com.fatidecoraciones.pedidos.models.Cliente;
import com.fatidecoraciones.pedidos.repositories.ClienteRepository;
import com.fatidecoraciones.pedidos.repositories.PedidoRepository;
import com.fatidecoraciones.pedidos.repositories.PresupuestoRepository;
import com.fatidecoraciones.pedidos.services.ClienteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PresupuestoRepository presupuestoRepository;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/lista")
    public ResponseEntity<List<ClienteDto>> lista() {
        List<ClienteDto> list = clienteService.getClientesDto();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/lista/{id}")
    public ResponseEntity <ClienteDto> uno (@PathVariable("id") Long id) {

            if (clienteService.getCliente(id) == null){
        return new ResponseEntity("El CLIENTE no existe",HttpStatus.BAD_REQUEST);  }

        ClienteDto uno = clienteService.getClienteDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }
    @PostMapping("/nuevo")
    public ResponseEntity <?> nuevo (@RequestBody ClienteDto nuevo){

        if (StringUtils.isBlank(nuevo.getNombre()))
            return new ResponseEntity<>("Falta el NOMBRE", HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(nuevo.getApellido()))
            return new ResponseEntity<> ("Falta el APELLIDO", HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(nuevo.getDireccion()))
            return new ResponseEntity<> ("Falta el DOMICILIO", HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(nuevo.getTelefono()))
            return new ResponseEntity<> ("Falta el TELEFONO", HttpStatus.BAD_REQUEST);

        Cliente cliente = new Cliente(
                nuevo.getNombre(),
                nuevo.getApellido(),
                nuevo.getDireccion(),
                nuevo.getTelefono()
        );

        clienteRepository.save(cliente);
        return new ResponseEntity<> ( HttpStatus.OK);
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity <?> editar (@PathVariable ("id") Long id,@RequestBody ClienteDto editar){

        if (editar.getApellido() == null)
            return new ResponseEntity<> ("Falta el APELLIDO", HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(editar.getNombre()))
            return new ResponseEntity<> ("Falta el NOMBRE", HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(editar.getTelefono()))
            return new ResponseEntity<> ("Falta el TELEFONO", HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(editar.getDireccion()))
            return new ResponseEntity<>("Falta la DIRECCION", HttpStatus.BAD_REQUEST);

        Cliente cliente = clienteService.getCliente(id);

        cliente.setApellido(editar.getApellido());
        cliente.setDireccion(editar.getDireccion());
        cliente.setNombre(editar.getNombre());
        cliente.setTelefono(editar.getTelefono());

        clienteRepository.save(cliente);
        return new ResponseEntity<> (HttpStatus.OK);
    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity <?> borrar (@PathVariable ("id") Long id) {
    if (!clienteService.existById(id))
        return new ResponseEntity<>("No existe el CLIENTE", HttpStatus.NOT_FOUND);
    if (!clienteRepository.findById(id).get().getPedidos().isEmpty())
        return new ResponseEntity<>("El CLIENTE aun tiene pedidos registrados", HttpStatus.BAD_REQUEST);
    if (!clienteRepository.findById(id).get().getPresupuestos().isEmpty())
            return new ResponseEntity<>("El CLIENTE aun tiene presupuestos registrados", HttpStatus.BAD_REQUEST);
        clienteRepository.deleteById(id);
            return new ResponseEntity<>("CLIENTE borrado", HttpStatus.OK);




    }

}
