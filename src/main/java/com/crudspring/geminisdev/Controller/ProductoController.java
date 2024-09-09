package com.crudspring.geminisdev.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.crudspring.geminisdev.Entity.Producto;
import com.crudspring.geminisdev.Service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/inventario")
    public String inicio(Model model) {
        var lista = productoService.listarProductos();
        var categorias = productoService.listarCategoriasUnicas();
        model.addAttribute("listaProductos", lista);
        model.addAttribute("categorias", categorias);
        //variable que guarda el total de productos
        var total=0D;
        for (var l : lista){
            total += l.getPrecio() * l.getCantidad();
        }
        model.addAttribute("total", total);
        return "inventario";
    }
    @GetMapping("/filtrarCategoria")
    public String filtrarCategoria(@RequestParam String categoria, Model model) {
        List<Producto> lista;
        var categorias = productoService.listarCategoriasUnicas();
        //variable que guarda el total de productos
        //si no se selecciona ninguna categoria muestra todos los productos
        if(categoria == null || categoria.isEmpty()){
            lista= productoService.listarProductos();
        }else{
            lista = productoService.listarProductosCategorias(categoria);
        }
        var total=0D;
        for (var l : lista){
            total += l.getPrecio() * l.getCantidad();
        }
        model.addAttribute("total", total);
        model.addAttribute("listaProductos", lista);
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoriaSeleccionada", categoria);
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
