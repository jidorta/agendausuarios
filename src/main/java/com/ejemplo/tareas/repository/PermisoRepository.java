package com.ejemplo.tareas.repository;

import com.ejemplo.tareas.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermisoRepository  extends JpaRepository<Permiso,Long> {
    Optional<Permiso> findByNombre(String nombre);
}
