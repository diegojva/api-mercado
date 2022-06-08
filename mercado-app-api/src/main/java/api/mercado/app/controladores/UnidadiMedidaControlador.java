package api.mercado.app.controladores;

import api.mercado.app.entidades.Sector;
import api.mercado.app.entidades.UnidadMedida;
import api.mercado.app.servicios.SectorServicio;
import api.mercado.app.servicios.UnidadMedidaServicio;
import api.mercado.app.servicios.impl.UnidadMedidaImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidades_medidas")
public class UnidadiMedidaControlador {

    private final UnidadMedidaServicio unidadMedidaServicio;

    public UnidadiMedidaControlador(UnidadMedidaServicio unidadMedidaServicio) {
        this.unidadMedidaServicio = unidadMedidaServicio;
    }

    @PreAuthorize("hasRole('MASTER')")
    @PostMapping
    public ResponseEntity<UnidadMedida> registrarUnidadMedida(@Validated  @RequestBody UnidadMedida unidadMedida) {
        UnidadMedida unidadMedidaNew = unidadMedidaServicio.registrarUnidadMedida(unidadMedida);
        return new ResponseEntity<UnidadMedida>(unidadMedidaNew, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UnidadMedida>> listrarUnidadesMedidas() {
        List<UnidadMedida> unidades_medidas = unidadMedidaServicio.listarUnidadesMedidas();
        return new ResponseEntity<List<UnidadMedida>>(unidades_medidas, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedida> obtenerUnidadMedidaPorId(@PathVariable Long id) {
        UnidadMedida unidadMedida = unidadMedidaServicio.obtenerUnidadMedidaPorId(id);
        return new ResponseEntity<UnidadMedida>(unidadMedida, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MASTER')")
    @PutMapping
    public ResponseEntity<UnidadMedida> actualizarUnidadMedida(@RequestBody UnidadMedida unidadMedida) {
        UnidadMedida unidadMedidaUpdate = unidadMedidaServicio.modificarUnidadMedida(unidadMedida);
        return new ResponseEntity<UnidadMedida>(unidadMedidaUpdate, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('MASTER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUnidadMedida(@PathVariable Long id) {
        unidadMedidaServicio.eliminarUnidadMedida(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
