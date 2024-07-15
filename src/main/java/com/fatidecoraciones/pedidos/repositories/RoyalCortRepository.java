package com.fatidecoraciones.pedidos.repositories;

import com.fatidecoraciones.pedidos.models.RoyalCort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoyalCortRepository extends JpaRepository<RoyalCort, Long>,
        JpaSpecificationExecutor<RoyalCort> {

    RoyalCort findByTela(String tela);

    @Modifying
    @Transactional
    @Query("UPDATE RoyalCort p SET p.precio = p.precio * :porcentaje")
    void actualizarPrecios(double porcentaje);

}