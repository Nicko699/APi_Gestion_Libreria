package org.bookstore.Repository;

import org.bookstore.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Repositorio para gestionar las operaciones de libro en la base de datos
@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
}
