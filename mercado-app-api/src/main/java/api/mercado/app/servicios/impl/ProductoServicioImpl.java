package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.Producto;
import api.mercado.app.entidades.Puesto;
import api.mercado.app.repositorios.ProductoRepositorio;
import api.mercado.app.repositorios.PuestoRepositorio;
import api.mercado.app.servicios.ProductoServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepositorio productoRepositorio;

    public ProductoServicioImpl(ProductoRepositorio productoRepositorio){
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    public Producto registrarProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    @Override
    public Producto modificarProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepositorio.findAll();
    }

    @Override
    public Producto obtenerPuestoPorId(Long idProducto) {
        return productoRepositorio.findById(idProducto).orElse(new Producto());
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        productoRepositorio.deleteById(idProducto);
    }

    @Override
    public List<Producto> obtenerProductosPorPuesto(Long idPuesto) {
        List<Producto> productos = productoRepositorio.findByPuestoId(idPuesto);
        return productos;
    }
}
