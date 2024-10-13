package com.crudspring.geminisdev.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SimuladorController {
   
    @GetMapping("/simulador")
    public String mostrarSimulador(){
        return "simulador";
    }

    @PostMapping("/simulador")
    public String calcularSimulador(
        @RequestParam("monto") double monto, 
        @RequestParam("plazo") int plazo, 
        @RequestParam("interes") double interes,
        Model model)
    {
        //convertir la tasa de interes anual a mensual
        double tasaMensual = (interes / 100) / 12;
        //calculo del pago mensual
        double pagoMensual = (tasaMensual * monto) / (1-Math.pow(1+tasaMensual, -plazo));
        //calculo del costo total del credito
        double costoTotal = pagoMensual * plazo;
        //añadir los valores al modelo de la vista 
        model.addAttribute("monto", monto);
        model.addAttribute("plazo", plazo);
        model.addAttribute("interes", interes);
        model.addAttribute("pagoMensual", String.format("%.2f", pagoMensual));
        model.addAttribute("costoTotal", String.format("%.2f", costoTotal));
        return "simulador";   
    }
    
}
