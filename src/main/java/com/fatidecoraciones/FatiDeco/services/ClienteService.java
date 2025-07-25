package com.fatidecoraciones.FatiDeco.services;

import com.fatidecoraciones.FatiDeco.criteria.ClienteCriteria;
import com.fatidecoraciones.FatiDeco.dtos.ClienteDto;
import com.fatidecoraciones.FatiDeco.models.Cliente;
import com.fatidecoraciones.FatiDeco.models.Cliente_;
import com.fatidecoraciones.FatiDeco.repositories.ClienteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService extends QueryService<Cliente> {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findByNombre(String nombre) {
        return clienteRepository.findByNombreIgnoreCase(nombre.trim());
    }

    public ClienteDto findByNombreDto(String nombre) {
        Cliente cliente = clienteRepository.findByNombreIgnoreCase(nombre);
        if (cliente != null) {
            return new ClienteDto(cliente);
        } else {
            return null;
        }
    }

    public boolean existByNombre(String nombre) {
        return clienteRepository.existsByNombreIgnoreCase(nombre);
    }

    public boolean existById(Long id) {
        return clienteRepository.existsById(id);
    }

    public Cliente getCliente(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public List<ClienteDto> getClientesDto() {
        return clienteRepository.findAll().stream().map(ClienteDto::new).collect(Collectors.toList());
    }

    public ClienteDto getClienteDto(Long id) {
        return new ClienteDto(this.getCliente(id));
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    public boolean tieneMedidas(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        return cliente != null && cliente.getMedidas() != null && !cliente.getMedidas().isEmpty();
    }

    public boolean tieneConfecciones(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        return cliente != null && cliente.getTalleres() != null && !cliente.getTalleres().isEmpty();
    }

    public List<Cliente> findByCriteria(ClienteCriteria clienteCriteria) {
        final Specification<Cliente> specification = createSpecification(clienteCriteria);
        return clienteRepository.findAll(specification, Sort.by("nombre","telefono"));
    }

    private Specification<Cliente> createSpecification(ClienteCriteria clienteCriteria) {
        Specification<Cliente> specification = Specification.where(null);
        if (clienteCriteria != null) {
            if (clienteCriteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(clienteCriteria.getNombre(), Cliente_.nombre));
            }
        }
        return specification;
    }


}