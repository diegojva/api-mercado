package api.mercado.app.repositorios;

import api.mercado.app.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepositorio extends JpaRepository<Administrador,Long> {
    boolean existsByUsername(String username);
    Optional<Administrador> findByUsername(String username);
}
