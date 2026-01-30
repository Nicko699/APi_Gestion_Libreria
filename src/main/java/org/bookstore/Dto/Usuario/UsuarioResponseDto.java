package org.bookstore.Dto.Usuario;
import org.bookstore.Dto.Compra.CompraResponseDto;
import org.bookstore.Dto.Rol.RolResponseDto;
import org.bookstore.Model.Rol;

import java.util.List;
// DTO para representar una respuesta de usuario crear
public class UsuarioResponseDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String num_celular;
    private List<RolResponseDto>listaRoles;
    private List<CompraResponseDto> listaCompras;

    public UsuarioResponseDto() {
    }

    public UsuarioResponseDto(Long id, String nombre, String apellido, String email, String num_celular, List<RolResponseDto> listaRoles, List<CompraResponseDto> listaCompras) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.num_celular = num_celular;
        this.listaRoles = listaRoles;
        this.listaCompras = listaCompras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNum_celular() {
        return num_celular;
    }

    public void setNum_celular(String num_celular) {
        this.num_celular = num_celular;
    }

    public List<RolResponseDto> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<RolResponseDto> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<CompraResponseDto> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<CompraResponseDto> listaCompras) {
        this.listaCompras = listaCompras;
    }
}
