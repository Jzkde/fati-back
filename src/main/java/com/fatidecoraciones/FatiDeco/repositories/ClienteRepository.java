package com.fatidecoraciones.FatiDeco.repositories;

import com.fatidecoraciones.FatiDeco.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCase (String nombre);
    Optional<Cliente> findByNombre(String nombre);

}
