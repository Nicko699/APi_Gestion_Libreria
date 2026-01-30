package org.bookstore.Repository;

import org.bookstore.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// Repositorio para gestionar las operaciones de usuario en la base de datos
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

   Optional<Usuario> findByEmail(String email);

   boolean existsByEmail(String email);

}
