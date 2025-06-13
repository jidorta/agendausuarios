package com.ejemplo.tareas.dto.tarea;

import com.ejemplo.tareas.model.Tarea;

public class TareaDTO {
    private Long id;
    private String titulo;
    private String descripcion;

    public TareaDTO(Tarea tarea){
        this.id = tarea.getId();
        this.titulo = tarea.getDescripcion();

    }

    public TareaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
