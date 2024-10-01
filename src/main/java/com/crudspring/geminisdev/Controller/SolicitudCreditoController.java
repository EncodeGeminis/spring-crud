package com.crudspring.geminisdev.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crudspring.geminisdev.Entity.SolicitudCredito;
import com.crudspring.geminisdev.Service.ClienteService;
import com.crudspring.geminisdev.Service.SolicitudCreditoService;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/creditos")
public class SolicitudCreditoController {
    
    @Autowired
    private SolicitudCreditoService solicitudCreditoService;
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/mostrarCreditos")
    public String mostrarFormulario(Model model){
        var listaClientes=clienteService.obtenerClientes();
        model.addAttribute("clientes", listaClientes);
        model.addAttribute("solicitudCredito", new SolicitudCredito());
        return "SolicitudCreditoFormulario";
    }
    //manejar la solicitud del credito
    @PostMapping("/solicitarCredito")
    public String procesarSolicitud(@ModelAttribute SolicitudCredito solicitudCredito, Model model) {
        //setear la fecha de la solicitud al momento actual
        solicitudCredito.setFechaSolicitud(new Date());
        //setear el estado inicial como pendiente
        solicitudCredito.setEstado("pendiente");
        //guardar la solicitud en la base de datos
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

    @GetMapping("/aprobarSolicitud/{id}")
    public String aprobarSolicitud(@PathVariable Long id, Model model) {
        SolicitudCredito solicitud = solicitudCreditoService.obtenerSolicitudCreditoPorId(id);

        if(solicitud!=null && solicitud.getEstado().equals("pendiente")){
            //cambiamos el estado a aprobado 
            solicitud.setEstado("aprobado");
            solicitud.setMontoAprobado(solicitud.getMontoSolicitado());
            solicitud.setLimiteCredito(solicitud.getMontoAprobado());

            solicitudCreditoService.guardarSolicitudCredito(solicitud);
            model.addAttribute("mensaje", "la solicitud fue aprobada");            
        } else {
            model.addAttribute("mensaje", "su solicitud no fue aprobada");
        }
        return "resultadoSolicitudCredito";
    }
    
    @GetMapping("/rechazarSolicitud/{id}")
    public String rechazarSolicitud(@PathVariable Long id, Model model) {
        SolicitudCredito solicitud = solicitudCreditoService.obtenerSolicitudCreditoPorId(id);

        if(solicitud != null && solicitud.getEstado().equals("pendiente")){
            //cambiamos el estado rechazado.
            solicitud.setEstado("rechazado");    
            solicitudCreditoService.guardarSolicitudCredito(solicitud);
            model.addAttribute("mensaje", "la solicitud ha sido rechazada"); 
        }else{
            model.addAttribute("mensaje", "No se puede realizar la solicitud");
        }

        return "resultadoSolicitudCredito";
    }

    @GetMapping("/editarSolicitud/{id}")
    public String mostrarFormularioEditarCredito(@PathVariable("id") Long id, Model model) {
        SolicitudCredito solicitud = solicitudCreditoService.obtenerSolicitudCreditoPorId(id);
        if(solicitud != null){
            model.addAttribute("solicitudCredito", solicitud);
            return "editarSolicitudCreditoFormulario";
        }else{
            model.addAttribute("mensaje", "solicitud no encontrada");
        return "ResultadoSolicitudCredito";
        }
    }
    
    @PostMapping("/editarCredito")
    public String procesarEdicionSolicitud(@ModelAttribute SolicitudCredito solicitudCredito, Model model) {
        //actualizar los datos de la solicitud
        SolicitudCredito solicitudExistente = solicitudCreditoService.obtenerSolicitudCreditoPorId(solicitudCredito.getId());
        if(solicitudExistente != null){
            solicitudExistente.setMontoSolicitado(solicitudCredito.getMontoSolicitado());
            solicitudExistente.setMotivoSolicitud(solicitudCredito.getMotivoSolicitud());
            solicitudExistente.setTipoCredito(solicitudCredito.getTipoCredito());
            solicitudCreditoService.guardarSolicitudCredito(solicitudExistente);
            model.addAttribute("mensaje", "la solicitud se edito exitosamente.");
        }else{
            model.addAttribute("mensaje", "no se pudo editar la solicitud");
        }
        return "ResultadoSolicitudCredito";
    }
    
    @GetMapping("/eliminarSolicitud/{id}")
    public String eliminarSolicitud(@PathVariable("id") Long id, Model model) {
        SolicitudCredito solicitud = solicitudCreditoService.obtenerSolicitudCreditoPorId(id);
        if(solicitud != null){
            solicitudCreditoService.eliminarSolicitudCredito(id);
            model.addAttribute("mensaje", "la solicitud ha sido eliminada exitosamente");
        } else{
            model.addAttribute("mensaje", "No se pudo encontrar la solicitud para eliminar");
        }
        return "resultadoSolicitudCredito";
    }
    

}
