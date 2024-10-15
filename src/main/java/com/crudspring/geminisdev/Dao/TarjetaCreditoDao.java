package com.crudspring.geminisdev.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.crudspring.geminisdev.Entity.Cliente;
import com.crudspring.geminisdev.Entity.TarjetaCredito;
import java.util.List;

@Repository
public interface TarjetaCreditoDao extends JpaRepository<TarjetaCredito, Long> {
    List<TarjetaCredito> findByCliente(Cliente cliente);
    
    @Query("Select t From TarjetaCredito t")
    Page<TarjetaCredito> listarTarjetas(Pageable pageable);
}
