package api.mercado.app.controladores;

import api.mercado.app.entidades.Cliente;
import api.mercado.app.entidades.Mercado;
import api.mercado.app.entidades.Vendedor;
import api.mercado.app.servicios.ClienteServicio;
import api.mercado.app.servicios.MercadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {

    private final ClienteServicio clienteServicio;

    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @PostMapping
    public ResponseEntity<Cliente> registrarCliente(@Valid @RequestBody Cliente cliente) {
        Cliente clienteNuevo = clienteServicio.registrarCliente(cliente);
        return new ResponseEntity<Cliente>(clienteNuevo, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteServicio.listaClientes();
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId( @PathVariable Long id) {
        Cliente cliente = clienteServicio.obtenerClientePorId(id);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }


    @GetMapping("/by-username/{name}")
    public ResponseEntity<Cliente> obtenerClientePorUsername(@PathVariable String name) {
        Cliente cliente = clienteServicio.getClientePorUsername(name);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<Cliente> actualizarCliente(@Valid @RequestBody Cliente cliente) {
        Cliente clienteUpdate = clienteServicio.modificarCliente(cliente);
        return new ResponseEntity<Cliente>(clienteUpdate, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPuesto(@PathVariable Long id) {
        clienteServicio.eliminarCliente(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
