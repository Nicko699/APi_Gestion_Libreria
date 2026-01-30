package org.bookstore.Exception;
import org.bookstore.Exception.Dto.MensajeError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// Manejador global de excepciones para la aplicación
@ControllerAdvice
public class ResponseEntityExceptionHandler {
// Manejador para excepciones de tipo NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MensajeError> notFoundException(NotFoundException exception) {

        MensajeError mensajeError = new MensajeError(404,"NOT_FOUND",exception.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);

    }
    //Manejador para excepciones de validación de argumentos de método
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {

        Map<String, String> listaErrores = new HashMap<>();
// Obtenemos los errores de validación
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
// Recorremos sobre los errores y los agregamos al mapa de errores
        for (FieldError fieldError : fieldErrors) {
            String fieldName = fieldError.getField();
            String fieldMessage = fieldError.getDefaultMessage();

            listaErrores.put(fieldName, fieldMessage);

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaErrores);

    }

    //Manejar los errores de BadRequestException
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MensajeError>BadRequestException(BadRequestException exception){

        MensajeError mensajeError=new MensajeError(400,"BAD_REQUEST",exception.getMessage(),LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);

    }



}
