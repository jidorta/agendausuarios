package com.ejemplo.tareas.service.impl;

import com.ejemplo.tareas.dto.request.UserUpdateAllRequest;
import com.ejemplo.tareas.dto.user.UserUpdateRequest;
import com.ejemplo.tareas.exception.ResourceNotFoundException;
import com.ejemplo.tareas.model.Role;
import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.repository.RoleRepository;
import com.ejemplo.tareas.repository.UsuarioRepository;
import com.ejemplo.tareas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Usuario crearUsuarioConRol(String username, String password, String nombreRol){
        Role rol = roleRepository.findByName(nombreRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + nombreRol));

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setEmail(username + "@correo.com");

        usuario.getRoles().add(rol);
        return userRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuarioParcial(Long id, UserUpdateRequest request){
        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        if (request.getEmail() != null ){
            user.setEmail(request.getEmail());
        }

        if (request.getPassword() != null){
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public Usuario updateUsuarioTotal(UserUpdateAllRequest request){
        Usuario user = userRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado")) ;

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public Usuario getUsuarioById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    }

    public Usuario save(Usuario user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("El usuario ya existe");
        }
        return userRepository.save(user);
    }
    @Override
    public Usuario update(Long id, Usuario user) {
        // Ejemplo genérico (tú puedes adaptarlo si ya usas los otros updates):
        Usuario existente = getUsuarioById(id);
        existente.setUsername(user.getUsername());
        existente.setEmail(user.getEmail());
        return userRepository.save(existente);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUsuario(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        userRepository.deleteById(id);
    }


}
