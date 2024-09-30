package com.crudspring.geminisdev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.SolicitudCredito;
@Repository
public interface SolicitudCreditoDao extends JpaRepository<SolicitudCredito, Long> {}
