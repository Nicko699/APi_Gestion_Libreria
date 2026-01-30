package org.bookstore.Dto.Libro;
// DTO para representar una respuesta de libro
public class LibroResponseDto {

    private Long id;
    private String titulo;
    private String autor;
    private String fecha_publicacion;
    private Long copias_disponibles;
    private String estado;
    private Double precio_copia;


    public LibroResponseDto() {
    }

    public LibroResponseDto(Long id, String titulo, String autor, String fecha_publicacion, Long copias_disponibles, String estado, Double precio_copia) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fecha_publicacion = fecha_publicacion;
        this.copias_disponibles = copias_disponibles;
        this.estado = estado;
        this.precio_copia = precio_copia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getPrecio_copia() {
        return precio_copia;
    }

    public void setPrecio_copia(Double precio_copia) {
        this.precio_copia = precio_copia;
    }
}
