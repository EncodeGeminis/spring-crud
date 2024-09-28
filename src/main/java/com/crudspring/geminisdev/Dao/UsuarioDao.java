package com.crudspring.geminisdev.Dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);
    //JPQL para buscar empleados por nombre
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    List<Usuario> buscarListaPorNombre(@Param("nombre") String nombre);
    //metodo para buscar el nombre parcial del usuario
    @Query("SELECT u FROM Usuario u WHERE u.nombre LIKE %:nombre%")
    List<Usuario> buscarPorNombreParcial(@Param("nombre") String nombre);
    //uso de paginacion para mostrar resultados filtrados
    //busqueda con paginacion
    @Query("SELECT u FROM Usuario u")
    Page<Usuario> buscarTodos(Pageable pageable );
}
/*
    //consultas con query 
    //JPQL para buscar empleados cuya edad sea mayor a un valor dado
    @Query("SELECT e FROM Empleado e WHERE e.edad > :edad")
    List<Empleado> buscarPorEdadMayorA(@Param("edad") int edad);
 */
