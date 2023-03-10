package com.davidbonelo.libreta.utility;

/**
 * Clase para el manejo de las respuestas de las API
 */
public class Response {

    /**
     * Indica de si existe un error o no en la respuesta del API
     */
    public Boolean error;

    /**
     * Mensaje del API cuando es utilizada
     */
    public String message;

    /**
     * Información del API cuando es necesario
     */
    public Object data;

    /**
     * Constructor de la clase
     */
    public Response() {
        error = false;
        message = "";
        data = null;
    }

    /**
     * Restaura a ceros la respuesta del API
     */
    public void restart() {
        error = false;
        message = "";
        data = null;
    }
}