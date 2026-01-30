package org.bookstore.Controller;

import org.bookstore.Dto.Compra.CompraRequestDto;
import org.bookstore.Dto.Compra.CompraResponseDto;
import org.bookstore.Exception.BadRequestException;
import org.bookstore.Exception.NotFoundException;
import org.bookstore.Model.Usuario;
import org.bookstore.Service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

// Controlador para gestionar las compras en la librería
@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    // Endpoint para obtener la lista de todas las compras (solo para Administrador)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtenerCompras")
    public ResponseEntity<Page<CompraResponseDto>> listaCompras(Pageable pageable) throws NotFoundException {

        Page<CompraResponseDto> listaCompras = compraService.listaCompras(pageable);

        return ResponseEntity.ok(listaCompras);
    }
// Endpoint para obtener la lista de compras del usuario autenticado (solo para Usuario)
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/obtenerCompras")
    public ResponseEntity<Page<CompraResponseDto>> listaComprasUsuario(Pageable pageable,@AuthenticationPrincipal Jwt jwt) throws NotFoundException {

        Page<CompraResponseDto> listaCompras = compraService.listaComprasUsuario(pageable,jwt);

        return ResponseEntity.ok(listaCompras);
    }

// Endpoint para obtener una compra por su ID (solo para Administrador)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtenerCompra/{id}")
    public ResponseEntity<CompraResponseDto> ObtenerCompra(@PathVariable Long id) throws NotFoundException {

        CompraResponseDto ObtenerCompra = compraService.obtenerCompra(id);

        return ResponseEntity.ok(ObtenerCompra);

    }
// Endpoint para obtener una compra por su ID (solo para Usuario)
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/obtenerCompra/{id}")
    public ResponseEntity<CompraResponseDto> ObtenerCompraUsuario(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) throws NotFoundException {

        CompraResponseDto ObtenerCompra = compraService.obtenerCompraUsuario(id,jwt);

        return ResponseEntity.ok(ObtenerCompra);

    }

// Endpoint para crear una nueva compra (para Administrador y Usuario)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/crearCompra")
    public ResponseEntity<CompraResponseDto> crearCompra(@RequestBody CompraRequestDto compraRequestDto, @AuthenticationPrincipal Jwt jwt) throws NotFoundException, BadRequestException {

        CompraResponseDto crearCompra = compraService.crearCompra(compraRequestDto, jwt);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(crearCompra.getId()).toUri();

        return ResponseEntity.created(location).body(crearCompra);

    }

}
