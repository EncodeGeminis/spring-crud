package com.crudspring.geminisdev.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crudspring.geminisdev.Entity.Usuario;
import com.crudspring.geminisdev.Service.UsuarioService;
import com.crudspring.geminisdev.Service.VentasService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    VentasService ventasService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/lista")
    public String listaUsuarios(Model model) {
        var usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "Usuarios";
    }

    @PostMapping("buscarUsuario")
    public String buscarUsuario(@RequestParam("nombre") String nombre, Model model) {
        Usuario usuario =usuarioService.buscarPorNombre(nombre);
        model.addAttribute("usuario", usuario);
        model.addAttribute("productos", ventasService.getListaProductos());
        model.addAttribute("totalPagar", ventasService.getTotalPagar());
        return "ventas";
    }
    @GetMapping("/nuevo")
    public String nuevoUsuario(){
        return "AgregarUsuario";
    }
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(Usuario usuario) {
        usuarioService.agregarUsuario(usuario);
        return "redirect:/ventas";
    }
    
    
}
