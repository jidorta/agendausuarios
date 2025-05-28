package com.ejemplo.tareas.repository;

import com.ejemplo.tareas.model.Permiso;
import com.ejemplo.tareas.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

   Optional<Role>  findByName(String name);

}
