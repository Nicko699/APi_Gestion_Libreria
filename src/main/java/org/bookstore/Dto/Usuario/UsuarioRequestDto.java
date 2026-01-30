package org.bookstore.Dto.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
// DTO para representar una solicitud de usuario/ crear y editar
public class UsuarioRequestDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe de tener un formato correcto")
    private String email;
    @NotBlank(message = "El numero de celular no puede estar vacío")
    @Size(min = 10, max = 10, message = "El número de celular debe de tener 10 digitos")
    private String num_celular;
    @NotNull(message = "El rol no puede ser null")
    private List<Long> listaRolId;


    public UsuarioRequestDto() {
    }

    public UsuarioRequestDto(String nombre, String apellido, String email, String num_celular, List<Long> listaRolId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.num_celular = num_celular;
        this.listaRolId = listaRolId;
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

    public List<Long> getListaRolId() {
        return listaRolId;
    }

    public void setListaRolId(List<Long> listaRolId) {
        this.listaRolId = listaRolId;
    }
}
