package org.bookstore.Service;

import org.bookstore.Dto.Libro.LibroRequestDto;
import org.bookstore.Dto.Libro.LibroResponseDto;
import org.bookstore.Exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// Servicio para gestionar los libros en la librería
public interface LibroService {

    public Page<LibroResponseDto> listaLibros(Pageable pageable) throws NotFoundException;

    public LibroResponseDto obtenerLibro(Long id) throws NotFoundException;

    public LibroResponseDto crearLibro(LibroRequestDto libroRequestDto) ;

    public void editarLibro(Long id, LibroRequestDto libroRequestDto) throws NotFoundException;

    public void eliminarLibro(Long id) throws NotFoundException;
}
