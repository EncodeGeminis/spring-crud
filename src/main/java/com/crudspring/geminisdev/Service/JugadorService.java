package com.crudspring.geminisdev.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crudspring.geminisdev.Entity.Jugador;

public interface JugadorService {
    /*
     * public List<Jugador> listarJugadores();
     */
    public Jugador guardaJugador(Jugador jugador);
    public void eliminarJugador(Long id);
    public Jugador buscarJugadorPorId(Long id);

    //metodo que muestra los jugadores de 4 en 4
    public Page<Jugador> listarJugadores(Pageable pageable);
    //metodo para mostrar los jugadores que tienen el mismo nombre
    public Page<Jugador> buscarPorNombreParcial(String nombre, Pageable pageable);
    //metodo para mostrar los jugadores que tienen mayor puntaje
    public Page<Jugador> buscarPorPuntaje(int puntaje, Pageable pageable);

}
