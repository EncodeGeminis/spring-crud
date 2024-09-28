package com.crudspring.geminisdev.Service;

import java.util.List;

import com.crudspring.geminisdev.Entity.Producto;

public interface ProductoService {
    //add funcionalidades
    public  void agregarProducto(Producto producto);
    public void eliminarProducto(Producto producto);
    public Producto buscarProducto(Producto producto);
    public Producto buscarProductoPorId(Long id);
    public List<Producto> listarProductos();
    public List<String> listarCategoriasUnicas();
    public List<Producto> listarProductosCategorias(String categoria);
    //metodos personalizados en el repositorio
    public List<Producto> buscarProductoPorNombre(String nombre);
}
