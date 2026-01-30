package org.bookstore.Model;

import jakarta.persistence.*;
// Entidad que representa los detalles de una compra
@Entity
public class DetallesCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Relación ManyToOne con Compra
    @ManyToOne
    private Compra compra;
    // Relación ManyToOne con Libro
    @ManyToOne
    private Libro libro;
    private Long cantidad;
    private Double SubTotal;
    private Double precioUnitario;

    public DetallesCompra() {
    }

    public DetallesCompra(Long id, Compra compra, Libro libro, Long cantidad, Double subTotal, Double precioUnitario) {
        this.id = id;
        this.compra = compra;
        this.libro = libro;
        this.cantidad = cantidad;
        SubTotal = subTotal;
        this.precioUnitario = precioUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(Double subTotal) {
        SubTotal = subTotal;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
