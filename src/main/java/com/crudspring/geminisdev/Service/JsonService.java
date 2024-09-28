package com.crudspring.geminisdev.Service;

import java.io.IOException;
import java.util.List;

import com.crudspring.geminisdev.Entity.Articulo;

public interface JsonService {

    //guardar una lista de articulos en un archivo Json
    public void escribirJson(List<Articulo> articulos)throws IOException;
    //leer una lista con articulos desde el archivo JSon
    public List<Articulo> leerJson() throws IOException;
}
