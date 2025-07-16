package com.fatidecoraciones.FatiDeco.repositories;

import com.fatidecoraciones.FatiDeco.models.CortEspeciales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CortEspecialesRepository extends JpaRepository<CortEspeciales, Long>,
        JpaSpecificationExecutor<CortEspeciales>
{

    CortEspeciales findByTelaIgnoreCase(String tela);

    List<CortEspeciales> findBySistema_SistemaIgnoreCase(String sistema);

    List<CortEspeciales> findByEsAdicionalAndMarca_MarcaIgnoreCaseAndSistema_Sistema(boolean adicional, String marca, String sistema);
    List<CortEspeciales> findByMarca_MarcaIgnoreCase (String marca);

    boolean existsByMarca_MarcaIgnoreCase(String marca);

    boolean existsByTelaIgnoreCase(String tela);
    boolean existsByTelaIgnoreCaseAndSistema_SistemaIgnoreCaseAndMarca_MarcaIgnoreCase(String tela, String sistema, String marca);

    Optional<CortEspeciales> findByEsTelaAndSistema_SistemaIgnoreCase(boolean es_tela, String sistema );

    Optional<CortEspeciales> findByTelaAndSistema_SistemaIgnoreCaseAndMarca_MarcaIgnoreCase(String tela, String sistema, String marca);

    Optional<CortEspeciales> findByTelaIgnoreCaseAndSistema_SistemaIgnoreCase(String tela, String sistema);

    @Modifying
    @Transactional
    @Query("UPDATE CortEspeciales t SET t.precio = t.precio * :porcentaje WHERE t.marca.id = :marcaId")
    void actualizarPrecios(double porcentaje, Long marcaId);


}