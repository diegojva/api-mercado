package api.mercado.app.repositorios;

import api.mercado.app.entidades.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepositorio extends JpaRepository<Sector, Long> {
}
