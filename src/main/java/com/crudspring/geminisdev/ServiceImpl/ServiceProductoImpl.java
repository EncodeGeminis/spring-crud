package com.crudspring.geminisdev.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudspring.geminisdev.Dao.ProductoDao;
import com.crudspring.geminisdev.Entity.Producto;
import com.crudspring.geminisdev.Service.ProductoService;

@Service("ProductoService")
public class ServiceProductoImpl implements ProductoService {

    @Autowired
    ProductoDao productoDao;

    @Override
    public void agregarProducto(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    public void eliminarProducto(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    public Producto buscarProducto(Producto producto) {
        var productoId = productoDao.findById(producto.getId()).orElse(null);
        return productoId;
    }
    @Override
    public Producto buscarProductoPorId(Long id){
        return productoDao.findById(id).orElse(null);
    }
    
    @Override
    public List<Producto> listarProductos() {
        //se realiza un cast para poder recuperar el objeto lista
        var listaProductos = (List<Producto>) productoDao.findAll(); 
        return listaProductos;
    }

    @Override
    public List<String> listarCategoriasUnicas() {
        return productoDao.findDistinctCategorias();
    }

    @Override
    public List<Producto> listarProductosCategorias(String categoria) {
        return productoDao.findByCategoria(categoria);
    }
}
