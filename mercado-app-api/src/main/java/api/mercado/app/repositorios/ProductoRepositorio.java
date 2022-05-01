package api.mercado.app.repositorios;

import api.mercado.app.entidades.Producto;
import api.mercado.app.entidades.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    List<Producto> findByPuestoId(long id_puesto);
}
