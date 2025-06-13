package com.ejemplo.tareas.dto;

import com.ejemplo.tareas.model.Permiso;

public class PermisoDTO {
    private Long id;
    private String nombre;

    public PermisoDTO(Permiso permiso) {
        this.id = permiso.getId();
        this.nombre = permiso.getNombre();
    }

    public PermisoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
