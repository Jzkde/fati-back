package com.fatidecoraciones.pedidos.repositories;

import com.fatidecoraciones.pedidos.models.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PresupuestoRepository extends JpaRepository<Presupuesto, Long>,
        JpaSpecificationExecutor<Presupuesto> {
}
