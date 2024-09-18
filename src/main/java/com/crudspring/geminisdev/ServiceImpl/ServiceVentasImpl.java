package com.crudspring.geminisdev.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudspring.geminisdev.Dao.VentaDao;
import com.crudspring.geminisdev.Entity.Producto;
import com.crudspring.geminisdev.Entity.Venta;
import com.crudspring.geminisdev.Service.ProductoService;
import com.crudspring.geminisdev.Service.VentasService;

@Service("VentasService")
public class ServiceVentasImpl implements VentasService {

    @Autowired
    ProductoService productoService;

    @Autowired
    VentaDao ventaDao;

    private List<Producto> listaProductos = new ArrayList<>();
    private List<Producto> listaCompras = new ArrayList<>();
    private double totalPagar = 0;


    public List<Producto> getListaProductos(){
        listaProductos = productoService.listarProductos();
        return listaProductos;
    }
    public double getTotalPagar(){
        return totalPagar;
    }
    public void agregarProducto(Producto producto){
        listaCompras.add(producto);
        totalPagar+=producto.getPrecio();
    }
    public void eliminarProducto(Producto producto){
        listaCompras.remove(producto);
        totalPagar-=producto.getPrecio();
    }
    public void limpiarCompra(){
        listaCompras.clear();
        totalPagar=0;
    }
    public void actualizarTotal(double descuento){
        totalPagar-=descuento;
    }
    public Venta guardarVenta(Venta venta){
        return ventaDao.save(venta);
    }
    public List<Venta> historialVentas(){
        return ventaDao.findAll();
    }

}
