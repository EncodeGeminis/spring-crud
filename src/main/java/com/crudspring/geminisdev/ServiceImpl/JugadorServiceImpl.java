package com.crudspring.geminisdev.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crudspring.geminisdev.Dao.JugadorDao;
import com.crudspring.geminisdev.Entity.Jugador;
import com.crudspring.geminisdev.Service.JugadorService;

@Service
public class JugadorServiceImpl implements JugadorService {
    @Autowired
    JugadorDao jugadorDao;

    /*
     * @Override
        public List<Jugador> listarJugadores() {
            var lista=(List<Jugador>)jugadorDao.findAll();
            return lista;
        }
     */

    @Override
    public Jugador guardaJugador(Jugador jugador) {
        return jugadorDao.save(jugador);
    }

    @Override
    public void eliminarJugador(Long id) {
        Jugador jugador = jugadorDao.findById(id).orElse(null);
        jugadorDao.delete(jugador);
    }

    @Override
    public Jugador buscarJugadorPorId(Long id) {
        return jugadorDao.findById(id).orElse(null);
    }

    @Override
    public Page<Jugador> listarJugadores(Pageable pageable) {
        return jugadorDao.findAll(pageable);
    }

    @Override
    public Page<Jugador> buscarPorNombreParcial(String nombre, Pageable pageable) {
        return jugadorDao.buscarPorNombreParcial(nombre, pageable);    
    }
}
