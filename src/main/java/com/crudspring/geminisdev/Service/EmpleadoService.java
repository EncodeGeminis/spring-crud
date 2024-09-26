package com.crudspring.geminisdev.Service;

import java.util.List;


import com.crudspring.geminisdev.Entity.Empleado;

public interface EmpleadoService {
    public void agregarEmpleado(Empleado empleado);
    public List<Empleado> listaEmpleados();
    public void eliminarEmpleado(Long id);
    public Empleado buscarEmpleadoPorId(Long id);

    /*
     * //metodos avanzados de busqueda 
    //paginacion 
    public Page<Empleado> buscarEmpleadosPaginados(String nombre, int page, int size);
    // Ordenación
    public List<Empleado> buscarEmpleadosConOrden(String nombre);
     */
}
