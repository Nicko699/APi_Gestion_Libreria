package org.bookstore.Security;


import org.bookstore.Exception.NotFoundException;
import org.bookstore.Model.Rol;
import org.bookstore.Model.Usuario;
import org.bookstore.Repository.RolRepository;
import org.bookstore.Repository.UsuarioRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Servicio para convertir un JWT de google en un token de autenticación de Spring Security
@Service
public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken>  {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public JwtAuthenticationConverter(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    //metodo para convertir el JWT de google en un token de autenticación
    @Transactional
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) throws NotFoundException {
// Obtenemos el email del JWT
        String email = jwt.getClaimAsString("email");

// Buscamos el usuario en la base de datos por su email, si no existe lo creamos
        Usuario usuarioEncontrado = usuarioRepository.findByEmail(email).orElseGet(()->{

            Usuario usuario=new Usuario();
            // Asignamos los datos del usuario desde el JWT de google
            usuario.setEmail(jwt.getClaimAsString("email"));
            usuario.setNombre(jwt.getClaimAsString("given_name"));
            usuario.setApellido(jwt.getClaimAsString("family_name"));
            usuario.setNum_celular("Sin numero");

            List<Rol>listaRoles=new ArrayList<>();
            
// Asignamos el rol de USER por defecto
            Rol rol=rolRepository.findByNombre("ROLE_USER").orElseThrow(()->
                    new NotFoundException("Rol no encontrado en el sistema"));

            listaRoles.add(rol);

            usuario.setListaRoles(listaRoles);
            usuario.setFecha_registro(LocalDate.now());

          return usuarioRepository.save(usuario);
        });
        
            Collection<GrantedAuthority> authorities = new ArrayList<>();

// Asignamos los roles del usuario como autoridades
            for (Rol rol : usuarioEncontrado.getListaRoles()) {

                GrantedAuthority authority = new SimpleGrantedAuthority(rol.getNombre());

                authorities.add(authority);

            }
// Retornamos un token de autenticación con el JWT y las autoridades del usuario
            return new JwtAuthenticationToken(jwt, authorities, usuarioEncontrado.getEmail());

    }}
