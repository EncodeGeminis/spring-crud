package com.crudspring.geminisdev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crudspring.geminisdev.Entity.Cliente;
import com.crudspring.geminisdev.Entity.TarjetaCredito;
import java.util.List;

@Repository
public interface TarjetaCreditoDao extends JpaRepository<TarjetaCredito, Long> {
    List<TarjetaCredito> findByCliente(Cliente cliente);
}
