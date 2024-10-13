package com.crudspring.geminisdev.ServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudspring.geminisdev.Dao.CreditoDao;
import com.crudspring.geminisdev.Dao.SolicitudCreditoDao;
import com.crudspring.geminisdev.Dao.TarjetaCreditoDao;
import com.crudspring.geminisdev.Entity.Cliente;
import com.crudspring.geminisdev.Entity.Credito;
import com.crudspring.geminisdev.Entity.SolicitudCredito;
import com.crudspring.geminisdev.Entity.TarjetaCredito;
import com.crudspring.geminisdev.Service.SolicitudCreditoService;

@Service
public class SolicitudCreditoServiceIMpl implements SolicitudCreditoService {
    @Autowired
    SolicitudCreditoDao solicitudCreditoDao;
    @Autowired
    TarjetaCreditoDao tarjetaCreditoDao;
    @Autowired
    CreditoDao creditoDao;
    @Override
    public List<SolicitudCredito> obtenerSolicitudes() {
        return solicitudCreditoDao.findAll();
    }

    @Override
    public void guardarSolicitudCredito(SolicitudCredito solicitudCredito) {
        solicitudCreditoDao.save(solicitudCredito);
    }

    @Override
    public SolicitudCredito obtenerSolicitudCreditoPorId(Long id) {
        return solicitudCreditoDao.findById(id).orElse(null);
    }

    @Override
    public void eliminarSolicitudCredito(Long id) {
        solicitudCreditoDao.deleteById(id);
    }

    //metodo para aprobar solicitud, generar credito y tarjeta
    @Override
    public Credito aprobarSolicitud(SolicitudCredito solicitudCredito) {
        
        if (solicitudCredito.getEstado().equals("pendiente")) {
            solicitudCredito.setEstado("aprobado");
            solicitudCredito.setMontoAprobado(solicitudCredito.getMontoSolicitado());
            solicitudCredito.setLimiteCredito(solicitudCredito.getMontoAprobado());
            //plazo y tasa de interes
            int plazoEnMeses = 24;
            double tasaInteres =0.68;
            double montoConInteres= solicitudCredito.getMontoSolicitado() * (1+tasaInteres);
            LocalDate fechaLimitePago = LocalDate.now().plusMonths(plazoEnMeses);
            //se guarda la solicitud
            guardarSolicitudCredito(solicitudCredito);
            //crear y asignar un credito
            Credito credito = new Credito();
            credito.setCliente(solicitudCredito.getCliente());
            credito.setFechaAprobacion(java.sql.Date.valueOf(LocalDate.now()));
            credito.setLimiteCredito(solicitudCredito.getMontoAprobado());
            credito.setInteresAnual(tasaInteres*100);
            credito.setSaldoActual(solicitudCredito.getMontoAprobado());
            credito.setDisponibleParaUsar(solicitudCredito.getMontoAprobado());
            credito.setFechaCorte(java.sql.Date.valueOf(fechaLimitePago));
            creditoDao.save(credito);

            //Crear tarjeta de credito asociada
            TarjetaCredito tarjeta = generarTarjetaCredito(solicitudCredito.getCliente(), montoConInteres);
            tarjeta.setPlazoEnMeses(plazoEnMeses);
            tarjeta.setTasaInteres(tasaInteres);
            tarjeta.setFechaLimitePago(fechaLimitePago);
            //guardar la tarjeta de credito 
            tarjetaCreditoDao.save(tarjeta);
            return credito;
        }
        return null;
    }

    @Override
    public TarjetaCredito generarTarjetaCredito(Cliente cliente, double montoSolicitado) {
        //generar un numero de tarjeta aleatorio(simulado)
        String numeroTarjeta = generarNumeroTarjetaAleatorio();
        //definir limite de credito basado en el monto solicitado
        double limiteCredito = montoSolicitado;
        //Fecha de expiracion: 3 años dede la fecha actual 
        LocalDate fechaExpiracion = LocalDate.now().plusYears(3);
        //crear y devolver la tarjeta de credito 
        return new TarjetaCredito(numeroTarjeta, limiteCredito, fechaExpiracion, cliente);
    }
    private String generarNumeroTarjetaAleatorio(){
        Random random= new Random ();
        StringBuilder numeroTarjeta = new StringBuilder();
        for(int i=0;i<16;i++){
            numeroTarjeta.append(random.nextInt(10));
        }
        return numeroTarjeta.toString();
    }
}
