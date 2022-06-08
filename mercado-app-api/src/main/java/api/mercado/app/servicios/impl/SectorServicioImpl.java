package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.Puesto;
import api.mercado.app.entidades.Sector;
import api.mercado.app.repositorios.PuestoRepositorio;
import api.mercado.app.repositorios.SectorRepositorio;
import api.mercado.app.servicios.SectorServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorServicioImpl implements SectorServicio {

    private final SectorRepositorio sectorRepositorio;

    public SectorServicioImpl(SectorRepositorio sectorRepositorio){
        this.sectorRepositorio = sectorRepositorio;
    }

    @Override
    public Sector registrarSector(Sector sector) {
        sector.setNombre(sector.getNombre().toUpperCase());
        return sectorRepositorio.save(sector);
    }

    @Override
    public Sector modificarSector(Sector sector) {
        sector.setNombre(sector.getNombre().toUpperCase());
        return sectorRepositorio.save(sector);
    }

    @Override
    public List<Sector> listarSector() {
        return sectorRepositorio.findAll();
    }

    @Override
    public Sector obtenerSectorPorId(Long idSector) {
        return sectorRepositorio.findById(idSector).orElse(new Sector());
    }

    @Override
    public void eliminarSector(Long idSector) {
        sectorRepositorio.deleteById(idSector);
    }
}
