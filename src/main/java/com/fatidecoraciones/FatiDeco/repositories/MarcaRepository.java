package com.fatidecoraciones.FatiDeco.repositories;

import com.fatidecoraciones.FatiDeco.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    Marca findByMarcaIgnoreCase(String marca);
}
