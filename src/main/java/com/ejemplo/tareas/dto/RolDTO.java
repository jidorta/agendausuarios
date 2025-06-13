package com.ejemplo.tareas.dto;

import com.ejemplo.tareas.model.Permiso;
import com.ejemplo.tareas.model.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RolDTO {
    private Long id;
    private String name;
    private List<PermisoDTO>permisos;

    public RolDTO(Role rol){
        this.id = rol.getId();
        this.name = rol.getName();
        this.permisos = rol.getPermisos()
                .stream()
                .map(PermisoDTO::new)
                .collect(Collectors.toList());
    }

    public RolDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermisoDTO> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoDTO> permisos) {
        this.permisos = permisos;
    }
}
