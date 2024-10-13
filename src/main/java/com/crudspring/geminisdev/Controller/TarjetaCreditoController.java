package com.crudspring.geminisdev.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crudspring.geminisdev.Entity.HistorialCredito;
import com.crudspring.geminisdev.Entity.TarjetaCredito;
import com.crudspring.geminisdev.Service.HistorialCreditoService;
import com.crudspring.geminisdev.Service.TarjetaCreditoService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/tarjetaCredito")
public class TarjetaCreditoController {

    @Autowired
    private TarjetaCreditoService tarjetaCreditoService;

    @Autowired
    private HistorialCreditoService historialCreditoService;

    @PostMapping("/pagar")
    public String realizarPago(@RequestParam("tarjetaId") Long tarjetaId,
            @RequestParam("montoPago") double montoPago, Model model) {
        try {
            
            // se hace el pago
            TarjetaCredito tarjeta = tarjetaCreditoService.obtenerTarjetaPorId(tarjetaId);
            if (tarjeta != null) {
                // validar si el monto es valido, actualizar el saldo de la tarjeta
                double saldoRestante = tarjeta.getLimiteCredito() - montoPago;
                if (saldoRestante >= 0) {
                    // actualizar el saldo de la tarjeta
                    tarjeta.setLimiteCredito(saldoRestante);
                    tarjetaCreditoService.agregarTarjetaCredito(tarjeta);

                    // Registrar el pago en el historial
                    HistorialCredito historialCredito = new HistorialCredito();
                    historialCredito.setCredito(tarjeta.getCredito());
                    historialCredito.setMonto(montoPago);
                    historialCredito.setFechaPago(new Date());
                    historialCredito.setInteresAcumulado(0);
                    historialCredito.setEstadoPago("pagado");
                    historialCreditoService.registrarPago(historialCredito);

                    model.addAttribute("mensaje", "Pago realizado con exito");
                } else {
                    model.addAttribute("mensaje", "el monto del pago excede el saldo de la tarjeta");
                }
            } else {
                model.addAttribute("mensaje", "Tarjeta no encontrada");
            }
        } catch (Exception e) {
            model.addAttribute("mensaje", "ocurrio un error al realizar el pago");
            e.printStackTrace();
        }
        return "detalleTarjetaCredito";
    }

}
