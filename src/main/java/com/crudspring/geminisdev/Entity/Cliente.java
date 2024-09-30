package com.crudspring.geminisdev.Entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String nombre;
    private String fechaNacimiento;
    private String curp;
    private String rfc;
    private String direccion;
    private String telefono;
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Empleo> empleos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<SolicitudCredito> solicitudesCredito;

    public Cliente() {}

    public Cliente(Long idCliente, String nombre, String fechaNacimiento, String curp, String rfc, String direccion,
            String telefono, String email, List<Empleo> empleos, List<SolicitudCredito> solicitudesCredito) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.curp = curp;
        this.rfc = rfc;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.empleos = empleos;
        this.solicitudesCredito = solicitudesCredito;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Empleo> getEmpleos() {
        return empleos;
    }

    public void setEmpleos(List<Empleo> empleos) {
        this.empleos = empleos;
    }

    public List<SolicitudCredito> getSolicitudesCredito() {
        return solicitudesCredito;
    }

    public void setSolicitudesCredito(List<SolicitudCredito> solicitudesCredito) {
        this.solicitudesCredito = solicitudesCredito;
    }
}
