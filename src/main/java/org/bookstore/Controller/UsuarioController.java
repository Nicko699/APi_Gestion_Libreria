package org.bookstore.Controller;

import jakarta.validation.Valid;
import org.bookstore.Dto.Usuario.UsuarioListaResponseDto;
import org.bookstore.Dto.Usuario.UsuarioRequestDto;
import org.bookstore.Dto.Usuario.UsuarioResponseDto;
import org.bookstore.Exception.BadRequestException;
import org.bookstore.Exception.NotFoundException;
import org.bookstore.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
// Controlador para gestionar los usuarios en la librería
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    // Endpoint para obtener la lista de todos los usuarios (solo para Administrador)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtenerUsuarios")
    public ResponseEntity<Page<UsuarioListaResponseDto>>listaUsuarios(Pageable pageable) throws NotFoundException {

        Page< UsuarioListaResponseDto> listaUsuarios= usuarioService.listaUsuarios(pageable);

        return ResponseEntity.ok(listaUsuarios);

    }
    // Endpoint para obtener un usuario por su ID (solo para Administrador)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtenerUsuario/{id}")
    public ResponseEntity<UsuarioListaResponseDto>obtenerUsuario(@PathVariable Long id)throws NotFoundException{

        UsuarioListaResponseDto obtenerUsuario=usuarioService.obtenerUsuario(id);

        return ResponseEntity.ok(obtenerUsuario);
    }
// Endpoint para crear un nuevo usuario (solo para Administrador)
    @PostMapping("/crearUsuario")
    public ResponseEntity<UsuarioResponseDto>crearUsuario(@Valid  @RequestBody UsuarioRequestDto usuarioRequestDto) throws BadRequestException {

        UsuarioResponseDto crearUsuario=usuarioService.crearUsuario(usuarioRequestDto);

        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(crearUsuario.getId()).toUri();

        return ResponseEntity.created(location).body(crearUsuario);
    }
// Endpoint para editar un usuario existente (solo para Administrador)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editarUsuario/{id}")
    public ResponseEntity<Void>editarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDto usuarioRequestDto) throws NotFoundException, BadRequestException {

        usuarioService.editarUsuario(id, usuarioRequestDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    // Endpoint para eliminar un usuario por su ID (solo para Administrador)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("eliminarUsuario/{id}")
    public ResponseEntity<UsuarioResponseDto>eliminarUsuario(@PathVariable Long id)throws  NotFoundException{

        usuarioService.eliminarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}
