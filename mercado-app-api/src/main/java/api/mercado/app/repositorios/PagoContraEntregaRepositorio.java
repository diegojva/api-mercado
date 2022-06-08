package api.mercado.app.repositorios;

import api.mercado.app.entidades.PagoContraEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoContraEntregaRepositorio extends JpaRepository<PagoContraEntrega, Long> {
}
