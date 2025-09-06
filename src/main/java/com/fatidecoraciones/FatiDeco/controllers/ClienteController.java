package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.criteria.ClienteCriteria;
import com.fatidecoraciones.FatiDeco.dtos.BusquedaDto;
import com.fatidecoraciones.FatiDeco.dtos.ClienteDto;
import com.fatidecoraciones.FatiDeco.models.Cliente;
import com.fatidecoraciones.FatiDeco.services.ClienteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.service.filter.StringFilter;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cliente")
@CrossOrigin
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/lista")
    public ResponseEntity lista() {
        List<ClienteDto> list = clienteService.getClientesDto();
        if (list.isEmpty()) {
            return new ResponseEntity("No hay PROVEEDORES cargados", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/buscar")
    public ResponseEntity<ClienteDto> buscar(@RequestParam String clienteN) {

        ClienteDto cliente = clienteService.findByNombreDto(clienteN);



        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/filtro")
    public ResponseEntity<List<ClienteDto>> filtro(@RequestBody BusquedaDto busquedaDto) {

        ClienteCriteria clienteCriteria = createCriteria(busquedaDto);
        List<Cliente> lista = clienteService.findByCriteria(clienteCriteria);

        List<ClienteDto> listaDto = lista.stream().map(cliente -> {

            ClienteDto clienteDto = new ClienteDto();

            clienteDto.setId(cliente.getId());
            clienteDto.setNombre(cliente.getNombre());
            clienteDto.setDireccion(cliente.getDireccion());
            clienteDto.setTelefono(cliente.getTelefono());

            return clienteDto;

        }).collect(Collectors.toList());

        return new ResponseEntity<>(listaDto, HttpStatus.OK);
    }

    @GetMapping("/uno/{id}")
    public ResponseEntity<ClienteDto> uno(@PathVariable Long id) {


        if (!clienteService.existsById(id)) {
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
            return new ResponseEntity<>("El CLIENTE ya Existe", HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>("El CLIENTE ya Existe", HttpStatus.NOT_FOUND);
        }

        if (!clienteService.existsById(id)) {
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
        if (!clienteService.existsById(id)) {
            return new ResponseEntity<>("El CLIENTE no existe", HttpStatus.NOT_FOUND);
        }

        // Validar si el cliente tiene presupuestos
        if (clienteService.tieneMedidas(id)) {
            return new ResponseEntity<>("No se puede eliminar el CLIENTE porque tiene MEDIDAS asociadas", HttpStatus.BAD_REQUEST);
        }
        if (clienteService.tieneConfecciones(id)) {
            return new ResponseEntity<>("No se puede eliminar el CLIENTE porque tiene CONFECCIONES asociadas", HttpStatus.BAD_REQUEST);
        }

        clienteService.delete(id);
        return new ResponseEntity<>("CLIENTE borrado", HttpStatus.OK);
    }

    private ClienteCriteria createCriteria(BusquedaDto busqueda) {
        ClienteCriteria clienteCriteria = new ClienteCriteria();
        if (busqueda != null) {

            //Cliente
            if (!StringUtils.isBlank(busqueda.getCliente())) {
                StringFilter filter = new StringFilter();
                filter.setContains(busqueda.getCliente());
                clienteCriteria.setNombre(filter);
            }

        }
        return clienteCriteria;
    }
}