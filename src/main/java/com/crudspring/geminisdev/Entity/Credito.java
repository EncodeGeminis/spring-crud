package com.crudspring.geminisdev.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "credito")
public class Credito implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private Date fechaAprobacion;
    private double limiteCredito;
    private double interesAnual;
    private Date fechaCorte;
    private double saldoActual;
    private double disponibleParaUsar;

    @OneToMany(mappedBy = "credito", cascade = CascadeType.ALL)
    private List<HistorialCredito> historialCredito;

    public Credito() {}

    public Credito(Long id, Cliente cliente, Date fechaAprobacion, double limiteCredito, double interesAnual,
            Date fechaCorte, double saldoActual, double disponibleParaUsar, List<HistorialCredito> historialCredito) {
        this.id = id;
        this.cliente = cliente;
        this.fechaAprobacion = fechaAprobacion;
        this.limiteCredito = limiteCredito;
        this.interesAnual = interesAnual;
        this.fechaCorte = fechaCorte;
        this.saldoActual = saldoActual;
        this.disponibleParaUsar = disponibleParaUsar;
        this.historialCredito = historialCredito;
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

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public double getInteresAnual() {
        return interesAnual;
    }

    public void setInteresAnual(double interesAnual) {
        this.interesAnual = interesAnual;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public double getDisponibleParaUsar() {
        return disponibleParaUsar;
    }

    public void setDisponibleParaUsar(double disponibleParaUsar) {
        this.disponibleParaUsar = disponibleParaUsar;
    }

    public List<HistorialCredito> getHistorialCredito() {
        return historialCredito;
    }

    public void setHistorialCredito(List<HistorialCredito> historialCredito) {
        this.historialCredito = historialCredito;
    }
    
}
