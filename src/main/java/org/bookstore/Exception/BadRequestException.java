package org.bookstore.Exception;
// Excepción personalizada para solicitudes incorrectas
public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
