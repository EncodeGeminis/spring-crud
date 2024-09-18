package com.crudspring.geminisdev.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudspring.geminisdev.Dao.UsuarioDao;
import com.crudspring.geminisdev.Entity.Usuario;
import com.crudspring.geminisdev.Service.UsuarioService;

@Service("UsuarioService")
public class ServiceUsuarioImpl implements UsuarioService{

    @Autowired
    UsuarioDao usuarioDao;

    @Override
    public void agregarUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    public Usuario buscarUsuario(Usuario usuario) {
        var usuarioId = usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
        return usuarioId;
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        var usuarioId = usuarioDao.findById(id).orElse(null);
        return usuarioId;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = (List<Usuario>) usuarioDao.findAll();
        return lista;
    }

    @Override
    public Usuario buscarPorNombre(String nombre){
        return usuarioDao.findByNombre(nombre);
    }
}
