package api.mercado.app.repositorios;

import api.mercado.app.entidades.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendedorRepositorio extends JpaRepository<Vendedor, Long> {

     Optional<Vendedor> findByUsername(String username);
     boolean existsByUsername(String username);
}

