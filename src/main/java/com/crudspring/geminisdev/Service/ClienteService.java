package com.crudspring.geminisdev.Service;

import java.util.List;

import com.crudspring.geminisdev.Entity.Cliente;

public interface ClienteService {
    public List<Cliente> obtenerClientes();
    public Cliente guardarCliente(Cliente cliente);
    public Cliente obtenerClientePorId(Long id);
    public void eliminarCliente(Long id);
}
