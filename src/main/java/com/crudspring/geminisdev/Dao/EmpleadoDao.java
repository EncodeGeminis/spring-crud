package com.crudspring.geminisdev.Dao;

import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.Empleado;
import java.util.List;


@Repository
public interface EmpleadoDao extends JpaRepository<Empleado, Long>{
    //este metodo hace una busqueda por el mismo nombre
    List<Empleado> findByNombre(String nombre);
    //este metodo busca los empleados que tienen mas de cierta edad:
    List<Empleado> findByEdadGreatherThan(int edad);
    //busca empleados por nombre y por edad
    List<Empleado> findByNombreAndEdad(String nombre, int edad);
    //se buscan los empleados que contengan un valor dado.
    List<Empleado> findByNombreContaining(String nombre, Pageable pageable);

    //consultas con query 
    //JPQL para buscar empleados por nombre
    @Query("SELECT e FROM Empleado e WHERE e.nombre = :nombre")
    List<Empleado> buscarPorNombre(@Param("nombre") String nombre);
    //JPQL para buscar empleados cuya edad sea mayor a un valor dado
    @Query("SELECT e FROM Empleado e WHERE e.edad > :edad")
    List<Empleado> buscarPorEdadMayorA(@Param("edad") int edad);
    //JPQL para buscar empleados por nombre parcial 
    @Query("SELECT e FROM Empleado e WHERE e.nombre LIKE %:nombre%")
    List<Empleado> buscarPorNombreParcial(@Param("nombre") String nombre);

    //consultas nativas de Sql en spring 
    //se hace una consulta nativa para buscar por nombre
    @Query(value = "SELECT * FROM empleado WHERE nombre = :nombre", nativeQuery = true)
    List<Empleado> buscarPorNombreNativo(@Param("nombre")String nombre);

    //uso de paginacion para mostrar resultados filtrados
    //busqueda con ordenacion 
    Page<Empleado> findByNombreContaining(String nombre, Sort sort);

}
