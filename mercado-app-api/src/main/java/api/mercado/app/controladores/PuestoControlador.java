package api.mercado.app.controladores;

import api.mercado.app.entidades.Puesto;
import api.mercado.app.servicios.PuestoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/puestos")
public class PuestoControlador {

    private final PuestoServicio puestoServicio;

    public PuestoControlador(PuestoServicio puestoServicio) {
        this.puestoServicio = puestoServicio;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Puesto> registrarPuesto(@RequestBody Puesto puesto) {
        Puesto puestoNew = puestoServicio.registrarPuesto(puesto);
        return new ResponseEntity<Puesto>(puestoNew, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Puesto>> listarPuestos() {
        List<Puesto> puestos = puestoServicio.listarPuestos();
        return new ResponseEntity<List<Puesto>>(puestos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Puesto> obtenerPuestoPorId(@PathVariable Long id) {
        Puesto puesto = puestoServicio.obtenerPuestoPorId(id);
        return new ResponseEntity<Puesto>(puesto, HttpStatus.OK);
    }

    @GetMapping("/mis-puestos/{id}")
    public ResponseEntity<List<Puesto>> obtenerPuestosPorVendedorId( @PathVariable Long id) {
        List<Puesto> puesto = puestoServicio.obtenerPuestosPorIdVendedor(id);
        return new ResponseEntity<List<Puesto>>(puesto, HttpStatus.OK);
    }

    @GetMapping("/mis-puestos/by-mercado/{id}")
    public ResponseEntity<List<Puesto>> obtenerPuestosPorMercadoId( @PathVariable Long id) {
        List<Puesto> puesto = puestoServicio.obtenerPuestosPorIdMercado(id);
        return new ResponseEntity<List<Puesto>>(puesto, HttpStatus.OK);
    }

    @GetMapping("/mis-puestos/by-estado/{estado}")
    public ResponseEntity<List<Puesto>> obtenerPuestosPorEstado( @PathVariable String estado) {
        List<Puesto> puesto = puestoServicio.obtenerPuestosPorEstado(estado);
        return new ResponseEntity<List<Puesto>>(puesto, HttpStatus.OK);
    }

    @GetMapping("/mis-puestos/by-mercadoId/{id}/and/by-estado/{estado}")
    public ResponseEntity<List<Puesto>> obtenerPuestoPorMercadoIdYEstado( @PathVariable Long id,@PathVariable  String estado) {
        List<Puesto> puesto = puestoServicio.obtenerPuestosPorMercadoIdYEstado(id, estado);
        return new ResponseEntity<List<Puesto>>(puesto, HttpStatus.OK);
    }

    @GetMapping("/mis-puestos/by-sector/{id}")
    public ResponseEntity<List<Puesto>> obtenerPuestosPorSectorId( @PathVariable Long id) {
        List<Puesto> puesto = puestoServicio.obtenerPuestosPorIdsector(id);
        return new ResponseEntity<List<Puesto>>(puesto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Puesto> actualizarPuesto(@RequestBody Puesto puesto) {
        Puesto puestoUpdate = puestoServicio.modificarPuesto(puesto);
        return new ResponseEntity<Puesto>(puestoUpdate, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPuesto(@PathVariable Long id) {
        puestoServicio.eliminarPuesto(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
