package com.crudspring.geminisdev.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.crudspring.geminisdev.Dao.EmpleadoDao;
import com.crudspring.geminisdev.Entity.Empleado;
import com.crudspring.geminisdev.Service.EmpleadoService;

@Service("EmpleadoService")
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    EmpleadoDao empleadoDao;

    public void agregarEmpleado(Empleado empleado) {
        empleadoDao.save(empleado);
    }

    public List<Empleado> listaEmpleados() {
        var lista = (List<Empleado>) empleadoDao.findAll();
        return lista;
    }

    public void eliminarEmpleado(Long id) {
        empleadoDao.deleteById(id);
    }

    @Override
    public Empleado buscarEmpleadoPorId(Long id) {
        var empleado = empleadoDao.findById(id).orElse(null);
        return empleado;
    }

    /*
     * @Override
    // metodos avanzados de busqueda
    // paginacion
    public Page<Empleado> buscarEmpleadosPaginados(String nombre, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return empleadoDao.findByNombreContaining(nombre, pageable);
    }

    @Override
    // Ordenación
    public List<Empleado> buscarEmpleadosConOrden(String nombre) {
        Sort sort = Sort.by(Sort.Direction.ASC, "edad"); // Ordena por edad de menor a mayor
        return empleadoDao.findByNombreContaining(nombre, sort);
    }
     */
}
