package com.fatidecoraciones.FatiDeco.repositories;

import com.fatidecoraciones.FatiDeco.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>,
        JpaSpecificationExecutor<Producto> {

    Producto findByNombre(String tela);

    Producto findByArt(Long art);
    List<Producto> findByEsTela(boolean esTela);
    boolean existsByNombre(String nombre);

    @Modifying
    @Transactional
    @Query("UPDATE Producto t SET t.precio = t.precio * :porcentaje WHERE t.marca.id = :marcaId")
    void actualizarPrecios(double porcentaje, Long marcaId);

    @Query("SELECT t FROM Producto t WHERE LOWER(t.nombre) = LOWER(:nombre) AND t.esTela = :esTela")
    Optional<Producto> findByNombreAndEsTela(String nombre, boolean esTela);


    // @Query("SELECT t FROM Producto t WHERE t.esTela = :esTela ORDER BY t.nombre")


//    @Query("SELECT t FROM Producto t WHERE t.esTela = false ORDER BY t.nombre")
//    List<Producto> findSoloSistemas ();
}