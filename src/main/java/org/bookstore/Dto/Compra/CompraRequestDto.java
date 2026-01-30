package org.bookstore.Dto.Compra;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.bookstore.Dto.Libro.LibroCompraRequestDto;


import java.util.List;
// DTO para representar una solicitud de compra
public class CompraRequestDto {

    @NotEmpty(message = "La lista no puede estar vacía")
    private List<LibroCompraRequestDto> listaLibros;


    public CompraRequestDto() {
    }

    public CompraRequestDto(List<LibroCompraRequestDto> listaLibros) {
        this.listaLibros = listaLibros;
    }

    public List<LibroCompraRequestDto> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<LibroCompraRequestDto> listaLibros) {
        this.listaLibros = listaLibros;
    }
}
