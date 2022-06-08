package api.mercado.app.repositorios;

import api.mercado.app.entidades.Mercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MercadoRepositorio extends JpaRepository<Mercado, Long> {

}
