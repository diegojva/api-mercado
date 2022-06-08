package api.mercado.app.controladores;

import api.mercado.app.dto.AtencionPedidoDTO;
import api.mercado.app.dto.DetallePedidoDTO;
import api.mercado.app.dto.PedidoClienteDTO;
import api.mercado.app.entidades.Orden;
import api.mercado.app.entidades.Puesto;
import api.mercado.app.repositorios.OrdenDetalleRepositorio;
import api.mercado.app.servicios.OrdenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
public class OrdenControlador {

    @Autowired
    private OrdenServicio ordenServicio;

    @PostMapping
    public ResponseEntity<Orden> createOrden(@RequestBody Orden order){
        Orden newOrder=ordenServicio.crearOrden(order);
        return new ResponseEntity<Orden>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Orden>> obtenerOrdenes() {
        List<Orden> ordenes=ordenServicio.listarOrdenes();
        return new ResponseEntity<List<Orden>>(ordenes,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerOrdenPorId(@PathVariable Long id) {
        Orden orden = ordenServicio.getOrdenPorId(id);
        return new ResponseEntity<Orden>(orden, HttpStatus.OK);
    }
    @GetMapping("/sin-anteder/{id}/status/{estado}")
    public ResponseEntity<List<Orden>> obtenerOrdenPorEstado(@PathVariable Long id, @PathVariable String estado) {
        List<Orden> ordenes = ordenServicio.listarOrdenesPorEstadoAndDespachadorId(id, estado);
        return new ResponseEntity<List<Orden>>(ordenes, HttpStatus.OK);
    }

    @GetMapping("/sin-anteder/{id}/status/{estadoUno}/and/{estadoDos}")
    public ResponseEntity<List<Orden>> obtenerPorDespachadorIdAndEstadoNotAndEstadoNot(@PathVariable Long id, @PathVariable String estadoUno, @PathVariable String estadoDos) {
        List<Orden> ordenes = ordenServicio.listarOrdenesPorDespachadorIdAndEstadoNotAndEstadoNot(id, estadoUno, estadoDos);
        return new ResponseEntity<List<Orden>>(ordenes, HttpStatus.OK);
    }

    @GetMapping("/sin-anteder/{id}/status/{estadoUno}/and/{estadoDos}/and/{estadoTres}")
    public ResponseEntity<List<Orden>> obtenerPorDespachadorIdAndEstadoNotAndEstadoNotEstadoNot(@PathVariable Long id, @PathVariable String estadoUno, @PathVariable String estadoDos,@PathVariable String estadoTres) {
        List<Orden> ordenes = ordenServicio.listarOrdenesPorDespachadorIdAndEstadoNotAndEstadoNotEstadoNot(id, estadoUno, estadoDos,estadoTres);
        return new ResponseEntity<List<Orden>>(ordenes, HttpStatus.OK);
    }

    @GetMapping("/pedidos/by-puesto/{id}")
    public ResponseEntity<List<AtencionPedidoDTO>> obtenerOrdendesPorPuestoId(@PathVariable Long id) {
        List<AtencionPedidoDTO> atencionPedidoDTO = ordenServicio.atencionesPedidosDTO(id);
        return new ResponseEntity<List<AtencionPedidoDTO>>(atencionPedidoDTO, HttpStatus.OK);
    }

    @GetMapping("/pedidos/by-id/{id}/no-contains/{estado}")
    public ResponseEntity<List<Orden>> obtenerOrdendesPorEstadoExceptoSinAtender(@PathVariable Long id, @PathVariable String estado) {
        List<Orden> ordens = ordenServicio.listarOrdenesPorEstadoExceptoSinAtender(id, estado);
        return new ResponseEntity<List<Orden>>(ordens, HttpStatus.OK);
    }

    @GetMapping("/pedidos/by-despachador/{id}")
    public ResponseEntity<List<Orden>> obtenerOrdenesPorDepachadorIr(@PathVariable Long id) {
        List<Orden> ordenes = ordenServicio.listarOrdenesPorIdDespachador(id);
        return new ResponseEntity<List<Orden>>(ordenes, HttpStatus.OK);
    }

    @GetMapping("/pedidos-general/by-idCliente/{id}")
    public ResponseEntity<List<PedidoClienteDTO>> obtenerPedidosDTOPorCliente(@PathVariable Long id) {
        List<PedidoClienteDTO> pedidosClienteDTO = ordenServicio.atencionPedidosCliente(id);
        return new ResponseEntity<List<PedidoClienteDTO>>(pedidosClienteDTO, HttpStatus.OK);
    }

    @GetMapping("/pedidos-detalles/by-cliente/{id}")
    public ResponseEntity<List<DetallePedidoDTO>> obtenerDetallesOrdenesPorCliente(@PathVariable Long id) {
        List<DetallePedidoDTO> detallePedidoDTOS = ordenServicio.detallePedidoDTO(id);
        return new ResponseEntity<List<DetallePedidoDTO>>(detallePedidoDTOS, HttpStatus.OK);
    }

    @GetMapping("/by-cliente/{id}")
    public ResponseEntity<List<Orden>> obtenerOrdenesPorClienteId(@PathVariable Long id) {
        List<Orden> ordenes = ordenServicio.listarOrdenesPorIdCliente(id);
        return new ResponseEntity<List<Orden>>(ordenes, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Orden> actualizarOrden(@RequestBody Orden orden) {
        Orden ordenUpdate = ordenServicio.actualizarOrden(orden);
        return new ResponseEntity<Orden>(ordenUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPuesto(@PathVariable Long id) {
        ordenServicio.eliminarOrden(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
