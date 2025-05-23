package com.ejemplo.tareas.controller;

import com.ejemplo.tareas.dto.request.UserCreateRequest;
import com.ejemplo.tareas.dto.request.UserUpdateAllRequest;
import com.ejemplo.tareas.dto.response.ApiResponse;
import com.ejemplo.tareas.dto.user.UserDTO;
import com.ejemplo.tareas.dto.user.UserUpdateRequest;
import com.ejemplo.tareas.mapper.UserMapper;
import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.service.UserService;
import com.ejemplo.tareas.service.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioDetailsService usuarioService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Usuario>>>obtenerTodos(){
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(new ApiResponse<>("Lista de usuarios",usuarios,true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Usuario>>obtenerPorId(@PathVariable Long id){
        Optional<Usuario> usuario  = usuarioService.obtenerPorId(id);
        if (usuario.isPresent()){
            return ResponseEntity.ok(new ApiResponse<>("Usuario encontrado",usuario.get(),true));
        }else{
            return ResponseEntity.status(404).body(new ApiResponse<>("Usuario no encontrado",null,false));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Usuario>>crearUsuario(@RequestBody Usuario usuario){
        Usuario creado = usuarioService.guardar(usuario);
        return ResponseEntity.ok(new ApiResponse<>("Usuario creado correctamente",creado,true));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateRequest request
            ){
        Usuario updateUser = userService.updateUser(id,request);
        return ResponseEntity.ok(UserMapper.toUserDTO(updateUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>eliminarUsuario(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);

        if(usuario.isPresent()){
            usuarioService.eliminar(id);
            return ResponseEntity.ok(new ApiResponse<>("Usuario eliminado correctamente", null, true));
        }else{
            return ResponseEntity.status(404).body(new ApiResponse<>("Usuario NO encontrado",null,false ));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO>updateUser(@RequestBody UserUpdateAllRequest request){
        Usuario updated = userService.update(request);
        return ResponseEntity.ok(UserMapper.toUserDTO(updated));
    }




}
