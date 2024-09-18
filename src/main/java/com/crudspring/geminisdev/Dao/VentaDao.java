package com.crudspring.geminisdev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.Venta;

@Repository
public interface VentaDao extends JpaRepository<Venta, Long> {}
