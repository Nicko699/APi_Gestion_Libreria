package org.bookstore.Service;

import org.bookstore.Dto.Libro.LibroRequestDto;
import org.bookstore.Dto.Libro.LibroResponseDto;
import org.bookstore.Exception.NotFoundException;
import org.bookstore.Model.Libro;
import org.bookstore.Repository.LibroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// Servicio para gestionar los libros en la librería
@Service
public class LibroServiceImpl implements LibroService {
    @Autowired
    private LibroRepository libroRepository;

// Método para obtener la lista de todos los libros con paginación
    @Transactional(readOnly = true)
    @Override
    public Page<LibroResponseDto> listaLibros(Pageable pageable) throws NotFoundException {
        ModelMapper modelMapper = new ModelMapper();

        Page<Libro> listaLibros = libroRepository.findAll(pageable);

        if (listaLibros.isEmpty()){
            throw new NotFoundException("No hay libros disponibles");
        }

        List<LibroResponseDto> libroResponseDtos = new ArrayList<>();

        for (Libro libro : listaLibros) {

            LibroResponseDto libroResponseDto = modelMapper.map(libro, LibroResponseDto.class);

            libroResponseDtos.add(libroResponseDto);
        }
        return new PageImpl<>(libroResponseDtos, pageable, listaLibros.getTotalElements());
    }
// Método para obtener un libro por su ID
    @Transactional(readOnly = true)
    @Override
    public LibroResponseDto obtenerLibro(Long id) throws NotFoundException {
        ModelMapper modelMapper = new ModelMapper();

        Optional<Libro> libro = libroRepository.findById(id);

        if (libro.isPresent()) {
            return modelMapper.map(libro.get(), LibroResponseDto.class);
        } else {
            throw new NotFoundException("No se ha encontrado el libro con el id" + id);
        }
    }
// Método para crear un nuevo libro
    @Transactional
    @Override
    public LibroResponseDto crearLibro(LibroRequestDto libroRequestDto) {
        ModelMapper modelMapper = new ModelMapper();
        Libro libro = modelMapper.map(libroRequestDto, Libro.class);
        //seteamos el estado del libro según las copias disponibles
        if (libro.getCopias_disponibles() >0) {
            libro.setEstado("En Stock");
            //si no hay copias disponibles actualizamos el estado del libro a "Agotado"
        } else {
            libro.setEstado("Agotado");
        }
        Libro libroGuardado = libroRepository.save(libro);

        return modelMapper.map(libroGuardado, LibroResponseDto.class);
    }
     // Método para editar un libro existente
    @Transactional
    @Override
    public void editarLibro(Long id, LibroRequestDto libroRequestDto) throws NotFoundException {

        Libro libro = libroRepository.findById(id).orElseThrow(()->
                new NotFoundException("No se ha encontrado el libro con el id:" + id));

            libro.setTitulo(libroRequestDto.getTitulo());
            libro.setAutor(libroRequestDto.getAutor());
            libro.setFecha_publicacion(libroRequestDto.getFecha_publicacion());
            libro.setCopias_disponibles(libroRequestDto.getCopias_disponibles());
            libro.setPrecio_copia(libroRequestDto.getPrecio_copia());

             libroRepository.save(libro);

    }
   // Método para eliminar un libro por su ID
  @Transactional
    @Override
    public void eliminarLibro(Long id) throws NotFoundException {
        Optional<Libro> libro = libroRepository.findById(id);
        if (libro.isPresent()) {
            libroRepository.deleteById(id);
        } else {
            throw new NotFoundException("No se ha encontrado el libro con el id:" + id);
        }

    }
}
