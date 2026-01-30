package org.bookstore.Service;

import org.bookstore.Dto.Rol.RolResponseDto;
import org.bookstore.Dto.Usuario.UsuarioListaResponseDto;
import org.bookstore.Dto.Usuario.UsuarioRequestDto;
import org.bookstore.Dto.Usuario.UsuarioResponseDto;
import org.bookstore.Exception.BadRequestException;
import org.bookstore.Exception.NotFoundException;
import org.bookstore.Model.Rol;
import org.bookstore.Model.Usuario;
import org.bookstore.Repository.RolRepository;
import org.bookstore.Repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
// Servicio para gestionar los usuarios en la librería
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

   // Método para obtener la lista de todos los usuarios con paginación
    @Transactional(readOnly = true)
    @Override
    public Page<UsuarioListaResponseDto> listaUsuarios(Pageable pageable) throws NotFoundException {

        ModelMapper modelMapper = new ModelMapper();

        Page<Usuario> listaUsuarios = usuarioRepository.findAll(pageable);

        if (listaUsuarios.isEmpty()){
            throw new NotFoundException("No hay usuarios en el sistema");
        }

        List<UsuarioListaResponseDto> listaUsuarioResponse = new ArrayList<>();

        for (Usuario usuario : listaUsuarios) {


            UsuarioListaResponseDto usuarioResponseDto = modelMapper.map(usuario, UsuarioListaResponseDto.class);

            listaUsuarioResponse.add(usuarioResponseDto);
        }

        return new PageImpl<>(listaUsuarioResponse, pageable, listaUsuarios.getTotalElements());

    }
   // Método para obtener un usuario por su ID
    @Transactional(readOnly = true)
    @Override
    public UsuarioListaResponseDto obtenerUsuario(Long id) throws NotFoundException {
        ModelMapper modelMapper = new ModelMapper();

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            return modelMapper.map(usuario.get(), UsuarioListaResponseDto.class);
        } else {
            throw new NotFoundException("Usuario no encontrado con el id:" + id);
        }
    }
   // Método para crear un nuevo usuario
    @Transactional
    @Override
    public UsuarioResponseDto crearUsuario(UsuarioRequestDto usuarioRequestDto) throws BadRequestException {

        if (usuarioRepository.existsByEmail(usuarioRequestDto.getEmail())){
            throw new BadRequestException("Ya existe una cuenta con el correo: " + usuarioRequestDto.getEmail());
        }

        // Creamos el nuevo usuario
        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioRequestDto.getNombre());
        usuario.setApellido(usuarioRequestDto.getApellido());
        usuario.setEmail(usuarioRequestDto.getEmail());
        usuario.setNum_celular(usuarioRequestDto.getNum_celular());
        usuario.setFecha_registro(LocalDate.now());

        // Buscamos por roles
        List<Rol> listaRoles = usuarioRequestDto.getListaRolId().stream()
                .map(rolId -> rolRepository.findRolById(rolId)
                        .orElseThrow(() -> new NotFoundException("Rol no encontrado con el id: " + rolId)))
                .collect(Collectors.toList());

        usuario.setListaRoles(listaRoles);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        
        UsuarioResponseDto responseDto = new UsuarioResponseDto();
        responseDto.setId(usuarioGuardado.getId());
        responseDto.setNombre(usuarioGuardado.getNombre());
        responseDto.setApellido(usuarioGuardado.getApellido());
        responseDto.setEmail(usuarioGuardado.getEmail());
        responseDto.setNum_celular(usuarioGuardado.getNum_celular());

        // Mapeamos los roles a DTOs
        List<RolResponseDto> rolesDto = usuarioGuardado.getListaRoles().stream()
                .map(this::convertirRolToDto)
                .collect(Collectors.toList());

        responseDto.setListaRoles(rolesDto);
        responseDto.setListaCompras(new ArrayList<>());

        return responseDto;
    }

    // Método auxiliar para convertir Rol a RolResponseDto
    private RolResponseDto convertirRolToDto(Rol rol) {
        RolResponseDto rolDto = new RolResponseDto();
        rolDto.setId(rol.getId());
        rolDto.setNombre(rol.getNombre());
        return rolDto;
    }

//  Método para editar un usuario existente
    @Transactional
    @Override
    public void editarUsuario(Long id, UsuarioRequestDto usuarioRequestDto) throws NotFoundException, BadRequestException {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()->
                new NotFoundException("Usuario no encontrado con el id:"+id));

        List<Rol>listaRoles=new ArrayList<>();
        //buscamos por el id de los roles
        for (Long rolId: usuarioRequestDto.getListaRolId()){
          //encontramos el rol por su id y si no existe lanzamos una excepción
            Rol rolEncontrado=rolRepository.findRolById(rolId)
                    .orElseThrow(()->new NotFoundException("Rol no encontrado en el sistema con el id:" +rolId));

            listaRoles.add(rolEncontrado);
        }

            usuario.setListaRoles(listaRoles);
            usuario.setNombre(usuarioRequestDto.getNombre());
            usuario.setApellido(usuarioRequestDto.getApellido());
            usuario.setEmail(usuarioRequestDto.getEmail());
            usuario.setNum_celular(usuarioRequestDto.getNum_celular());

            usuarioRepository.save(usuario);

    }
//  Método para eliminar un usuario por su ID
    @Transactional
    @Override
    public void eliminarUsuario(Long id) throws NotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            throw new NotFoundException("Usuario no encontrado con el id:" + id);
        }
    }
}
