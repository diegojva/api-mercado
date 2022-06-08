package api.mercado.app.controladores;

import api.mercado.app.entidades.Producto;
import api.mercado.app.entidades.Puesto;
import api.mercado.app.servicios.ProductoServicio;
import api.mercado.app.servicios.PuestoServicio;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/productos")
public class ProductoControlador {

    private final ProductoServicio productoServicio;

    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Producto> registrarProducto(@Valid  @RequestBody Producto producto) {
        Producto productoNew = productoServicio.registrarProducto(producto);
        return new ResponseEntity<Producto>(productoNew, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoServicio.listarProductos();
        return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
    }

    @GetMapping("/mis-productos/{id}")
    public ResponseEntity<List<Producto>> obtenerPuestosPorVendedorId( @PathVariable Long id) {
        List<Producto> productos = productoServicio.obtenerProductosPorPuesto(id);
        return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
    }

    @GetMapping("/productos-by-orden/{id}")
    public ResponseEntity<List<Producto>> obtenerProductosPorOrdenId( @PathVariable Long id) {
        List<Producto> productos = productoServicio.obtenerProductosPorOrdenId(id);
        return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoServicio.obtenerProductoPorId(id);
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Producto> modificarProducto(@RequestBody Producto producto) {
        Producto productoUpdate = productoServicio.modificarProducto(producto);
        return new ResponseEntity<Producto>(productoUpdate, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPuesto(@PathVariable Long id) {
        productoServicio.eliminarProducto(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
