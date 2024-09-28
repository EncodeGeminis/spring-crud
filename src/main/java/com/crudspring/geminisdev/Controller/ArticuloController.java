package com.crudspring.geminisdev.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.crudspring.geminisdev.Entity.Articulo;
import com.crudspring.geminisdev.Service.JsonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;




@Controller
public class ArticuloController {
    @Autowired
    private JsonService jsonService;

    private List<Articulo> listarArticulos = new ArrayList<>();

    //Mostrar el formulario
    @GetMapping("/articulos")
    public String mostrarFormularioArticulo(Model model) {
        model.addAttribute("articulo", new Articulo());
        return "articulos";
    }
    //Guardar articulos en el archivo Json desde la vista 
    @PostMapping("/articulos/guardarArticulos")
    public String guardarArticulo(@ModelAttribute Articulo articulo, Model model) {
        try{
            //generar un id unico 
            articulo.setId(listarArticulos.size());
            listarArticulos.add(articulo);
            jsonService.escribirJson(listarArticulos);
            model.addAttribute("mensaje", "producto guardado exitosamente");
            model.addAttribute("articulo", new Articulo());//resetea el formulario
        } catch (IOException e) {
            model.addAttribute("mensaje","Error al guardar el producto"+e.getMessage());
        }
        return "articulos";
    }

    //Obtener articulos desde el archivo json (opcional)
    @GetMapping("/articulos/listarArticulos")
    public String listarArticulos(Model model) {
        try {
            List<Articulo> articulos = jsonService.leerJson();
            model.addAttribute("articulos", articulos);    
        } catch (IOException e) {
            model.addAttribute("mensaje", "Error al listar Articulos" + e.getMessage());
        }
        return "articulos";
    }
    
    
}
