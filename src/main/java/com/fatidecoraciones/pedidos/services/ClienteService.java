package com.fatidecoraciones.pedidos.services;

import com.fatidecoraciones.pedidos.dtos.ClienteDto;
import com.fatidecoraciones.pedidos.models.Cliente;
import com.fatidecoraciones.pedidos.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public boolean existById (Long id) {
        return clienteRepository.existsById(id);
    }
    public Cliente getCliente(Long id) { return clienteRepository.findById(id).orElse(null); }
    public void save (Cliente cliente){
        clienteRepository.save(cliente);
    }
    public List<ClienteDto> getClientesDto() {
        return clienteRepository.findAll().stream().map(cliente -> new ClienteDto(cliente)).collect(Collectors.toList());
    }
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }
    public ClienteDto getClienteDto(Long id) {
        return new ClienteDto(this.getCliente(id));
    }
    public void delete (Long id){clienteRepository.deleteById(id);}


}
