package api.mercado.app.repositorios;

import api.mercado.app.entidades.Orden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepositorio extends JpaRepository<Orden, Long> {
    //@Query(value="SELECT * FROM fn_reportOrder()",nativeQuery=true)
    //List<Object[]> reportOrder();
    List<Orden> findByDespachadorIdAndEstado(Long id, String estado);
    List<Orden> findByDespachadorIdAndEstadoNotAndEstadoNot(Long id, String estadoUno, String estadoDos);
    List<Orden> findByDespachadorIdAndEstadoNotAndEstadoNotAndEstadoNot(Long id, String estadoUno, String estadoDos, String tres);
    List<Orden> findByClienteId(Long idCliente);
    List<Orden> findByDespachadorIdAndEstadoNot(Long id, String estado);
    List<Orden> findByDespachadorId(Long idDespachador);
}
