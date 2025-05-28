package com.ejemplo.tareas.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true,nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "roles_permisos",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id"))
    private Set<Permiso>permisos = new HashSet<>();

    @ManyToMany(mappedBy ="roles")
    private Set<Usuario> usuarios = new HashSet<>();

    public Role() {
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
}
