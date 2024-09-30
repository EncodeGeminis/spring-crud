package com.crudspring.geminisdev.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudspring.geminisdev.Dao.ClienteDao;
import com.crudspring.geminisdev.Entity.Cliente;
import com.crudspring.geminisdev.Service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteDao clienteDao;

    @Override
    public List<Cliente> obtenerClientes() {
        return clienteDao.findAll();    
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteDao.save(cliente);   
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        var cliente = clienteDao.findById(id).orElse(null);
        return cliente;    
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteDao.deleteById(id);   
    }

}
