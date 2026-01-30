package org.bookstore.Dto.Compra;

import org.bookstore.Dto.Usuario.UsuarioCompraResponseDto;

import java.time.LocalDate;
import java.util.List;
// DTO para representar una respuesta de compra
public class CompraResponseDto {

    private Long id;
    private LocalDate fecha_compra;
    private Long cantidad;
    private Double total_pagar;
    private String estado_compra;
    private UsuarioCompraResponseDto usuario;
    private List<DetalleCompraResponseDto> listaDetallesCompra;

    public CompraResponseDto() {
    }

    public CompraResponseDto(Long id, LocalDate fecha_compra, Long cantidad, Double total_pagar, String estado_compra, UsuarioCompraResponseDto usuario, List<DetalleCompraResponseDto> listaDetallesCompra) {
        this.id = id;
        this.fecha_compra = fecha_compra;
        this.cantidad = cantidad;
        this.total_pagar = total_pagar;
        this.estado_compra = estado_compra;
        this.usuario = usuario;
        this.listaDetallesCompra = listaDetallesCompra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(LocalDate fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal_pagar() {
        return total_pagar;
    }

    public void setTotal_pagar(Double total_pagar) {
        this.total_pagar = total_pagar;
    }

    public String getEstado_compra() {
        return estado_compra;
    }

    public void setEstado_compra(String estado_compra) {
        this.estado_compra = estado_compra;
    }

    public UsuarioCompraResponseDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioCompraResponseDto usuario) {
        this.usuario = usuario;
    }

    public List<DetalleCompraResponseDto> getListaDetallesCompra() {
        return listaDetallesCompra;
    }

    public void setListaDetallesCompra(List<DetalleCompraResponseDto> listaDetallesCompra) {
        this.listaDetallesCompra = listaDetallesCompra;
    }
}
