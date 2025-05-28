package com.ejemplo.tareas.service;

import com.ejemplo.tareas.model.Role;

import java.util.List;

public interface RoleService {

        Role save(Role role);
        List<Role> findAll();
        Role findByName(String name);
}
