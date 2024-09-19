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
@Table(name = "detalleventa")
public class DetalleVenta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;
    private String nombre;
    private int cantidad;
    private double precio;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    public DetalleVenta() {
    }

    public DetalleVenta(Long idOrden, String nombre, int cantidad, double precio, Venta venta) {
        this.idOrden = idOrden;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.venta = venta;
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
      
}
