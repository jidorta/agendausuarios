package com.ejemplo.tareas.controller;

import com.ejemplo.tareas.dto.request.UserCreateRequest;
import com.ejemplo.tareas.dto.user.UserDTO;
import com.ejemplo.tareas.dto.user.UserLoginRequest;
import com.ejemplo.tareas.dto.user.LoginResponse;
import com.ejemplo.tareas.mapper.UserMapper;
import com.ejemplo.tareas.model.Usuario;
import com.ejemplo.tareas.security.JwtUtil;
import com.ejemplo.tareas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO>register(@RequestBody UserCreateRequest request){
        Usuario user = UserMapper.toUser(request);
        Usuario saved = userService.save(user);
        UserDTO dto = UserMapper.toUserDTO(saved);
        return ResponseEntity.ok(dto);
    }
}
