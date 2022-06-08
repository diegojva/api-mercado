package api.mercado.app.controladores;

import api.mercado.app.entidades.Cliente;
import api.mercado.app.entidades.Despachador;
import api.mercado.app.entidades.Mercado;
import api.mercado.app.entidades.Puesto;
import api.mercado.app.servicios.DespachadorServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/despachadores")
public class DespachadorControlador {


    private final DespachadorServicio despachadorServicio;

    public DespachadorControlador(DespachadorServicio despachadorServicio) {
        this.despachadorServicio = despachadorServicio;
    }

    @PostMapping
    public ResponseEntity<Despachador> registrarDespachador(@Valid @RequestBody Despachador despachador) {
        Despachador despachadorNew = despachadorServicio.createDespachador(despachador);
        return new ResponseEntity<Despachador>(despachadorNew, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Despachador> actualizarDespachador(@Valid @RequestBody Despachador despachador) {
        Despachador despachadorUpdate = despachadorServicio.actualizarDespachador(despachador);
        return new ResponseEntity<Despachador>(despachadorUpdate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Despachador>> listarMercados() {
        List<Despachador> despachadores = despachadorServicio.listarDespachadores();
        return new ResponseEntity<List<Despachador>>(despachadores, HttpStatus.OK);
    }

    @GetMapping("/list/by-status/{estadoUno}/or/{estadoDos}")
    public ResponseEntity<List<Despachador>> obtenerDespachadoresPorEstados(@PathVariable String estadoUno, @PathVariable  String estadoDos) {
        List<Despachador> despachadores = despachadorServicio.obtenerDespachadorPorEstados(estadoUno, estadoDos);
        return new ResponseEntity<List<Despachador>>(despachadores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despachador> obtenerDespachadorPorId(@PathVariable Long id) {
        Despachador despachador = despachadorServicio.obtenerDespachadorPorId(id);
        return new ResponseEntity<Despachador>(despachador, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPuesto(@PathVariable Long id) {
        despachadorServicio.eliminarDespachador(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
