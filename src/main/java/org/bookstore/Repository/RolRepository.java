package org.bookstore.Repository;

import org.bookstore.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// Repositorio para gestionar las operaciones de rol en la base de datos
@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {

    Optional<Rol> findByNombre(String nombre);

    Optional<Rol>findRolById(Long id);
}
