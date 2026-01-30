package org.bookstore.Exception.Dto;

import java.time.LocalDateTime;
// DTO para representar un mensaje de error
public class MensajeError {
    private int tipoError;
    private String error;
    private String mensaje;
    private LocalDateTime fecha;

    public MensajeError() {
    }

    public MensajeError(int tipoError, String error, String mensaje, LocalDateTime fecha) {
        this.tipoError = tipoError;
        this.error = error;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public int getTipoError() {
        return tipoError;
    }

    public void setTipoError(int tipoError) {
        this.tipoError = tipoError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
