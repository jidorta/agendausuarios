package com.ejemplo.tareas.service;

import com.ejemplo.tareas.dto.request.UserUpdateAllRequest;
import com.ejemplo.tareas.dto.user.UserUpdateRequest;
import com.ejemplo.tareas.exception.ResourceNotFoundException;
import com.ejemplo.tareas.model.Role;
import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.repository.RoleRepository;
import com.ejemplo.tareas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<Usuario> findAll();
    Usuario crearUsuarioConRol(String username, String password, String nombreRol);
    Usuario getUsuarioById(Long id);
    Usuario save(Usuario user);
    Usuario update(Long id, Usuario user);
    List<Usuario> getAllUsuarios();
    Usuario updateUsuarioParcial(Long id, UserUpdateRequest request);
    Usuario updateUsuarioTotal(UserUpdateAllRequest request);
    void deleteUsuario(Long id);

}
