package com.fatidecoraciones.FatiDeco.repositories;

import com.fatidecoraciones.FatiDeco.models.Cliente;
import com.fatidecoraciones.FatiDeco.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    Marca findByMarcaIgnoreCase(String marca);

    List<Marca> findByEsSistema(boolean esSistema);
    List<Marca> findAllByOrderByEsSistemaAscMarcaAsc();

}
