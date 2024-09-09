package com.crudspring.geminisdev.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.crudspring.geminisdev.Entity.Producto;
import com.crudspring.geminisdev.Service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/ventas")
public class VentasController {

    @Autowired
    ProductoService productoService;

    private List<Producto> listaCompras = new ArrayList<>();
    private double totalPagar = 0.0;

    @GetMapping
    public String mostrarVenta(Model model) {
        var productos = productoService.listarProductos();
        model.addAttribute("productos", productos);
        model.addAttribute("listaCompras", listaCompras);
        model.addAttribute("totalPagar", totalPagar);
        return "ventas";
    }
    
    @PostMapping("/agregarProducto/{id}")
    public String agregarProducto(@PathVariable Long id, Model model) {
        Producto producto= productoService.buscarProductoPorId(id);
        if(producto != null && producto.getCantidad()>0){
            listaCompras.add(producto);
            totalPagar += producto.getPrecio();
            producto.setCantidad(producto.getCantidad() - 1);
            productoService.agregarProducto(producto);
        }
        return "redirect:/ventas";
    }
    @PostMapping("/eliminarProducto/{id}")
    public String eliminarProducto(@PathVariable Long id){
        Producto producto = listaCompras.stream().filter(p ->
        p.getId().equals(id)).findFirst().orElse(null);
        if(producto != null){
            listaCompras.remove(producto);
            totalPagar -= producto.getPrecio();
            producto.setCantidad(producto.getCantidad()+1);
            productoService.agregarProducto(producto);
        }
        return "redirect:/ventas";
    }
    @PostMapping("/pagar")
    public String pagarCompra(Model model) {

        listaCompras.clear();
        totalPagar=0;
        model.addAttribute("mensaje", "compra realizada con exito");
        return "confirmacion";
    }
    @GetMapping("/volverVentas")
    public String irVentas() {
        return "redirect:/ventas";
    }
    
}
