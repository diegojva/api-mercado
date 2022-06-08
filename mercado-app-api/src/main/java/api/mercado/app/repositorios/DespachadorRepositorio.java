package api.mercado.app.repositorios;

import api.mercado.app.entidades.Despachador;
import api.mercado.app.entidades.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DespachadorRepositorio extends JpaRepository<Despachador,Long> {

    Optional<Despachador> findByUsername(String username);

    List<Despachador> findByEstadoOrEstado(String estadoUno, String estadoDos);
    boolean existsByUsername(String username);
}
