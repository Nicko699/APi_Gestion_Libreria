package org.bookstore.Repository;

import org.bookstore.Model.Compra;
import org.bookstore.Model.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// Repositorio para gestionar las operaciones de compra en la base de datos
@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {

    Page<Compra>findAllByUsuario(Usuario usuario, Pageable pageable);

   Optional<Compra>findByIdAndUsuario(Long id,Usuario usuario);
}
