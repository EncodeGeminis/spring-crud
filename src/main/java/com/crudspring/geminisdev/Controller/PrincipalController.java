package com.crudspring.geminisdev.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {

    //metodo que muestra la pagina principal
    @GetMapping("/")
    public String irInicio(Model model) {
        return "index";
    }
}
