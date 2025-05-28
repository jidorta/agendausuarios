package com.ejemplo.tareas.config;

import com.ejemplo.tareas.model.Role;
import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.repository.RoleRepository;
import com.ejemplo.tareas.repository.UsuarioRepository;
import com.ejemplo.tareas.service.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        crearRolSiNoExiste("ROLE_USER");
        crearRolSiNoExiste("ROLE_ADMIN");
        // Puedes añadir más roles si los necesitas
    }

    private void crearRolSiNoExiste(String nombreRol) {
        Optional<Role> optionalRole = roleRepository.findByName(nombreRol);
        if (optionalRole.isEmpty()) {
            Role role = new Role();
            role.setName(nombreRol);
            roleRepository.save(role); //  Aquí estaba el fallo
            System.out.println("Rol creado: " + nombreRol);
        } else {
            System.out.println("El rol ya existe: " + nombreRol);
        }

    }



}