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
@Table(name = "historial_credito")
public class HistorialCredito implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credito_id", nullable = false)
    private Credito credito;

    private double monto;
    private Date fechaPago;
    private double interesAcumulado;
    private String estadoPago; //pendiente, pagado, retrasado
    public HistorialCredito() {}
    public HistorialCredito(Long id, Credito credito, double monto, Date fechaPago, double interesAcumulado,
            String estadoPago) {
        this.id = id;
        this.credito = credito;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.interesAcumulado = interesAcumulado;
        this.estadoPago = estadoPago;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Credito getCredito() {
        return credito;
    }
    public void setCredito(Credito credito) {
        this.credito = credito;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public Date getFechaPago() {
        return fechaPago;
    }
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
    public double getInteresAcumulado() {
        return interesAcumulado;
    }
    public void setInteresAcumulado(double interesAcumulado) {
        this.interesAcumulado = interesAcumulado;
    }
    public String getEstadoPago() {
        return estadoPago;
    }
    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
    
    
}
