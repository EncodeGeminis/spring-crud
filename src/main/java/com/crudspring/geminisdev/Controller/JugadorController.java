package com.crudspring.geminisdev.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crudspring.geminisdev.Entity.Jugador;
import com.crudspring.geminisdev.Service.JugadorService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    JugadorService jugadorService;

    /*
     * @GetMapping("/listajugadores")
     * public String obtenerListaJugadores(Model model) {
     * List<Jugador> lista= jugadorService.listarJugadores();
     * model.addAttribute("jugadores", lista);
     * model.addAttribute("mensaje", "pagina desde spring con java");
     * return "jugadores";
     * }
     */

    @GetMapping("/listajugadores")
    public String obtenerListaJugadores(@RequestParam(name = "page", defaultValue = "0") int page , Model model) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Jugador> jugadorPage = jugadorService.listarJugadores(pageable);
        //atributos en el modelo 
        model.addAttribute("jugadores", jugadorPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", jugadorPage.getTotalPages());
        return "jugadores";
    }

    @GetMapping("/formularioJugadores")
    public String formularioJugadores(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "agregarJugador";
    }

    @PostMapping("/guardarJugador")
    public String guardarJugador(Jugador jugador) {
        jugadorService.guardaJugador(jugador);
        return "redirect:/jugadores/listajugadores";
    }

    @GetMapping("/eliminarJugador/{id}")
    public String eliminarJugador(@PathVariable("id") Long id) {
        jugadorService.eliminarJugador(id);
        return "redirect:/jugadores/listajugadores";
    }

    @GetMapping("/editarJugador/{id}")
    public String editarJugador(@PathVariable("id") Long id, Model model) {
        var jugador = jugadorService.buscarJugadorPorId(id);
        model.addAttribute("jugador", jugador);
        return "editarJugador";
    }

    @GetMapping("/buscarPorNombreParcial")
    public String buscarNombreParcial(@RequestParam("nombre") String nombre,
    @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Jugador> jugador = jugadorService.buscarPorNombreParcial(nombre, pageable);

        model.addAttribute("jugadores", jugador.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", jugador.getTotalPages());

        return "jugadores";
    }
    @GetMapping("/buscarPorPuntajeMayor")
    public String buscarPorPuntajeMayor(@RequestParam("puntaje") int puntaje,
    @RequestParam(name = "page", defaultValue = "0")int page, Model model) {
        Pageable pageable =PageRequest.of(page, 4);
        Page<Jugador> jugador = jugadorService.buscarPorPuntaje(puntaje, pageable);

        model.addAttribute("jugadores", jugador.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", jugador.getTotalPages());

        return "jugadores";
    }    
}
