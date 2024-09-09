package com.crudspring.geminisdev.Entity;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "producto")
public class Producto implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name = "precio")
    private double precio;
    @Column(name = "cantidad")
    private double cantidad;
    @Column(name = "categoria")
    private String categoria;

    private Producto(){}
    private Producto(Long id, String nombre, double precio, double cantidad){
        this.nombre=nombre;
        this.cantidad=cantidad;
        this.precio=precio;
    }
    //metodos get y set 
    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getCantidad() {
        return cantidad;
    }
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    } 
    public void setCategoria(String categoria){
        this.categoria=categoria;
    }   
    public String getCategoria(){
        return categoria;
    }
}
