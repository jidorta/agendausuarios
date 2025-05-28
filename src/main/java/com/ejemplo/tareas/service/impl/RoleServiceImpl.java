package com.ejemplo.tareas.service.impl;

import com.ejemplo.tareas.model.Role;
import com.ejemplo.tareas.repository.RoleRepository;
import com.ejemplo.tareas.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }



    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(()->new RuntimeException("Rol no encontrado") );
    }
}
