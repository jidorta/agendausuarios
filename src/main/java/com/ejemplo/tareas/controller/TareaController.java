package com.ejemplo.tareas.controller;


import com.ejemplo.tareas.dto.tarea.TareaDTO;
import com.ejemplo.tareas.model.Tarea;
import com.ejemplo.tareas.repository.TareaRepository;
import com.ejemplo.tareas.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepositorio;

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public List<TareaDTO> listarTareas(){
       return tareaRepositorio.findAll()
               .stream()
               .map(TareaDTO::new)
               .collect(Collectors.toList());
    }

    @PostMapping("/{usuarioId}/tareas")
    public ResponseEntity<Tarea> crearTarea(@PathVariable Long usuarioId, @RequestBody Tarea tarea){
        Tarea nuevaTarea = tareaService.crearTarea(usuarioId,tarea);
        return new ResponseEntity<>(nuevaTarea, HttpStatus.CREATED);

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







