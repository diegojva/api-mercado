package api.mercado.app.repositorios;

import api.mercado.app.entidades.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuestoRepositorio extends JpaRepository<Puesto, Long> {
     List<Puesto> findByVendedorId(Long id_vendedor);
     List<Puesto> findByMercadoId(Long id_mercado);
     List<Puesto> findBySectorId(Long id_sector);
     //Puesto findByVendedor(Long id_vendedor);
     List<Puesto> findByMercadoIdAndEstado(Long id, String estado);
     List<Puesto> findByEstado(String estado);
}