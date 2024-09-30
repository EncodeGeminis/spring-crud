package com.crudspring.geminisdev.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crudspring.geminisdev.Entity.Cliente;
import com.crudspring.geminisdev.Service.ClienteService;


@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/lista")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.obtenerClientes();
        model.addAttribute("clientes", clientes);
        model.addAttribute("mensaje", "mensaje desde el controlador");
        return "listaClientes";  // Vista para mostrar la lista de clientes
    }

    @GetMapping("/nuevoCliente")
    public String mostrarFormularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());  // Se pasa un nuevo objeto Cliente al formulario
        return "formularioCliente";  // Vista del formulario de Thymeleaf
    }

    @PostMapping("/guardarCliente")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardarCliente(cliente);  // Guardar el cliente en la base de datos
        return "redirect:/clientes/lista";  // Redirigir a la lista de clientes
    }

    @GetMapping("/editarCliente/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "listaClientes";
    }

    @PostMapping("/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "listaClientes";
    }
}

