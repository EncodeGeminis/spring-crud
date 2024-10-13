package com.crudspring.geminisdev.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crudspring.geminisdev.Entity.Cliente;
import com.crudspring.geminisdev.Entity.Credito;
import com.crudspring.geminisdev.Entity.SolicitudCredito;
import com.crudspring.geminisdev.Service.ClienteService;
import com.crudspring.geminisdev.Service.CreditoService;
import com.crudspring.geminisdev.Service.SolicitudCreditoService;
import com.crudspring.geminisdev.Service.TarjetaCreditoService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/creditos")
public class SolicitudCreditoController {

    @Autowired
    private SolicitudCreditoService solicitudCreditoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    TarjetaCreditoService tarjetaCreditoService;

    @Autowired
    CreditoService creditoService;

    @GetMapping("/mostrarCreditos")
    public String mostrarFormulario(Model model) {
        var listaClientes = clienteService.obtenerClientes();
        model.addAttribute("clientes", listaClientes);
        model.addAttribute("solicitudCredito", new SolicitudCredito());
        return "SolicitudCreditoFormulario";
    }

    // manejar la solicitud del credito
    @PostMapping("/solicitarCredito")
    public String procesarSolicitud(@ModelAttribute SolicitudCredito solicitudCredito, Model model) {
        // setear la fecha de la solicitud al momento actual
        solicitudCredito.setFechaSolicitud(new Date());
        // setear el estado inicial como pendiente
        solicitudCredito.setEstado("pendiente");
        // guardar la solicitud en la base de datos
        solicitudCreditoService.guardarSolicitudCredito(solicitudCredito);

        model.addAttribute("mensaje", "Solicitud enviada con exito");
        return "ResultadoSolicitudCredito";
    }

    @GetMapping("/listaSolicitud")
    public String obtenerListaSolicitudes(Model model) {
        var solicitudes = solicitudCreditoService.obtenerSolicitudes();
        model.addAttribute("solicitudes", solicitudes);
        return "listaSolicitudCredito";
    }

    // metodo para aprobar solicitud, generar credito y tarjeta
    @GetMapping("/aprobarSolicitud/{id}")
    public String aprobarSolicitud(@PathVariable Long id, Model model) {
        SolicitudCredito solicitud = solicitudCreditoService.obtenerSolicitudCreditoPorId(id);

        if (solicitud != null && solicitud.getEstado().equals("pendiente")) {
            // se aprueba la solicitud y se asigna un credito
            Credito credito = solicitudCreditoService.aprobarSolicitud(solicitud);
            if (credito != null) {
                model.addAttribute("mensaje", "La solicitud fue aprobada. se asigno un credito con un limite de " +
                        credito.getLimiteCredito() + " y una tasa de interes anual de " + credito.getInteresAnual()
                        + "%.");
            } else {
                model.addAttribute("mensaje", "Error al aprobar la solicitud");
            }
        } else if (solicitud != null && !solicitud.getEstado().equalsIgnoreCase("pendiente")) {
            model.addAttribute("mensaje", "la solicitud ya fue procesada");
        } else {
            model.addAttribute("mensaje", "no se encontro la solicitud");
        }
        return "resultadoSolicitudCredito";

    }

    @GetMapping("/rechazarSolicitud/{id}")
    public String rechazarSolicitud(@PathVariable Long id, Model model) {
        SolicitudCredito solicitud = solicitudCreditoService.obtenerSolicitudCreditoPorId(id);

        if (solicitud != null && solicitud.getEstado().equals("pendiente")) {
            // cambiamos el estado rechazado.
            solicitud.setEstado("rechazado");
            solicitudCreditoService.guardarSolicitudCredito(solicitud);
            model.addAttribute("mensaje", "la solicitud ha sido rechazada");
        } else {
            model.addAttribute("mensaje", "No se puede realizar la solicitud");
        }

        return "resultadoSolicitudCredito";
    }

    @GetMapping("/editarSolicitud/{id}")
    public String mostrarFormularioEditarCredito(@PathVariable("id") Long id, Model model) {
        SolicitudCredito solicitud = solicitudCreditoService.obtenerSolicitudCreditoPorId(id);
        if (solicitud != null) {
            model.addAttribute("solicitudCredito", solicitud);
            return "editarSolicitudCreditoFormulario";
        } else {
            model.addAttribute("mensaje", "solicitud no encontrada");
            return "ResultadoSolicitudCredito";
        }
    }

    @PostMapping("/editarCredito")
    public String procesarEdicionSolicitud(@ModelAttribute SolicitudCredito solicitudCredito, Model model) {
        // actualizar los datos de la solicitud
        SolicitudCredito solicitudExistente = solicitudCreditoService
                .obtenerSolicitudCreditoPorId(solicitudCredito.getId());
        if (solicitudExistente != null) {
            solicitudExistente.setMontoSolicitado(solicitudCredito.getMontoSolicitado());
            solicitudExistente.setMotivoSolicitud(solicitudCredito.getMotivoSolicitud());
            solicitudExistente.setTipoCredito(solicitudCredito.getTipoCredito());
            solicitudCreditoService.guardarSolicitudCredito(solicitudExistente);
            model.addAttribute("mensaje", "la solicitud se edito exitosamente.");
        } else {
            model.addAttribute("mensaje", "no se pudo editar la solicitud");
        }
        return "ResultadoSolicitudCredito";
    }

    @GetMapping("/eliminarSolicitud/{id}")
    public String eliminarSolicitud(@PathVariable("id") Long id, Model model) {
        SolicitudCredito solicitud = solicitudCreditoService.obtenerSolicitudCreditoPorId(id);
        if (solicitud != null) {
            solicitudCreditoService.eliminarSolicitudCredito(id);
            model.addAttribute("mensaje", "la solicitud ha sido eliminada exitosamente");
        } else {
            model.addAttribute("mensaje", "No se pudo encontrar la solicitud para eliminar");
        }
        return "resultadoSolicitudCredito";
    }

    @GetMapping("/tarjetaCredito/{clienteId}")
    public String mostrarTarjetaCredito(@PathVariable Long clienteId, Model model) {
        System.out.println("Cliente ID recibido: " + clienteId);
        Cliente cliente = clienteService.obtenerClientePorId(clienteId);
        if (cliente != null) {
            // se obtiene la tarjeta del cliente
            var tarjetaCredito = tarjetaCreditoService.obtenerTarjetaPorCliente(cliente);
            if (tarjetaCredito != null) {
                model.addAttribute("tarjetaCredito", tarjetaCredito);
                return "detalleTarjetaCredito";
            } else {
                model.addAttribute("mensaje", "no se encontro una tarjeta de credito asignada");
            }
        } else {
            model.addAttribute("mensaje", "cliente no encontrado");

        }
        return "detalleTarjetaCredito";
    }

}
