package com.crudspring.geminisdev.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.crudspring.geminisdev.Entity.Producto;
import com.crudspring.geminisdev.Service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/inventario")
    public String inicio(Model model) {
        var lista = productoService.listarProductos();
        model.addAttribute("listaProductos", lista);
        //variable que guarda el total de productos
        var total=0D;
        for (var l : lista){
            total += l.getPrecio() * l.getCantidad();
        }
        model.addAttribute("total", total);
        return "inventario";
    }

    @GetMapping("/agregarProducto")
    public String agregarProducto(Producto producto) {
        return "agregarProducto";
    }
    @PostMapping("/guardarProducto")
    public String guardarProducto(Producto producto) {
        productoService.agregarProducto(producto);
        return "redirect:/inventario";
    }
    @GetMapping("/editarProducto/{id}")
    public String editarProducto(Producto producto, Model model) {
        producto=productoService.buscarProducto(producto);
        model.addAttribute("producto", producto);
        return "editarProducto"; 
    }
    @GetMapping("/eliminarProducto/{id}")
    public String eliminarProducto(Producto producto) {
        productoService.eliminarProducto(producto);
        return "redirect:/inventario";
    }
    
}
