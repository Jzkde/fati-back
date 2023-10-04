package com.fatidecoraciones.pedidos.repositories;

import com.fatidecoraciones.pedidos.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>,
        JpaSpecificationExecutor<Pedido> {
}
