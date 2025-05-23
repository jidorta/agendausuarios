package com.ejemplo.tareas.service;

import com.ejemplo.tareas.dto.request.UserUpdateAllRequest;
import com.ejemplo.tareas.dto.user.UserUpdateRequest;
import com.ejemplo.tareas.exception.ResourceNotFoundException;
import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

      @Autowired
      UsuarioRepository userRepository;

      @Autowired
      PasswordEncoder passwordEncoder;

    public UserService(UsuarioRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario save(Usuario user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null){
            user.setRole("USER");
        }

        return userRepository.save(user);
    }

    public Usuario updateUser(Long id, UserUpdateRequest request){
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

    public Usuario update(UserUpdateAllRequest request){
        Usuario user = userRepository.findById(request.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado")) ;

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        if (request.getPassword() != null && !request.getEmail().isEmpty()){
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return userRepository.save(user);
    }
}
