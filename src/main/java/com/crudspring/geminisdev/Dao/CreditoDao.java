package com.crudspring.geminisdev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.Credito;

@Repository
public interface CreditoDao extends JpaRepository<Credito, Long> {}
