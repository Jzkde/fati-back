package com.fatidecoraciones.FatiDeco.repositories;

import com.fatidecoraciones.FatiDeco.models.Rango;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RangoRepository extends JpaRepository<Rango, Long> {
    List<Rango> findByCortEspeciales_Marca_MarcaIgnoreCaseAndCortEspeciales_Sistema_SistemaIgnoreCase(String marca, String sistema);
    List<Rango> findAllByOrderByAnchoMinAsc();
}
