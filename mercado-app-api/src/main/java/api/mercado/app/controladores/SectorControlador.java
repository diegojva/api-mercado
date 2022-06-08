package api.mercado.app.controladores;

import api.mercado.app.entidades.Puesto;
import api.mercado.app.entidades.Sector;
import api.mercado.app.servicios.PuestoServicio;
import api.mercado.app.servicios.SectorServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sectores")
public class SectorControlador {

    private final SectorServicio sectorServicio;

    public SectorControlador(SectorServicio sectorServicio) {
        this.sectorServicio = sectorServicio;
    }

    @PreAuthorize("hasRole('MASTER')")
    @PostMapping
    public ResponseEntity<Sector> registrarSector(@RequestBody Sector sector) {
        Sector sectorNew = sectorServicio.registrarSector(sector);
        return new ResponseEntity<Sector>(sectorNew, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Sector>> listarSectores() {
        List<Sector> sectores = sectorServicio.listarSector();
        return new ResponseEntity<List<Sector>>(sectores, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Sector> obtenerSectorPorId(@PathVariable Long id) {
        Sector sector = sectorServicio.obtenerSectorPorId(id);
        return new ResponseEntity<Sector>(sector, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MASTER')")
    @PutMapping
    public ResponseEntity<Sector> actualizarSector(@RequestBody Sector sector) {
        Sector sectorUpdate = sectorServicio.modificarSector(sector);
        return new ResponseEntity<Sector>(sectorUpdate, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('MASTER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSector(@PathVariable Long id) {
        sectorServicio.eliminarSector(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
