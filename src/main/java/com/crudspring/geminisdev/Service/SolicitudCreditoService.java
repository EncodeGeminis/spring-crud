package com.crudspring.geminisdev.Service;

import java.util.List;

import com.crudspring.geminisdev.Entity.SolicitudCredito;

public interface SolicitudCreditoService {
    public List<SolicitudCredito> obtenerSolicitudes();
    public SolicitudCredito guardarSolicitudCredito(SolicitudCredito solicitudCredito);
    public SolicitudCredito obtenerSolicitudCreditoporId(Long id);
    public void eliminarSolicitudCredito(Long id);
}
