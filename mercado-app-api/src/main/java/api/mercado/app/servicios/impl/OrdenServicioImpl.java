package api.mercado.app.servicios.impl;

import api.mercado.app.dto.AtencionPedidoDTO;
import api.mercado.app.dto.DetallePedidoDTO;
import api.mercado.app.dto.PedidoClienteDTO;
import api.mercado.app.entidades.*;
import api.mercado.app.excepciones.GeneralServiceException;
import api.mercado.app.excepciones.IncorrectResourceRequestException;
import api.mercado.app.excepciones.ResourceNotFoundException;
import api.mercado.app.repositorios.OrdenDetalleRepositorio;
import api.mercado.app.repositorios.OrdenRepositorio;
import api.mercado.app.repositorios.PagoContraEntregaRepositorio;
import api.mercado.app.repositorios.ProductoRepositorio;
import api.mercado.app.servicios.OrdenServicio;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdenServicioImpl implements OrdenServicio {

    @Autowired
    private OrdenRepositorio ordenRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    public Orden crearOrden(Orden orden) {
        double total=0;
        for (OrdenDetalle item:orden.getItems()){
            Producto producto=productoRepositorio.findById(item.getProducto().getId())
                    .orElseThrow(()->new ResourceNotFoundException("No existe el producto "+item.getProducto().getId()));

            item.setPrice(producto.getPrecio());
            item.setTotal(producto.getPrecio()*item.getQuantity());
            if(producto.getStock()>=item.getQuantity()){producto.setStock(producto.getStock()-item.getQuantity());}
            else { throw new IllegalArgumentException("No hay stock suficiente.");}
            total+=item.getTotal();
        }
        orden.setTotal(total);
        orden.getItems().forEach(line->line.setOrder(orden));
        orden.setFecha_creacion(LocalDateTime.now());
        orden.setEstado("SIN ATENDER");
        return ordenRepositorio.save(orden);
    }

    @Override
    public List<Orden> listarOrdenes() {
        return ordenRepositorio.findAll();
    }

    @Override
    public List<Orden> listarOrdenesPorIdCliente(Long id) {
        return ordenRepositorio.findByClienteId(id);
    }

    @Override
    public Orden getOrdenPorId(Long id) {
        return ordenRepositorio.findById(id).orElse(new Orden());
    }

    @Override
    public Orden actualizarOrden(Orden orden) {
        double total=0;

        for (OrdenDetalle item:orden.getItems()){
            Producto producto=productoRepositorio.findById(item.getProducto().getId())
                    .orElseThrow(()->new ResourceNotFoundException("No existe el producto "+item.getProducto().getId()));

            item.setPrice(producto.getPrecio());
            item.setTotal(producto.getPrecio()*item.getQuantity());
            total+=item.getTotal();
        }
        orden.setTotal(total);
        orden.getItems().forEach(line->line.setOrder(orden));
        orden.setFecha_creacion(LocalDateTime.now());

        return ordenRepositorio.save(orden);
    }

    @Override
    public void eliminarOrden(Long id) {
        ordenRepositorio.deleteById(id);
    }

    @Override
    public List<Orden> listarOrdenesPorIdDespachador(Long id) {
        return ordenRepositorio.findByDespachadorId(id);
    }

    @Override
    public List<Orden> listarOrdenesPorEstadoExceptoSinAtender(Long id, String estado) {
        return ordenRepositorio.findByDespachadorIdAndEstadoNot(id, estado);
    }

    @Override
    public List<Orden> listarOrdenesPorEstadoAndDespachadorId(Long id, String estado) {
        return ordenRepositorio.findByDespachadorIdAndEstado(id, estado);
    }

    @Override
    public List<Orden> listarOrdenesPorDespachadorIdAndEstadoNotAndEstadoNot(Long id, String estadoUno, String estadoDos) {
        return ordenRepositorio.findByDespachadorIdAndEstadoNotAndEstadoNot(id, estadoUno, estadoDos);
    }

    @Override
    public List<Orden> listarOrdenesPorDespachadorIdAndEstadoNotAndEstadoNotEstadoNot(Long id, String estadoUno, String estadoDos, String estadoTres) {
        return ordenRepositorio.findByDespachadorIdAndEstadoNotAndEstadoNotAndEstadoNot(id, estadoUno,estadoDos,estadoTres);
    }

    @Override
    public List<AtencionPedidoDTO> atencionesPedidosDTO(Long idPuesto) {
        List<Orden> ordenes = ordenRepositorio.findAll();
        List<AtencionPedidoDTO> atencionPedidoDTO = new ArrayList<>();

        ordenes.forEach(x ->{
            List<OrdenDetalle> ordenDetalle = x.getItems();
            AtencionPedidoDTO atencionPedidoDTONew = new AtencionPedidoDTO();
                ordenDetalle.forEach(y ->{
                    if(y.getProducto().getPuesto().getId()== idPuesto ){
                        atencionPedidoDTONew.setId(x.getId());
                        atencionPedidoDTONew.setCliente(x.getCliente());
                        atencionPedidoDTONew.setFechaPedido(String.valueOf(x.getFecha_creacion()));
                        atencionPedidoDTONew.setTotal(x.getTotal());
                        atencionPedidoDTONew.setEstado(x.getEstado());
                        atencionPedidoDTO.add(atencionPedidoDTONew);
                    }
                });
        });
        List<AtencionPedidoDTO> atencionPedidoDTOSinDuplicado = atencionPedidoDTO.stream().distinct().collect(Collectors.toList());
        return atencionPedidoDTOSinDuplicado;
    }

    @Override
    public List<PedidoClienteDTO> atencionPedidosCliente(Long idCliente) {

        List<PedidoClienteDTO> pedidosClienteDTO = new ArrayList<>();
        ordenRepositorio.findByClienteId(idCliente).forEach(pedidos ->{

            PedidoClienteDTO pedidoClienteDTO = new PedidoClienteDTO();

            pedidoClienteDTO.setId(pedidos.getId());
            pedidoClienteDTO.setFecha_pedido(String.valueOf(pedidos.getFecha_creacion()));
            pedidos.getItems().forEach(items ->{
                pedidoClienteDTO.setCantidad(items.getQuantity());
            });
            pedidoClienteDTO.setTotal(pedidos.getTotal());
            pedidoClienteDTO.setEstado(pedidos.getEstado());
            pedidosClienteDTO.add(pedidoClienteDTO);
        });
        return pedidosClienteDTO;
    }

    @Override
    public List<DetallePedidoDTO> detallePedidoDTO(Long idCliente) {
        List<DetallePedidoDTO> ordenDetalles = new ArrayList<>();

        ordenRepositorio.findByClienteId(idCliente).forEach(x ->{
            for (OrdenDetalle item:x.getItems()){

                DetallePedidoDTO detallePedidoDTO = new DetallePedidoDTO();
                detallePedidoDTO.setProducto(item.getProducto());
                detallePedidoDTO.setCantidad(item.getQuantity());
                detallePedidoDTO.setPrecio(item.getPrice());
                detallePedidoDTO.setTotal(item.getTotal());
                ordenDetalles.add(detallePedidoDTO);
            }
        });
        return ordenDetalles;
    }

}
