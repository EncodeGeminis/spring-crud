package com.crudspring.geminisdev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.Cliente;

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Long> {}
