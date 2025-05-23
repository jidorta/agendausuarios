package com.ejemplo.tareas.dto.response;

public class ApiResponse<T> {

    private String mensaje;
    private T datos;
    private boolean exito;

    public ApiResponse(){}

    public ApiResponse(String mensaje, T datos, boolean exito) {
        this.mensaje = mensaje;
        this.datos = datos;
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getDatos() {
        return datos;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }
}
