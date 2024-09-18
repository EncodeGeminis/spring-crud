package com.crudspring.geminisdev.Service;

import java.util.List;

import com.crudspring.geminisdev.Entity.Usuario;

public interface UsuarioService {
    public void agregarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
    public Usuario buscarUsuario(Usuario usuario);
    public Usuario buscarUsuarioPorId(Long id);
    public List<Usuario> listarUsuarios();
    public Usuario buscarPorNombre(String nombre);
}
