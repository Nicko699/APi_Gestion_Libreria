package org.bookstore.Exception;
// Excepción personalizada para recursos no encontrados
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}
