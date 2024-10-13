package com.crudspring.geminisdev.Service;

import java.util.List;

import com.crudspring.geminisdev.Entity.Cliente;
import com.crudspring.geminisdev.Entity.TarjetaCredito;

public interface TarjetaCreditoService {
    public List<TarjetaCredito> obtenerTarjetaPorCliente(Cliente cliente);
    public void agregarTarjetaCredito(TarjetaCredito tarjetaCredito);
    public TarjetaCredito obtenerTarjetaPorId(Long id);
}
