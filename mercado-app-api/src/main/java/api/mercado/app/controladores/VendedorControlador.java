package api.mercado.app.controladores;

import api.mercado.app.entidades.Vendedor;
import api.mercado.app.servicios.VendedorServicio;
import org.hibernate.annotations.Cascade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/vendedores")
public class VendedorControlador {

    private final VendedorServicio vendedorServicio;

    public VendedorControlador(VendedorServicio vendedorServicio) {
        this.vendedorServicio = vendedorServicio;
    }

    @PostMapping
    public ResponseEntity<Vendedor> registrarVendedor(@Valid @RequestBody Vendedor vendedor) {
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


    @GetMapping("/by-puesto/{id}")
    public ResponseEntity<Vendedor> obtenerVendedorPorPuestoId(@PathVariable Long id) {
        Vendedor vendedor = vendedorServicio.getVendedorPorPuestoId(id);
        return new ResponseEntity<Vendedor>(vendedor, HttpStatus.OK);
    }

    @GetMapping("/by-username/{name}")
    public ResponseEntity<Vendedor> obtenerVendedorPorUsername(@PathVariable String name) {
        Vendedor vendedor = vendedorServicio.getVendedorPorUsername(name);
        return new ResponseEntity<Vendedor>(vendedor, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Vendedor> actualizarVendedor(@Valid @RequestBody Vendedor vendedor) {
        Vendedor vendedorUpdate = vendedorServicio.modificarVendedor(vendedor);
        return new ResponseEntity<Vendedor>(vendedorUpdate, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVendedor(@PathVariable Long id) {
        vendedorServicio.eliminarVendedor(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
