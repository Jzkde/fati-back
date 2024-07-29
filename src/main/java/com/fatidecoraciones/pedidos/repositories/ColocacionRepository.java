package com.fatidecoraciones.pedidos.repositories;

import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.Colocacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ColocacionRepository extends JpaRepository<Colocacion, Long> {

    Colocacion findByTipo(String tipo);

    @Modifying
    @Transactional
    @Query("UPDATE Colocacion p SET p.precio = p.precio * :porcentaje")
    void actualizarPrecios(double porcentaje);

}
