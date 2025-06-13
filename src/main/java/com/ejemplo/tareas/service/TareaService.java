package com.ejemplo.tareas.service;

import com.ejemplo.tareas.model.Tarea;
import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.repository.TareaRepository;
import com.ejemplo.tareas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Tarea crearTarea(Long usuarioId, Tarea tarea){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

       // tarea.setUsuario(usuario);
        return tareaRepository.save(tarea);
    }
}
