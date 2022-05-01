package api.mercado.app.controladores;

import api.mercado.app.entidades.Vendedor;
import api.mercado.app.servicios.VendedorServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/vendedores")
public class VendedorControlador {

    private final VendedorServicio vendedorServicio;

    public VendedorControlador(VendedorServicio vendedorServicio) {
        this.vendedorServicio = vendedorServicio;
    }

    @PostMapping
    public ResponseEntity<Vendedor> registrarVendedor(@RequestBody Vendedor vendedor) {
        Vendedor vendedorNew = vendedorServicio.registrarVendedor(vendedor);
        return new ResponseEntity<Vendedor>(vendedorNew, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vendedor>> listarVendedores() {
        List<Vendedor> vendedores = vendedorServicio.listarVendedores();
        return new ResponseEntity<List<Vendedor>>(vendedores, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> obtenerVendedorPorId(@PathVariable Long id) {
        Vendedor vendedor = vendedorServicio.obtenerVendedorPorId(id);
        return new ResponseEntity<Vendedor>(vendedor, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Vendedor> actualizarVendedor(@RequestBody Vendedor vendedor) {
        Vendedor vendedorUpdate = vendedorServicio.modificarVendedor(vendedor);
        return new ResponseEntity<Vendedor>(vendedorUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVendedor(@PathVariable Long id) {
        vendedorServicio.eliminarVendedor(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
