package com.crudspring.geminisdev.Service;

import java.util.List;

import com.crudspring.geminisdev.Entity.Cliente;
import com.crudspring.geminisdev.Entity.Credito;
import com.crudspring.geminisdev.Entity.SolicitudCredito;
import com.crudspring.geminisdev.Entity.TarjetaCredito;

public interface SolicitudCreditoService {
    public List<SolicitudCredito> obtenerSolicitudes();
    public void guardarSolicitudCredito(SolicitudCredito solicitudCredito);
    public SolicitudCredito obtenerSolicitudCreditoPorId(Long id);
    public void eliminarSolicitudCredito(Long id);

    public Credito aprobarSolicitud(SolicitudCredito solicitudCredito);
    public TarjetaCredito generarTarjetaCredito(Cliente cliente, double montoSolicitado);



}
