package com.crudspring.geminisdev.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Producto producto = productoService.buscarProductoPorId(id);
        if (producto != null && producto.getCantidad() > 0) {
            listaCompras.add(producto);
            totalPagar += producto.getPrecio();
            producto.setCantidad(producto.getCantidad() - 1);
            productoService.agregarProducto(producto);
        }
        return "redirect:/ventas";
    }

    @PostMapping("/eliminarProducto/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        Producto producto = listaCompras.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (producto != null) {
            listaCompras.remove(producto);
            totalPagar -= producto.getPrecio();
            producto.setCantidad(producto.getCantidad() + 1);
            productoService.agregarProducto(producto);
        }
        return "redirect:/ventas";
    }

    @PostMapping("/pagar")
    public String pagarCompra(Model model) {

        //funcionalidad para manejar los descuentos en la compra
        double descuento = 0;
        String porcentaje ="0%";

        //se aplica el descuento segun el total a pagar
        if (totalPagar > 5000){
            descuento = totalPagar*0.15; //15% de descuento
            porcentaje = "15%";
        } else if(totalPagar > 1000){
            descuento = totalPagar*0.10; // 10% de descuento
            porcentaje = "10%";
        }

        double totalconDescuento = totalPagar - descuento;
        List<Producto> copiaListaCompras = new ArrayList<>(listaCompras);
        model.addAttribute("total", "Total sin Descuento: "+totalPagar);
        model.addAttribute("listaCompras", copiaListaCompras);
        model.addAttribute("totalDescuento", totalconDescuento);
        model.addAttribute("porcentaje" , porcentaje);
        model.addAttribute("mensaje", "Descuento aplicado: "+descuento);
        
         listaCompras.clear();
        totalPagar = 0;
        return "confirmacion";
    }

    @PostMapping("/eliminarCompra")
    public String eliminarCompra() {
        // Mapa para agrupar los productos por id y sumar las cantidades
        Map<Long, Integer> productosAgrupados = new HashMap<>();

        // Recorre la lista de compras y agrupa las cantidades por id de producto
        for (Producto producto : listaCompras) {
            productosAgrupados.put(producto.getId(),
                    productosAgrupados.getOrDefault(producto.getId(), 0) + 1);
        }

        // Itera sobre los productos agrupados para actualizar la base de datos
        for (Map.Entry<Long, Integer> entry : productosAgrupados.entrySet()) {
            Long productoId = entry.getKey();
            int cantidadADevolver = entry.getValue();

            // Buscar el producto en la base de datos para actualizar su cantidad
            Producto producto = productoService.buscarProductoPorId(productoId);
            if (producto != null) {
                // Incrementa la cantidad en el inventario
                producto.setCantidad(producto.getCantidad() + cantidadADevolver);
                // Guarda el producto actualizado en la base de datos
                productoService.agregarProducto(producto);
            }
        }

        // Limpia la lista de compras y restablece el total a pagar
        listaCompras.clear();
        totalPagar = 0;
        return "redirect:/ventas";
    }

    @GetMapping("/volverVentas")
    public String irVentas() {
        return "redirect:/ventas";
    }

}
