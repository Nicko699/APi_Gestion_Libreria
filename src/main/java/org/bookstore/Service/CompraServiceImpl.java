package org.bookstore.Service;

import org.bookstore.Dto.Compra.CompraRequestDto;
import org.bookstore.Dto.Compra.CompraResponseDto;
import org.bookstore.Dto.Libro.LibroCompraRequestDto;
import org.bookstore.Exception.BadRequestException;
import org.bookstore.Exception.NotFoundException;
import org.bookstore.Model.Compra;
import org.bookstore.Model.DetallesCompra;
import org.bookstore.Model.Libro;
import org.bookstore.Model.Usuario;
import org.bookstore.Repository.CompraRepository;
import org.bookstore.Repository.DetallesCompraRepository;
import org.bookstore.Repository.LibroRepository;
import org.bookstore.Repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// Implementación del servicio para gestionar las compras en la librería
@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DetallesCompraRepository detallesCompraRepository;

// Listar todas las compras (solo para Administrador)
    @Transactional(readOnly = true)
    @Override
    public Page<CompraResponseDto> listaCompras(Pageable pageable)throws NotFoundException {

        ModelMapper modelMapper = new ModelMapper();

        Page<Compra> listaCompras = compraRepository.findAll(pageable);

        if (listaCompras.isEmpty()){

            throw new NotFoundException("No hay compras realizadas");
        }

        List<CompraResponseDto> listaComprasDto = new ArrayList<>();
// Convertimos cada compra a su DTO correspondiente
        for (Compra compra : listaCompras) {

            CompraResponseDto compraResponseDto = modelMapper.map(compra, CompraResponseDto.class);

            listaComprasDto.add(compraResponseDto);

        }
        //retornamos la lista de compras en formato paginado
        return new PageImpl<>(listaComprasDto, pageable, listaCompras.getTotalElements());
    }
// Listar las compras del usuario autenticado (solo para Usuario)
    @Transactional(readOnly = true)
    @Override
    public Page<CompraResponseDto> listaComprasUsuario(Pageable pageable, Jwt jwt)throws NotFoundException {

        ModelMapper modelMapper = new ModelMapper();
// Obtenemos el correo del usuario desde el JWT
        String correo=jwt.getClaimAsString("email");
// Buscamos el usuario en la base de datos por su correo
        Usuario usuario=usuarioRepository.findByEmail(correo)
                .orElseThrow(()->new NotFoundException("Usuario no encontrado en el sistema"));

        Page<Compra> listaCompras = compraRepository.findAllByUsuario(usuario,pageable );

        if (listaCompras.isEmpty()){

            throw new NotFoundException("No hay compras realizadas");
        }

        List<CompraResponseDto> listaComprasDto = new ArrayList<>();
// Convertimos cada compra a su DTO correspondiente
        for (Compra compra : listaCompras) {

            CompraResponseDto compraResponseDto = modelMapper.map(compra, CompraResponseDto.class);

            listaComprasDto.add(compraResponseDto);

        }
        //retornamos la lista de compras en formato paginado
        return new PageImpl<>(listaComprasDto, pageable, listaCompras.getTotalElements());
    }

// Obtener una compra por su ID (solo para Administrador)
    @Transactional(readOnly = true)
    @Override
    public CompraResponseDto obtenerCompra(Long id) throws NotFoundException {

        ModelMapper modelMapper = new ModelMapper();

        Optional<Compra> obtenerCompra = compraRepository.findById(id);

        if (obtenerCompra.isPresent()) {
            return modelMapper.map(obtenerCompra.get(), CompraResponseDto.class);
        }

        throw new NotFoundException("No se ha encontrado la compara con el id: " + id);
    }
// Obtener una compra por su ID (solo para Usuario)
    @Transactional
    @Override
    public CompraResponseDto obtenerCompraUsuario(Long id, Jwt jwt) throws NotFoundException {
        ModelMapper modelMapper = new ModelMapper();

        String correo=jwt.getClaimAsString("email");

        Usuario usuario=usuarioRepository.findByEmail(correo)
                .orElseThrow(()->new NotFoundException("Usuario no encontrado en el sistema"));

        Optional<Compra> obtenerCompra = compraRepository.findByIdAndUsuario(id,usuario);

        if (obtenerCompra.isPresent()) {
            return modelMapper.map(obtenerCompra.get(), CompraResponseDto.class);
        }
        throw new NotFoundException("No se ha encontrado la compara con el id: " + id);
    }
// Crear una nueva compra (para Administrador y Usuario)
    @Transactional
    @Override
    public CompraResponseDto crearCompra(CompraRequestDto compraRequestDto, Jwt jwt) throws NotFoundException, BadRequestException {
        
        ModelMapper modelMapper = new ModelMapper();

        Compra compra = modelMapper.map(compraRequestDto, Compra.class);

        String correo=jwt.getClaimAsString("email");

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(correo);
// si el usuario autenticado existe lo asignamos a la compra
        if (usuarioEncontrado.isPresent()) {
            compra.setUsuario(usuarioEncontrado.get());
        } else {
            throw new NotFoundException("No se ha encontrado el usuario con el correo:" + correo);
        }


        List<DetallesCompra> listaDetallesCompra = new ArrayList<>();

// Variables para calcular el total de la compra y las copias restantes
        long copiasRestantes;
        double totalCompra = 0;
        double subTotalCompra;
        long totalCopiasLibros = 0;
      //si la lista de libros no es nula procesamos cada libro
        if (compraRequestDto.getListaLibros() != null) {
         // Recorremos la lista de libros en la solicitud de compra
            for (LibroCompraRequestDto libroDto : compraRequestDto.getListaLibros()) {
           // Buscamos el libro en la base de datos por su ID
                Libro libroObtenido = libroRepository.findById(libroDto.getLibroId()).orElse(null);
             // Si el libro existe verificamos la disponibilidad de copias
                if ((libroObtenido != null)) {
               // Verificamos si hay copias disponibles para la compra
                    if (libroObtenido.getCopias_disponibles() >= libroDto.getCantidad_Libros()) {
                   // Calculamos las copias restantes después de la compra
                        copiasRestantes = libroObtenido.getCopias_disponibles() - libroDto.getCantidad_Libros();

                       // Si hay copias suficientes actualizamos el libro y creamos el detalle de compra
                        if (copiasRestantes >= 0) {

                            libroObtenido.setCopias_disponibles(copiasRestantes);

                            libroRepository.save(libroObtenido);

                            subTotalCompra = libroObtenido.getPrecio_copia() * libroDto.getCantidad_Libros();

                            DetallesCompra detallesCompra=new DetallesCompra();

                            detallesCompra.setCompra(compra);
                            detallesCompra.setLibro(libroObtenido);
                            detallesCompra.setCantidad(libroDto.getCantidad_Libros());
                            detallesCompra.setSubTotal(subTotalCompra);
                            detallesCompra.setPrecioUnitario(libroObtenido.getPrecio_copia());

                            listaDetallesCompra.add(detallesCompra);
                       // Actualizamos el total de la compra y el total de copias de libros
                            totalCompra += libroObtenido.getPrecio_copia() * libroDto.getCantidad_Libros();
                            totalCopiasLibros += libroDto.getCantidad_Libros();
                        //si no hay copias disponibles actualizamos el estado del libro y lanzamos una excepción
                        } else {
                            libroObtenido.setEstado("Agotado");
                            libroRepository.save(libroObtenido);

                            throw new NotFoundException("No hay copias disponibles del libro: " + libroObtenido.getTitulo());
                        }
                        //si las copias restantes son cero actualizamos el estado del libro a "Agotado"
                        if (copiasRestantes == 0) {
                            libroObtenido.setEstado("Agotado");
                            libroRepository.save(libroObtenido);
                        }

                   //si no hay copias suficientes lanzamos una excepción
                    } else {
                        throw new NotFoundException("Copias del libro insuficientes:" + libroObtenido.getCopias_disponibles());
                    }

                //si el libro no existe lanzamos una excepción
                } else {
                    throw new NotFoundException("No se ha encontrado el libro con el id:" + libroDto.getLibroId());
                }

            }

            compra.setEstado_compra("Comprado");
            compra.setFecha_compra(LocalDate.now());
            compra.setListaDetallesCompra(listaDetallesCompra);
            compra.setTotal_pagar(totalCompra);
            compra.setCantidad(totalCopiasLibros);

        //si la lista de libros es nula lanzamos una excepción

        } else {
            throw new BadRequestException("No hay libros seleccionados");
        }


        Compra compraGuardada = compraRepository.save(compra);
        
        detallesCompraRepository.saveAll(listaDetallesCompra);


        return modelMapper.map(compraGuardada, CompraResponseDto.class);

    }



}
