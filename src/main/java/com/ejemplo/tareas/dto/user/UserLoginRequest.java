package com.ejemplo.tareas.dto.user;

public class UserLoginRequest {

    private String username;
    private String password;

    public UserLoginRequest() {
    }
    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}