package org.bookstore.Dto.Libro;

import jakarta.validation.constraints.NotNull;
// DTO para representar una solicitud de compra de un libro
public class LibroCompraRequestDto {
    @NotNull(message = "El id del libro no puede estar vacío ")
    private Long libroId;
    @NotNull(message = "La cantidad de libros no puede estar vacío")
    private Long cantidad_Libros;

    public LibroCompraRequestDto() {
    }

    public LibroCompraRequestDto(Long libroId, Long cantidad_Libros) {
        this.libroId = libroId;
        this.cantidad_Libros = cantidad_Libros;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public Long getCantidad_Libros() {
        return cantidad_Libros;
    }

    public void setCantidad_Libros(Long cantidad_Libros) {
        this.cantidad_Libros = cantidad_Libros;
    }
}
