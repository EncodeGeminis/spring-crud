package com.crudspring.geminisdev.Service;

import java.util.List;

import com.crudspring.geminisdev.Entity.Producto;

public interface ProductoService {
    //add funcionalidades
    public  void agregarProducto(Producto producto);
    public void eliminarProducto(Producto producto);
    public Producto buscarProducto(Producto producto);
    public List<Producto> listarProductos();
}
