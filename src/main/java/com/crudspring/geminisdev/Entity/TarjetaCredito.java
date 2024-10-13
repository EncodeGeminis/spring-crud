package com.crudspring.geminisdev.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TarjetaCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroTarjeta;
    private double limiteCredito;
    private LocalDate fechaExpiracion;

    // Atributos de los detalles de pago 
    private int plazoEnMeses;
    private double tasaInteres;
    private LocalDate fechaLimitePago;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Nueva relación con Credito
    @ManyToOne
    @JoinColumn(name = "credito_id")
    private Credito credito;  // Aquí se establece la relación con Credito

    public TarjetaCredito() {}

    public TarjetaCredito(Long id, String numeroTarjeta, double limiteCredito, LocalDate fechaExpiracion,
                          int plazoEnMeses, double tasaInteres, LocalDate fechaLimitePago, Cliente cliente, Credito credito) {
        this.id = id;
        this.numeroTarjeta = numeroTarjeta;
        this.limiteCredito = limiteCredito;
        this.fechaExpiracion = fechaExpiracion;
        this.plazoEnMeses = plazoEnMeses;
        this.tasaInteres = tasaInteres;
        this.fechaLimitePago = fechaLimitePago;
        this.cliente = cliente;
        this.credito = credito;  // Inicializar la relación
    }
    

    // Getters y Setters

    public TarjetaCredito(String numeroTarjeta, double limiteCredito, LocalDate fechaExpiracion, Cliente cliente) {
        this.numeroTarjeta = numeroTarjeta;
        this.limiteCredito = limiteCredito;
        this.fechaExpiracion = fechaExpiracion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public int getPlazoEnMeses() {
        return plazoEnMeses;
    }

    public void setPlazoEnMeses(int plazoEnMeses) {
        this.plazoEnMeses = plazoEnMeses;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public LocalDate getFechaLimitePago() {
        return fechaLimitePago;
    }

    public void setFechaLimitePago(LocalDate fechaLimitePago) {
        this.fechaLimitePago = fechaLimitePago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Credito getCredito() {
        return credito;  // Ahora puedes acceder al credito
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }
}

