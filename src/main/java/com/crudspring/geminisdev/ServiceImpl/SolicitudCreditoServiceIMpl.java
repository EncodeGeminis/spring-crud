package com.crudspring.geminisdev.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudspring.geminisdev.Dao.SolicitudCreditoDao;
import com.crudspring.geminisdev.Entity.SolicitudCredito;
import com.crudspring.geminisdev.Service.SolicitudCreditoService;

@Service
public class SolicitudCreditoServiceIMpl implements SolicitudCreditoService {
    @Autowired
    SolicitudCreditoDao solicitudCreditoDao;

    @Override
    public List<SolicitudCredito> obtenerSolicitudes() {
        return solicitudCreditoDao.findAll();
    }

    @Override
    public SolicitudCredito guardarSolicitudCredito(SolicitudCredito solicitudCredito) {
        return solicitudCreditoDao.save(solicitudCredito);
    }

    @Override
    public SolicitudCredito obtenerSolicitudCreditoporId(Long id) {
        return solicitudCreditoDao.findById(id).orElse(null);
    }

    @Override
    public void eliminarSolicitudCredito(Long id) {
        solicitudCreditoDao.deleteById(id);
    }
}
