package org.bookstore.Repository;

import org.bookstore.Model.DetallesCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Repositorio para gestionar las operaciones de detalles de compra en la base de datos
@Repository
public interface DetallesCompraRepository extends JpaRepository<DetallesCompra,Long> {

}
