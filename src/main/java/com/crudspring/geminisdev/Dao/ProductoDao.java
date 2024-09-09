package com.crudspring.geminisdev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.Producto;
import java.util.List;


@Repository
public interface ProductoDao extends JpaRepository<Producto, Long> {
    // Ajustando la consulta para que busque directamente en el campo categoría de la tabla Producto
    @Query("SELECT p FROM Producto p WHERE p.categoria = :categoria")
    List<Producto> findByCategoria(@Param("categoria") String categoria);
    
    // Consulta para obtener categorías únicas sin necesidad de otra tabla
    @Query("SELECT DISTINCT p.categoria FROM Producto p WHERE p.categoria IS NOT NULL")
    List<String> findDistinctCategorias();
}
