package org.bookstore.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
// Entidad que representa un usuario en la tienda de libros
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String num_celular;
    private LocalDate fecha_registro;
// Relación OneToMany con Compra
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Compra> listaCompras;
/// Relación ManyToMany con Rol
    @JoinTable(name = "usuario_rol", joinColumns=@JoinColumn(name = "usuario_id"),
    inverseJoinColumns =@JoinColumn(name = "rol_id") )
   @ManyToMany
    private List<Rol>listaRoles;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellido, String email, String num_celular, LocalDate fecha_registro, List<Compra> listaCompras, List<Rol> listaRoles) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.num_celular = num_celular;
        this.fecha_registro = fecha_registro;
        this.listaCompras = listaCompras;
        this.listaRoles = listaRoles;
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

    public LocalDate getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public List<Compra> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }
}
