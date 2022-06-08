package api.mercado.app.controladores;

import api.mercado.app.entidades.Mercado;
import api.mercado.app.entidades.Puesto;
import api.mercado.app.servicios.MercadoServicio;
import api.mercado.app.servicios.PuestoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mercados")
public class MercadoControlador {

    private final MercadoServicio mercadoServicio;

    public MercadoControlador(MercadoServicio mercadoServicio) {
        this.mercadoServicio = mercadoServicio;
    }

    @PreAuthorize("hasRole('MASTER')")
    @PostMapping
    public ResponseEntity<Mercado> registrarMercado(@RequestBody Mercado mercado) {
        Mercado mercadoNuevo = mercadoServicio.registrarMercado(mercado);
        return new ResponseEntity<Mercado>(mercadoNuevo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Mercado>> listarMercados() {
        List<Mercado> mercados = mercadoServicio.listaMercados();
        return new ResponseEntity<List<Mercado>>(mercados, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mercado> obtenerMercadoPorId( @PathVariable Long id) {
        Mercado mercado = mercadoServicio.obtenerMercadoPorId(id);
        return new ResponseEntity<Mercado>(mercado, HttpStatus.OK);
    }

    @GetMapping("/by-puesto/{id}")
    public ResponseEntity<Mercado> obtenerMercadoPorIdPuesto( @PathVariable Long id) {
        Mercado mercado = mercadoServicio.obtenerMercadoPorPuestoId(id);
        return new ResponseEntity<Mercado>(mercado, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MASTER')")
    @PutMapping
    public ResponseEntity<Mercado> actualizarMercado(@RequestBody Mercado mercado) {
        Mercado mercadoUpdate = mercadoServicio.modificarMercado(mercado);
        return new ResponseEntity<Mercado>(mercadoUpdate, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('MASTER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPuesto(@PathVariable Long id) {
        mercadoServicio.eliminarMercado(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
