package com.crudspring.geminisdev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.HistorialCredito;

@Repository
public interface HistorialCreditoDao extends JpaRepository<HistorialCredito, Long> {}
