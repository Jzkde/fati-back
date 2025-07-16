package com.fatidecoraciones.FatiDeco.repositories;

import com.fatidecoraciones.FatiDeco.models.Marca;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SistemaRepository extends JpaRepository<Sistema, Long> {
    Sistema findBySistemaIgnoreCase(String sistema);
    List<Sistema> findByMarcas_MarcaIgnoreCaseAndEsSistemaTrue(String marca);
    List<Sistema> findByEsSistema(boolean esSistema);
    boolean existsBySistema(String sistema);
    List<Sistema> findAllByOrderBySistemaAsc();

}
