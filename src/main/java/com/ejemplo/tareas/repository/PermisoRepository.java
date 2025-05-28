package com.ejemplo.tareas.repository;

import com.ejemplo.tareas.model.Permiso;
import com.ejemplo.tareas.model.Role;

import java.util.Optional;

public interface PermisoRepository {
    Optional<Permiso> findByName(String name);
}
