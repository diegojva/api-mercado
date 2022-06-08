package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.OrdenDetalle;
import api.mercado.app.entidades.Producto;
import api.mercado.app.repositorios.OrdenDetalleRepositorio;
import api.mercado.app.repositorios.OrdenRepositorio;
import api.mercado.app.repositorios.ProductoRepositorio;

import api.mercado.app.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServicioImpl implements ProductoServicio {


    private final ProductoRepositorio productoRepositorio;
    private final OrdenDetalleRepositorio ordenDetalleRepositorio;
    @Autowired
    private  OrdenRepositorio ordenRepositorio;

    public ProductoServicioImpl(ProductoRepositorio productoRepositorio,OrdenDetalleRepositorio ordenDetalleRepositorio){
        this.productoRepositorio = productoRepositorio;
        this.ordenDetalleRepositorio = ordenDetalleRepositorio;
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
    public Producto obtenerProductoPorId(Long idProducto) {
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

    @Override
    public List<Producto> obtenerProductosPorOrdenId(Long idOrden) {
        List<Producto> productos = new ArrayList<>();
        for (OrdenDetalle item:ordenRepositorio.findById(idOrden).get().getItems()){
            productos.add(item.getProducto());
        }
        return productos;
    }
}
