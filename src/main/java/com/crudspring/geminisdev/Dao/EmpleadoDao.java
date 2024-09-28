package com.crudspring.geminisdev.Dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.Empleado;
import java.util.List;


@Repository
public interface EmpleadoDao extends JpaRepository<Empleado, Long>{
    //este metodo hace una busqueda por el mismo nombre
    List<Empleado> findByNombre(String nombre);
    //este metodo busca los empleados que tienen mas de cierta edad:
    List<Empleado> findByEdadGreaterThan(int edad);
    //busca empleados por nombre y por sueldo
    List<Empleado> findByNombreAndSueldo(String nombre, int sueldo);
    //se buscan los empleados que contengan un valor dado.
    List<Empleado> findByNombreContaining(String nombre, Pageable pageable);
}
