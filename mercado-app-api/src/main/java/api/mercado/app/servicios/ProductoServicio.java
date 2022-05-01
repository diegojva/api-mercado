package api.mercado.app.servicios;

import api.mercado.app.entidades.Producto;
import api.mercado.app.entidades.Puesto;

import java.util.List;

public interface ProductoServicio {

    Producto registrarProducto(Producto producto);
    Producto modificarProducto(Producto producto);
    List<Producto> listarProductos();
    Producto obtenerPuestoPorId(Long idProducto);
    void eliminarProducto(Long idProducto);
    public List<Producto> obtenerProductosPorPuesto(Long idPuesto);

}
