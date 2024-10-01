package com.crudspring.geminisdev.Entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "solicitudes_credito")
public class SolicitudCredito implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private double montoSolicitado;
    private Date fechaSolicitud;
    private String motivoSolicitud;
    private String estado; //pendiente, aprovado, rechazado
    private double montoAprobado;
    private String tipoCredito; //tarjeta de credito, prestamo personal
    private double limiteCredito;

    public SolicitudCredito() {}
    
    public SolicitudCredito(Long id, Cliente cliente, double montoSolicitado, Date fechaSolicitud,
            String motivoSolicitud, String estado, double montoAprobado,
            String tipoCredito, double limiteCredito) {
        this.id = id;
        this.cliente = cliente;
        this.montoSolicitado = montoSolicitado;
        this.fechaSolicitud = fechaSolicitud;
        this.motivoSolicitud = motivoSolicitud;
        this.estado = estado;
        this.montoAprobado = montoAprobado;
        this.tipoCredito = tipoCredito;
        this.limiteCredito = limiteCredito;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public double getMontoSolicitado() {
        return montoSolicitado;
    }
    public void setMontoSolicitado(double montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    public String getMotivoSolicitud() {
        return motivoSolicitud;
    }
    public void setMotivoSolicitud(String motivoSolicitud) {
        this.motivoSolicitud = motivoSolicitud;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public double getMontoAprobado() {
        return montoAprobado;
    }
    public void setMontoAprobado(double montoAprobado) {
        this.montoAprobado = montoAprobado;
    }
    public String getTipoCredito() {
        return tipoCredito;
    }
    public void setTipoCredito(String tipoCredito) {
        this.tipoCredito = tipoCredito;
    }
    public double getLimiteCredito() {
        return limiteCredito;
    }
    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
    
    
}
