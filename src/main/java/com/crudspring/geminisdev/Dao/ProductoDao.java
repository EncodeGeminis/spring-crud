package com.crudspring.geminisdev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.Producto;

@Repository
public interface ProductoDao extends JpaRepository<Producto, Long> {}
