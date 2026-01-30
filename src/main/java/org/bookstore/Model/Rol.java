package org.bookstore.Model;

import jakarta.persistence.*;

import java.util.List;
// Entidad que representa un rol de usuario
@Entity
public class Rol {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    // Relación ManyToMany con Usuario
    @ManyToMany(mappedBy = "listaRoles")
    private List<Usuario>listaUsuarios;

    public Rol() {
    }

    public Rol(Long id, String nombre, List<Usuario> listaUsuarios) {
        this.id = id;
        this.nombre = nombre;
        this.listaUsuarios = listaUsuarios;
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

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
