package com.ejemplo.tareas.dto;

public class LoginRequest {

    private String username;
    private String password;

    public LoginRequest() {
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