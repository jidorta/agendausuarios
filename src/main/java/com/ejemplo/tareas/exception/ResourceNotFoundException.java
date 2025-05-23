package com.ejemplo.tareas.exception;

public class ResourceNotFoundException  extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
}
