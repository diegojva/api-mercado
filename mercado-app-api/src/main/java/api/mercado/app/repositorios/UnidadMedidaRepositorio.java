package api.mercado.app.repositorios;

import api.mercado.app.entidades.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaRepositorio extends JpaRepository<UnidadMedida, Long> {
}
