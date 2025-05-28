package com.ejemplo.tareas.repository;

import com.ejemplo.tareas.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {


}
