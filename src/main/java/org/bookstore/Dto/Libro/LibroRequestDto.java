package org.bookstore.Dto.Libro;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
// DTO para representar una solicitud de libro
public class LibroRequestDto {

    @NotEmpty(message = "El titulo no puede estar vacío")
    private String titulo;
    @NotEmpty(message = "El autor no puede estar vacío")
    private String autor;
    @NotEmpty(message = "la fecha de publicación no puede estar vacía")
    private String fecha_publicacion;
    @NotNull(message = "El número de copias disponibles no puede estar vacío")
    private Long copias_disponibles;
    @NotNull(message = "El precio por la copia del libro no puede estar vacío")
    private Double precio_copia;

    public LibroRequestDto() {
    }

    public LibroRequestDto(String titulo, String autor, String fecha_publicacion, Long copias_disponibles, Double precio_copia) {
        this.titulo = titulo;
        this.autor = autor;
        this.fecha_publicacion = fecha_publicacion;
        this.copias_disponibles = copias_disponibles;
        this.precio_copia = precio_copia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public Long getCopias_disponibles() {
        return copias_disponibles;
    }

    public void setCopias_disponibles(Long copias_disponibles) {
        this.copias_disponibles = copias_disponibles;
    }

    public Double getPrecio_copia() {
        return precio_copia;
    }

    public void setPrecio_copia(Double precio_copia) {
        this.precio_copia = precio_copia;
    }
}
