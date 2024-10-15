package com.crudspring.geminisdev.Entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Jugador implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int edad;
    private String userName;
    private int puntaje;
    private String correo;

    //definicion de constructores 
    public Jugador(){}

    public Jugador(String nombre, int edad, String userName, int puntaje, String correo) {
        this.nombre = nombre;
        this.edad = edad;
        this.userName = userName;
        this.puntaje = puntaje;
        this.correo = correo;
    }



    public Jugador(Long id, String nombre, int edad, String userName, int puntaje, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.userName = userName;
        this.puntaje = puntaje;
        this.correo = correo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
