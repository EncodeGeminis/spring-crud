package com.crudspring.geminisdev.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crudspring.geminisdev.Entity.Empleado;
import com.crudspring.geminisdev.Service.EmpleadoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/listaEmpleados")
    public String listarEmpleados(Model model) {
        List<Empleado> lista = empleadoService.listaEmpleados();
        model.addAttribute("empleados", lista);
        return "empleados";
    }
    @GetMapping("/agregarEmpleado")
    public String agregarEmpleado(Empleado empleado){
        return "agregarEmpleado";
    }
    @PostMapping("/guardarEmpleado")
    public String guardarEmpleado(Empleado empleado){
        empleadoService.agregarEmpleado(empleado);
        return "redirect:/empleados/listaEmpleados";
    }

    @GetMapping("/eliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable("id") Long id){
        empleadoService.eliminarEmpleado(id);
        return "redirect:/empleados/listaEmpleados";
    }
    @GetMapping("/editarEmpleado/{id}")
    public String editarEmpleado(@PathVariable("id") Long id, Model model){
        var empleado=empleadoService.buscarEmpleadoPorId(id);
        model.addAttribute("empleado", empleado);
        return "editarEmpleado";
    }
    
}
