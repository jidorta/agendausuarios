package com.ejemplo.tareas.config;

import com.ejemplo.tareas.model.Permiso;
import com.ejemplo.tareas.model.Role;
import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.repository.PermisoRepository;
import com.ejemplo.tareas.repository.RoleRepository;
import com.ejemplo.tareas.repository.UsuarioRepository;
import com.ejemplo.tareas.service.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermisoRepository permisoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        crearPermisosSiNoExisten();
        crearRolSiNoExisteConPermisos("ROLE_ADMIN",List.of("LEER_TAREAS","CREAR_TAREAS"));
        crearRolSiNoExisteConPermisos("ROLE_USER",List.of("LEER_TAREAS"));


        if (!usuarioRepository.existsByUsername("admin")){

            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setEmail("admin@correo.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(()-> new RuntimeException("Rol ROLE_ADMIN no encontrado"));
            admin.getRoles().add(adminRole);
            usuarioRepository.save(admin);
            System.out.println ("Usuario admin creado");
        }else{
            System.out.println("El usuario admin ya existe");
        }
        if (roleRepository.count() == 0){
            // Leer tareas
            Permiso leerTareas = new Permiso();
            leerTareas.setNombre("LEER_TAREAS");

            // Crear tareas
            Permiso crearTareas = new Permiso();
            crearTareas.setNombre("CREAR_TAREAS");
            permisoRepository.saveAll(List.of(leerTareas,crearTareas));



        }
    }

    private void crearPermisosSiNoExisten(){
        crearPermisosSiNoExiste("LEER_TAREAS");
        crearPermisosSiNoExiste("CREAR_TAREAS");
    }

    private void crearPermisosSiNoExiste(String nombre){
        if(permisoRepository.findByNombre(nombre).isEmpty()){
            Permiso permiso = new Permiso();
            permiso.setNombre(nombre);
            permisoRepository.save(permiso);
        }
    }

    private void crearRolSiNoExisteConPermisos(String nombreRol, List<String>permisosNombres){
        if (roleRepository.findByName(nombreRol).isEmpty()){
            Role role = new Role();
            role.setName(nombreRol);


            List<Permiso> permisos = permisosNombres.stream()
                    .map(nombre -> permisoRepository.findByNombre(nombre)
                            .orElseThrow(() ->new RuntimeException("Permiso"+ nombre + "no encontrado")))
                    .toList();

            role.setPermisos(new HashSet<>(permisos));
            roleRepository.save(role);
        }
    }




}