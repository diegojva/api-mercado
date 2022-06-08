package api.mercado.app.repositorios;

import api.mercado.app.entidades.OrdenDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenDetalleRepositorio extends JpaRepository<OrdenDetalle, Long> {
    //List<OrdenDetalle> findByProductoId(Long idProductoId);
    List<OrdenDetalle> findByProductoId(Long id);
}
