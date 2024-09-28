package com.crudspring.geminisdev.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crudspring.geminisdev.Entity.Usuario;

public interface UsuarioService {
    public void agregarUsuario(Usuario usuario);
    public void eliminarUsuario(Long id);
    public Usuario buscarUsuario(Usuario usuario);
    public Usuario buscarUsuarioPorId(Long id);
    //public List<Usuario> listarUsuarios();
    public Page<Usuario> listarUsuarios(Pageable pageable);
    public Page<Usuario> buscarUsuarioPorNombre(String nombre, Pageable pageable);
    public Page<Usuario> buscarPorNombreParcial(String nombre, Pageable pageable);

    public Usuario buscarPorNombre(String nombre);
    public Usuario obtenerUsuarioPorId(Long id);
    public void actualizarUsuario(Usuario usuario);
    //metodos personalizados
    public List<Usuario> buscarListaNombres(String nombre);
    //public List<Usuario> buscarPorNombreParcial(String nombre);

}
