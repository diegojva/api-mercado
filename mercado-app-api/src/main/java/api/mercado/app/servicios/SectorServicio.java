package api.mercado.app.servicios;

import api.mercado.app.entidades.Sector;

import java.util.List;

public interface SectorServicio {

    Sector registrarSector(Sector sector);
    Sector modificarSector(Sector sector);
    List<Sector> listarSector();
    Sector obtenerSectorPorId(Long idSector);
    void eliminarSector(Long idSector);

}
