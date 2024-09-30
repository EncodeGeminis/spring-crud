package com.crudspring.geminisdev.Entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleos")
public class Empleo implements Serializable{
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private String empresa;
    private String cargo;
    private String tipoContrato; //fijo, temporal
    private double ingresoMensual;
    private double ingresoAdicional;
    private int tiempoEmpresa;
    public Empleo() {}
    public Empleo(Long id, Cliente cliente, String empresa, String cargo, String tipoContrato, double ingresoMensual,
            double ingresoAdicional, int tiempoEmpresa) {
        this.id = id;
        this.cliente = cliente;
        this.empresa = empresa;
        this.cargo = cargo;
        this.tipoContrato = tipoContrato;
        this.ingresoMensual = ingresoMensual;
        this.ingresoAdicional = ingresoAdicional;
        this.tiempoEmpresa = tiempoEmpresa;
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
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getTipoContrato() {
        return tipoContrato;
    }
    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    public double getIngresoMensual() {
        return ingresoMensual;
    }
    public void setIngresoMensual(double ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }
    public double getIngresoAdicional() {
        return ingresoAdicional;
    }
    public void setIngresoAdicional(double ingresoAdicional) {
        this.ingresoAdicional = ingresoAdicional;
    }
    public int getTiempoEmpresa() {
        return tiempoEmpresa;
    }
    public void setTiempoEmpresa(int tiempoEmpresa) {
        this.tiempoEmpresa = tiempoEmpresa;
    }
    
}
