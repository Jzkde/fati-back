package com.fatidecoraciones.FatiDeco.repositories;

import com.fatidecoraciones.FatiDeco.models.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SistemaRepository extends JpaRepository<Sistema, Long> {
    Sistema findBySistemaIgnoreCase(String sistema);
}
