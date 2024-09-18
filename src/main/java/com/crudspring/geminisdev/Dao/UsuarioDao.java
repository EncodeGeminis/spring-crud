package com.crudspring.geminisdev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);
}
