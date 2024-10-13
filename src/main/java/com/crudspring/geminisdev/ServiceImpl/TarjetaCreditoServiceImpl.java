package com.crudspring.geminisdev.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudspring.geminisdev.Dao.TarjetaCreditoDao;
import com.crudspring.geminisdev.Entity.Cliente;
import com.crudspring.geminisdev.Entity.TarjetaCredito;
import com.crudspring.geminisdev.Service.TarjetaCreditoService;
import java.util.List;
@Service
public class TarjetaCreditoServiceImpl implements TarjetaCreditoService {

    @Autowired
    TarjetaCreditoDao tarjetaCreditoDao;

    @Override
    public List<TarjetaCredito> obtenerTarjetaPorCliente(Cliente cliente) {
        return tarjetaCreditoDao.findByCliente(cliente);
    }

    @Override
    public void agregarTarjetaCredito(TarjetaCredito tarjetaCredito) {
        tarjetaCreditoDao.save(tarjetaCredito);
    }

    @Override
    public TarjetaCredito obtenerTarjetaPorId(Long id) {
        return tarjetaCreditoDao.findById(id).orElse(null);
    }
    

}
