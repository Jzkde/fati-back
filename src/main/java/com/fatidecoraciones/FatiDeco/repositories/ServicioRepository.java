package com.fatidecoraciones.FatiDeco.repositories;

import com.fatidecoraciones.FatiDeco.enums.Serv;
import com.fatidecoraciones.FatiDeco.models.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    Servicio findByNombreIgnoreCase(String tipo);
    List<Servicio> findByTipo(Serv tipo);

    @Modifying
    @Transactional
    @Query("UPDATE Servicio p SET p.precio = p.precio * :porcentaje")
    void actualizarPrecios(double porcentaje);
}