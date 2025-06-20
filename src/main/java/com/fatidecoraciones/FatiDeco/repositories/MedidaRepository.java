package com.fatidecoraciones.FatiDeco.repositories;

import com.fatidecoraciones.FatiDeco.models.Medida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidaRepository extends JpaRepository<Medida, Long>,
        JpaSpecificationExecutor<Medida> {
}