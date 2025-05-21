package com.ejemplo.tareas.exception;

import com.ejemplo.tareas.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Manejo general para cualquier exception no controlada
    @ExceptionHandler
    public ResponseEntity<ApiResponse<Void>>manejarExcepcionGeneral(Exception ex){
        ApiResponse<Void> respuesta = new ApiResponse<>(
                "Error interno del servidor" + ex.getMessage(),null,false
        );
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>>manejarIllegalArgument(IllegalArgumentException ex){
        ApiResponse<Void> respuesta = new ApiResponse<>(
                "Petición inválida:" + ex.getMessage(), null,false);
        return new ResponseEntity<>(respuesta,HttpStatus.BAD_REQUEST);

    }
}
