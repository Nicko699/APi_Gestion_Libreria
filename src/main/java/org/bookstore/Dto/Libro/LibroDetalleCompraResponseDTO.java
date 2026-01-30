package org.bookstore.Dto.Libro;
// DTO para representar una respuesta de detalle de compra
public class LibroDetalleCompraResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String fecha_publicacion;
    private Double precio_copia;


    public LibroDetalleCompraResponseDTO() {
    }

    public LibroDetalleCompraResponseDTO(Long id, String titulo, String autor, String fecha_publicacion, Double precio_copia) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fecha_publicacion = fecha_publicacion;
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

    public Double getPrecio_copia() {
        return precio_copia;
    }

    public void setPrecio_copia(Double precio_copia) {
        this.precio_copia = precio_copia;
    }
}
