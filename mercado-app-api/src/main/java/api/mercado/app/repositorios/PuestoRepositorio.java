package api.mercado.app.repositorios;

import api.mercado.app.entidades.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuestoRepositorio extends JpaRepository<Puesto, Long> {
     List<Puesto> findByVendedorId(Long id_vendedor);
     //Puesto findByVendedor(Long id_vendedor);
}