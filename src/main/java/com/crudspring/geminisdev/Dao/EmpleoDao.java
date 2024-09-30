package com.crudspring.geminisdev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.Empleado;

@Repository
public interface EmpleoDao extends JpaRepository<Empleado, Long> {}
