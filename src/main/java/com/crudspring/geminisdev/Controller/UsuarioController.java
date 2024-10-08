package com.crudspring.geminisdev.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crudspring.geminisdev.Entity.Usuario;
import com.crudspring.geminisdev.Service.UsuarioService;
import com.crudspring.geminisdev.Service.VentasService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    VentasService ventasService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/lista")
    public String listaUsuarios(@RequestParam(name = "page", defaultValue= "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Usuario> usuarioPage = usuarioService.listarUsuarios(pageable);
        // Agregar atributos al modelo
        model.addAttribute("usuarios", usuarioPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usuarioPage.getTotalPages());
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
    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios/lista";
    }

    @GetMapping("/editarUsuario/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        return "editarUsuario"; // Nombre de la plantilla de editar usuario
    }

    @PostMapping("/actualizarUsuario")
    public String actualizarUsuario(Usuario usuario) {
        usuarioService.actualizarUsuario(usuario);
        return "redirect:/usuarios/lista"; // O la página a la que desees redirigir
    }
    @GetMapping("/buscarPorNombre")
    public String buscarPorNombre(@RequestParam("nombre") String nombre,
        @RequestParam(name="page", defaultValue = "0") int page, Model model) {
            Pageable pageable = PageRequest.of(page, 4);
            Page <Usuario> usuarios = usuarioService.buscarUsuarioPorNombre(nombre, pageable);

        model.addAttribute("usuarios", usuarios.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usuarios.getTotalPages());
        return "Usuarios";
    }
    @GetMapping("/buscarPorNombreParcial")
    public String buscarPorNombreParcial(@RequestParam("nombre") String nombre,
    @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Usuario> usuarios = usuarioService.buscarPorNombreParcial(nombre, pageable);

        model.addAttribute("usuarios", usuarios.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usuarios.getTotalPages());
        
        return "Usuarios";
    }
}
