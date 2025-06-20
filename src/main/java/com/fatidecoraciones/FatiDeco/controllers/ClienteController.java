package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.dtos.ClienteDto;
import com.fatidecoraciones.FatiDeco.models.Cliente;
import com.fatidecoraciones.FatiDeco.services.ClienteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("cliente")
@CrossOrigin
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ClienteDto>> lista() {
        List<ClienteDto> list = clienteService.getClientesDto();
        if (list.isEmpty()) {
            return new ResponseEntity("No hay PROVEEDORES cargados", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<ClienteDto> buscar(@RequestParam String clienteN) {
        ClienteDto cliente = clienteService.findByNombreDto(clienteN.trim());
        if (cliente == null) {
            return new ResponseEntity("CLIENTE no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/uno/{id}")
    public ResponseEntity<ClienteDto> uno(@PathVariable Long id) {


        if (!clienteService.existById(id)) {
            return new ResponseEntity("El CLIENTE no existe", HttpStatus.NOT_FOUND);
        }
        ClienteDto uno = clienteService.getClienteDto(id);
        return new ResponseEntity<>(uno, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    @Transactional
    public ResponseEntity<?> nuevo(@RequestBody ClienteDto nuevo) {

        if (StringUtils.isBlank(nuevo.getNombre())) {
            return new ResponseEntity<>("Falta el nombre del CLIENTE", HttpStatus.BAD_REQUEST);
        }
        Cliente clienteN = clienteService.findByNombre(nuevo.getNombre().trim());
        if (clienteN != null) {
            return new ResponseEntity("El CLIENTE ya Existe", HttpStatus.NOT_FOUND);
        }

        Cliente cliente = new Cliente(
                nuevo.getNombre().trim(),
                nuevo.getDireccion(),
                nuevo.getTelefono()
        );
        clienteService.save(cliente);
        return new ResponseEntity<>("CLIENTE guardado con éxito: " + cliente.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    @Transactional
    public ResponseEntity<?> editar(@PathVariable Long id,
                                    @RequestBody ClienteDto editar) {
        Cliente clienteN = clienteService.findByNombre(editar.getNombre().trim());
        if (clienteN != null) {
            return new ResponseEntity("El CLIENTE ya Existe", HttpStatus.NOT_FOUND);
        }

        if (!clienteService.existById(id)) {
            return new ResponseEntity<>("No existe el CLIENTE", HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(editar.getNombre())) {
            return new ResponseEntity<>("Falta el nombre del CLIENTE", HttpStatus.BAD_REQUEST);
        }

        Cliente cliente = clienteService.getCliente(id);

        cliente.setNombre(editar.getNombre().trim());
        cliente.setDireccion(editar.getDireccion());
        cliente.setTelefono(editar.getTelefono());

        clienteService.save(cliente);
        return new ResponseEntity<>("CLIENTE modificado", HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    @Transactional
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        if (!clienteService.existById(id)) {
            return new ResponseEntity<>("El CLIENTE no existe", HttpStatus.NOT_FOUND);
        }

        // Validar si el cliente tiene presupuestos
        if (clienteService.tieneMedidas(id)) {
            return new ResponseEntity<>("No se puede eliminar el CLIENTE porque tiene MEDIDAS asociadas", HttpStatus.BAD_REQUEST);
        }
        if (clienteService.tieneConfecciones(id)){
            return new ResponseEntity<>("No se puede eliminar el CLIENTE porque tiene CONFECCIONES asociadas", HttpStatus.BAD_REQUEST);
        }

        clienteService.delete(id);
        return new ResponseEntity<>("CLIENTE borrado", HttpStatus.OK);
    }


}