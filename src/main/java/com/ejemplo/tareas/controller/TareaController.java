package com.ejemplo.tareas.controller;


import com.ejemplo.tareas.model.Tarea;
import com.ejemplo.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepositorio;

    @GetMapping
    public List<Tarea> listarTareas(){
        return tareaRepositorio.findAll();
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea){
        return tareaRepositorio.save(tarea);
    }

    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Long id, @RequestBody Tarea tareaActualizada){
        return tareaRepositorio.findById(id).map(tarea ->{
            tarea.setDescripcion(tareaActualizada.getDescripcion());
            tarea.setCompletada(tareaActualizada.isCompletada());
            return tareaRepositorio.save(tarea);
        }).orElseGet(() ->{
            tareaActualizada.setId(id);
            return tareaRepositorio.save(tareaActualizada);
        });
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id){
        tareaRepositorio.deleteById(id);
    }
}







