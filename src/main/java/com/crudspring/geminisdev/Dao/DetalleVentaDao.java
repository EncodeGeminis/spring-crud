package com.crudspring.geminisdev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.crudspring.geminisdev.Entity.DetalleVenta;

@Repository
public interface DetalleVentaDao extends JpaRepository<DetalleVenta, Long>{
    List<DetalleVenta> findByVentaId(Long ventaId);
}
