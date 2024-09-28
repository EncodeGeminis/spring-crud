package com.crudspring.geminisdev.Service;

import java.util.List;


import com.crudspring.geminisdev.Entity.Usuario;

public interface UsuarioService {
    public void agregarUsuario(Usuario usuario);
    public void eliminarUsuario(Long id);
    public Usuario buscarUsuario(Usuario usuario);
    public Usuario buscarUsuarioPorId(Long id);
    public List<Usuario> listarUsuarios();
    public Usuario buscarPorNombre(String nombre);
    public Usuario obtenerUsuarioPorId(Long id);
    public void actualizarUsuario(Usuario usuario);
    //metodos personalizados
    public List<Usuario> buscarListaNombres(String nombre);
    public List<Usuario> buscarPorNombreParcial(String nombre);

}
