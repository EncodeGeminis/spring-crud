package com.crudspring.geminisdev.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crudspring.geminisdev.Entity.Jugador;

@Repository
public interface JugadorDao extends JpaRepository<Jugador, Long> {
    //metodo para buscar el nombre parcial del usuario
    @Query("SELECT j FROM Jugador j WHERE j.nombre LIKE %:nombre%")
    Page<Jugador> buscarPorNombreParcial(@Param("nombre") String nombre, Pageable pageable);
    
    //este metodo busca los jugadores que tienen mayor puntaje
    Page<Jugador> findByPuntajeGreaterThan(int puntaje, Pageable pageable);

}
