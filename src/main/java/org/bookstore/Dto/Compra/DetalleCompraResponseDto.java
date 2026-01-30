package org.bookstore.Dto.Compra;


import org.bookstore.Dto.Libro.LibroDetalleCompraResponseDTO;
// DTO para representar una respuesta de detalle de compra
public class DetalleCompraResponseDto {

    private Long id;
    private LibroDetalleCompraResponseDTO libro;
    private Long cantidad;
    private Double SubTotal;
    private Double precioUnitario;

    public DetalleCompraResponseDto() {
    }

    public DetalleCompraResponseDto(Long id, LibroDetalleCompraResponseDTO libro, Long cantidad, Double subTotal, Double precioUnitario) {
        this.id = id;
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

    public LibroDetalleCompraResponseDTO getLibro() {
        return libro;
    }

    public void setLibro(LibroDetalleCompraResponseDTO libro) {
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
