package com.crudspring.geminisdev.Service;

import java.util.List;

import com.crudspring.geminisdev.Entity.DetalleVenta;
import com.crudspring.geminisdev.Entity.Producto;
import com.crudspring.geminisdev.Entity.Venta;

public interface VentasService {
    public List<Producto> getListaProductos();
    public double getTotalPagar();
    public void agregarProducto(Producto producto);
    public void eliminarProducto(Producto producto);
    public void limpiarCompra();
    public void actualizarTotal(double descuento);

    public Venta guardarVenta(Venta venta);
    public List<Venta> historialVentas();

    public Venta buscarVentaPorId(Long id);
    public List<DetalleVenta> buscarDetallesVentaPorId(Long id);

}
