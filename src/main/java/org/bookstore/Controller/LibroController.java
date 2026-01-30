package org.bookstore.Controller;

import jakarta.validation.Valid;
import org.bookstore.Dto.Libro.LibroRequestDto;
import org.bookstore.Dto.Libro.LibroResponseDto;
import org.bookstore.Exception.NotFoundException;
import org.bookstore.Model.Libro;
import org.bookstore.Service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
// Controlador para gestionar los libros en la librería
@RestController
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;
// Endpoint para obtener la lista de todos los libros (para Administrador y Usuario)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/obtenerLibros")
    public ResponseEntity<Page<LibroResponseDto>> listaLibros(Pageable pageable) throws NotFoundException {

        Page<LibroResponseDto> listaLibros = libroService.listaLibros(pageable);

        return ResponseEntity.ok(listaLibros);
    }
    // Endpoint para obtener un libro por su ID (para Administrador y Usuario)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/obtenerLibro/{id}")
    public ResponseEntity<LibroResponseDto> obtenerLibro(@PathVariable Long id) throws NotFoundException {

        LibroResponseDto obtenerLibro = libroService.obtenerLibro(id);

        return ResponseEntity.ok(obtenerLibro);

    }
    // Endpoint para crear un nuevo libro (solo para Administrador)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crearLibro")
    public ResponseEntity<LibroResponseDto> crearLibro(@Valid @RequestBody LibroRequestDto libroRequestDto) {

        LibroResponseDto crearLibro = libroService.crearLibro(libroRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(crearLibro.getId()).toUri();

        return ResponseEntity.created(location).body(crearLibro);

    }
    // Endpoint para editar un libro existente (solo para Administrador)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editarLibro/{id}")
    public ResponseEntity<Void> editarLibro(@PathVariable Long id, @Valid @RequestBody LibroRequestDto libroRequestDto) throws NotFoundException {

        libroService.editarLibro(id, libroRequestDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    // Endpoint para eliminar un libro por su ID (solo para Administrador)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminarLibro/{id}")
    public ResponseEntity<Libro> eliminarLibro(@PathVariable Long id) throws NotFoundException {

        libroService.eliminarLibro(id);

        return ResponseEntity.noContent().build();
    }

}
