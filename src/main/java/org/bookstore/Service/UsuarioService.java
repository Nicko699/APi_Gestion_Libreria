package org.bookstore.Service;
import org.bookstore.Dto.Usuario.UsuarioListaResponseDto;
import org.bookstore.Dto.Usuario.UsuarioRequestDto;
import org.bookstore.Dto.Usuario.UsuarioResponseDto;
import org.bookstore.Exception.BadRequestException;
import org.bookstore.Exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// Servicio para gestionar los usuarios en la librería
public interface UsuarioService {

    public Page<UsuarioListaResponseDto>listaUsuarios(Pageable pageable) throws NotFoundException;

    public UsuarioListaResponseDto obtenerUsuario(Long id) throws NotFoundException;

    public UsuarioResponseDto crearUsuario(UsuarioRequestDto usuarioRequestDto) throws BadRequestException;

    public void editarUsuario(Long id, UsuarioRequestDto usuarioRequestDto) throws NotFoundException, BadRequestException;

    public void eliminarUsuario(Long id) throws  NotFoundException;

}
