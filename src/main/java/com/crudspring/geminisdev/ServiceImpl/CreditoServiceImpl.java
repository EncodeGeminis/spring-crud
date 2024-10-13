package com.crudspring.geminisdev.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudspring.geminisdev.Dao.ClienteDao;
import com.crudspring.geminisdev.Dao.CreditoDao;
import com.crudspring.geminisdev.Entity.Cliente;
import com.crudspring.geminisdev.Entity.Credito;
import com.crudspring.geminisdev.Service.CreditoService;

@Service
public class CreditoServiceImpl implements CreditoService {

    @Autowired
    CreditoDao creditoDao;
    @Autowired
    ClienteDao clienteDao;

    @Override
    public Credito crearCredito(Credito credito, Long clienteId) {
        Optional<Cliente> cliente= clienteDao.findById(clienteId);
        if (cliente.isPresent()) {
            credito.setCliente(cliente.get());
            return creditoDao.save(credito);
        } else{
            throw new RuntimeException("CLiente no encontrado");
        }
    }

    @Override
    public Optional<Credito> obtenerCreditoPorId(Long id) {
        return creditoDao.findById(id);
    }

    @Override
    public List<Credito> listaCreditos() {
        return creditoDao.findAll();
    }

}
