package com.ejemplo.tareas.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="permisos")
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "permisos")
    private Set<Role> roles;
}
