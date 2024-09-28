package com.crudspring.geminisdev.Service;

import java.util.List;


import com.crudspring.geminisdev.Entity.Empleado;

public interface EmpleadoService {
    public void agregarEmpleado(Empleado empleado);
    public List<Empleado> listaEmpleados();
    public void eliminarEmpleado(Long id);
    public Empleado buscarEmpleadoPorId(Long id);
    //metodos que implementan el uso de busquedas personalizadas en el repositorio
    public List<Empleado> buscarPorNombre(String nombre);
    //metodo para validar que un empleado tenga mas de cierta edad
    public List<Empleado>  obtenerEmpleadosPorEdadMayor(int edad);
    //metodo que permite realizar la busqueda de los empleados que tienen el mismo nombre y la misma edad dada
    public List<Empleado> obtenerEmpleadoPorNombreYSueldo(String nombre, int sueldo);
}

