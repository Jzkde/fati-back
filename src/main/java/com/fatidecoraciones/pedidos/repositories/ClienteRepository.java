package com.fatidecoraciones.pedidos.repositories;

import com.fatidecoraciones.pedidos.models.Cliente;
import com.fatidecoraciones.pedidos.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
