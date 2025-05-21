package com.ejemplo.tareas.service;

import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id){
      return usuarioRepository.findById(id);
    }

    public Usuario guardar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id){
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario>buscarPorUsername(String username){
        return usuarioRepository.findByUsername(username);
    }
}
