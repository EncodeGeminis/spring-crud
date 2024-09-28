package com.crudspring.geminisdev.ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crudspring.geminisdev.Entity.Articulo;
import com.crudspring.geminisdev.Service.JsonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("ArticuloService")
public class JsonServiceImpl implements JsonService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String archivoJson = "articulo.json";

    // Guardar una lista de articulos en un archivo Json
    public void escribirJson(List<Articulo> articulos) throws IOException {
        objectMapper.writeValue(new File(archivoJson), articulos);
        System.out.println("Archivo Json Escrito correctamente.");
    }

    // Leer una lista con articulos desde el archivo JSon
    public List<Articulo> leerJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Articulo> articulos = new ArrayList<>();
        try {
            // Leer el archivo JSON
            articulos = Arrays.asList(objectMapper.readValue(new File("articulo.json"), Articulo[].class));
        } catch (IOException e) {
            // Manejar la excepción si el archivo no se encuentra o hay un error al leer
            throw new IOException("Error al leer el archivo JSON: " + e.getMessage());
        }
        return articulos;
    }
}
