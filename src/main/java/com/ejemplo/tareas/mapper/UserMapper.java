package com.ejemplo.tareas.mapper;

import com.ejemplo.tareas.dto.request.UserCreateRequest;
import com.ejemplo.tareas.dto.user.UserDTO;
import com.ejemplo.tareas.dto.user.UserUpdateDTO;
import com.ejemplo.tareas.dto.user.UserUpdateRequest;
import com.ejemplo.tareas.model.Role;
import com.ejemplo.tareas.model.Usuario;

import java.util.Set;
import java.util.stream.Collectors;


public class UserMapper {
    public static UserDTO toUserDTO(Usuario user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        Set<String> roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        dto.setRoles(roles);
        return dto;
    }

    public static Usuario toUser(UserCreateRequest dto){
        if (dto == null) return null;

         Usuario user = new Usuario();
         user.setUsername(dto.getUsername());
         user.setPassword(dto.getPassword());
         user.setEmail(dto.getEmail());
         return user;
    }



    public static void updateUserFromDTO(UserUpdateDTO dto, Usuario user){
        if(dto == null || user == null )return;

        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if(dto.getPassword() != null) user.setPassword(dto.getPassword());


    }

    public void updateUserFromRequest(UserUpdateRequest req, Usuario user){
        if (req.getEmail() != null){
            user.setEmail(req.getEmail());

        }
        if (req.getPassword() != null){
            user.setPassword(req.getPassword());
        }
    }


}
