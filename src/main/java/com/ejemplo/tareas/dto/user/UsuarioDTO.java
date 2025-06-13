package com.ejemplo.tareas.dto.user;

import com.ejemplo.tareas.dto.RolDTO;
import com.ejemplo.tareas.dto.tarea.TareaDTO;
import com.ejemplo.tareas.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDTO {
    private Long id;
    private String username;
    private String email;
    private List<RolDTO> roles;
    private List<TareaDTO>tareas;

    public UsuarioDTO(Usuario usuario){
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.email = usuario.getEmail();
        this.roles = usuario.getRoles()
                .stream()
                .map(RolDTO::new)
                .collect(Collectors.toList());
        this.tareas = usuario.getTarea()
                .stream()
                .map(TareaDTO::new)
                .collect(Collectors.toList());
    }

    public UsuarioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RolDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolDTO> roles) {
        this.roles = roles;
    }

    public List<TareaDTO> getTareas() {
        return tareas;
    }

    public void setTareas(List<TareaDTO> tareas) {
        this.tareas = tareas;
    }
}
