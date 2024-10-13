package com.crudspring.geminisdev.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudspring.geminisdev.Dao.HistorialCreditoDao;
import com.crudspring.geminisdev.Entity.HistorialCredito;
import com.crudspring.geminisdev.Service.HistorialCreditoService;

@Service
public class HistorialCreditoServiceImpl implements HistorialCreditoService {

    @Autowired
    HistorialCreditoDao historialCreditoDao;

    @Override
    public void registrarPago(HistorialCredito historialCredito) {
        historialCreditoDao.save(historialCredito);
    }

}
