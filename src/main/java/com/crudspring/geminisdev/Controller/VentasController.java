package com.crudspring.geminisdev.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.crudspring.geminisdev.Entity.DetalleVenta;
import com.crudspring.geminisdev.Entity.Producto;
import com.crudspring.geminisdev.Entity.Venta;
import com.crudspring.geminisdev.Service.ProductoService;
import com.crudspring.geminisdev.Service.UsuarioService;
import com.crudspring.geminisdev.Service.VentasService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/ventas")
public class VentasController {

    @Autowired
    VentasService ventasService;

    @Autowired
    ProductoService productoService;

    @Autowired
    UsuarioService usuarioService;

    List<Producto> listaCompras= new ArrayList<>();
    double totalPagar = 0;

    @GetMapping
    public String mostrarVenta(Model model) {
        var productos = productoService.listarProductos();
        // funcionalidad para manejar los descuentos en la compra
        double descuento = 0;
        String porcentaje = "0%";

        // se aplica el descuento segun el total a pagar
        if (totalPagar > 5000) {
            descuento = totalPagar * 0.15; // 15% de descuento
            porcentaje = "15%";
        } else if (totalPagar > 1000) {
            descuento = totalPagar * 0.10; // 10% de descuento
            porcentaje = "10%";
        }
        double totalconDescuento = totalPagar - descuento;
        model.addAttribute("totalConDescuento", totalconDescuento);
        model.addAttribute("porcentaje", porcentaje);
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
    public String pagarCompra( Model model) {

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
        
        //calcular el 1% de cashback
        double puntosPorAgregar = totalconDescuento*0.01;

        //Crear y guardar la venta 
        Venta venta= new Venta();
        venta.setTotal(totalconDescuento);
        venta.setDescuento(descuento);
        venta.setFechaVenta(LocalDateTime.now());
        //Crear y guardar los detalles de la venta 
        List<DetalleVenta> detallesVenta= new ArrayList<>();

        for(Producto producto : listaCompras){
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setNombre(producto.getNombre());
            detalleVenta.setCantidad(1);
            detalleVenta.setPrecio(producto.getPrecio());
            detalleVenta.setVenta(venta);
            detallesVenta.add(detalleVenta);
        }
        venta.setDetallesVenta(detallesVenta);
        ventasService.guardarVenta(venta);

        List<Producto> copiaListaCompras = new ArrayList<>(listaCompras);
        model.addAttribute("total", "Total sin Descuento: "+totalPagar);
        model.addAttribute("listaCompras", copiaListaCompras);
        model.addAttribute("totalDescuento", totalconDescuento);
        model.addAttribute("porcentaje" , porcentaje);
        model.addAttribute("mensaje", "Descuento aplicado: "+descuento);
        model.addAttribute("puntos", puntosPorAgregar);
        
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
    @GetMapping("/historialVentas")
    public String mostrarHistorialVentas(Model model) {
        List<Venta> historialVentas= ventasService.historialVentas();
        model.addAttribute("historialVentas", historialVentas);
        return "historialVentas";
    }
    @GetMapping("/mostrarDetallesVenta")
    public String mostrarDetallesVenta(@RequestParam("id") Long id, Model model) {
        //buscar la venta por id 
        Venta venta = ventasService.buscarVentaPorId(id);
        if (venta != null) {
            //buscar los detalles de la venta 
            List<DetalleVenta> detallesVenta = ventasService.buscarDetallesVentaPorId(id);
            //se agrega la venta y los detalles al modelo 
            model.addAttribute("venta", venta);
            model.addAttribute("detallesVenta", detallesVenta);
    }
    return "detallesVenta";
}
    

}
