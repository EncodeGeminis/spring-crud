package com.crudspring.geminisdev.Entity;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "Producto")
public class Producto implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name = "precio")
    private String precio;
    @Column(name = "cantidad")
    private String cantidad;

    private Producto(){}
    private Producto(Long id, String nombre, String precio, String cantidad){
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
    public String getPrecio() {
        return precio;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }
    public String getCantidad() {
        return cantidad;
    }
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
}
