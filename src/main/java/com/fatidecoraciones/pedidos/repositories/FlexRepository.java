package com.fatidecoraciones.pedidos.repositories;

import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.Flex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FlexRepository extends JpaRepository<Flex, Long>,
        JpaSpecificationExecutor<Flex> {

    Flex findByTela(String tela);

    List<Flex> findBySistema(Sistema sistema);

    @Modifying
    @Transactional
    @Query("UPDATE Flex p SET p.precio = p.precio * :porcentaje")
    void actualizarPrecios(double porcentaje);

}