package api.mercado.app.repositorios;

import api.mercado.app.entidades.Cliente;
import api.mercado.app.entidades.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    boolean existsByEmail(String email);
    Optional<Cliente> findByEmail(String email);
}
