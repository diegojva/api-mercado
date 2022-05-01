package api.mercado.app.controladores;

import api.mercado.app.entidades.Puesto;
import api.mercado.app.servicios.PuestoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

    @PutMapping
    public ResponseEntity<Puesto> actualizarPuesto(@RequestBody Puesto puesto) {
        Puesto puestoUpdate = puestoServicio.modificarPuesto(puesto);
        return new ResponseEntity<Puesto>(puestoUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPuesto(@PathVariable Long id) {
        puestoServicio.eliminarPuesto(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
