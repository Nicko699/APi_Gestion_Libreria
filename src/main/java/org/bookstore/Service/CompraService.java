package org.bookstore.Service;

import org.bookstore.Dto.Compra.CompraRequestDto;
import org.bookstore.Dto.Compra.CompraResponseDto;
import org.bookstore.Exception.BadRequestException;
import org.bookstore.Exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.jwt.Jwt;
// Servicio para gestionar las compras en la librería
public interface CompraService {

    public Page<CompraResponseDto> listaCompras(Pageable pageable) throws NotFoundException;

    public Page<CompraResponseDto> listaComprasUsuario(Pageable pageable, Jwt jwt) throws NotFoundException;

    public CompraResponseDto obtenerCompra(Long id) throws NotFoundException;

    public CompraResponseDto obtenerCompraUsuario(Long id, Jwt jwt) throws NotFoundException;

    public CompraResponseDto crearCompra(CompraRequestDto compraRequestDto, Jwt jwt) throws NotFoundException, BadRequestException;

//La compra no se puede editar ni eliminar


}
