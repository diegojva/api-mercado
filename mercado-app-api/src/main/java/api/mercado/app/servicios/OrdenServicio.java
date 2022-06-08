package api.mercado.app.servicios;

import api.mercado.app.dto.AtencionPedidoDTO;
import api.mercado.app.dto.DetallePedidoDTO;
import api.mercado.app.dto.PedidoClienteDTO;
import api.mercado.app.entidades.Orden;

import java.util.List;

public interface OrdenServicio {

    Orden crearOrden(Orden orden);
    List<Orden> listarOrdenes();
    //List<ReporteOrdenDTO> reportOrder();
    List<Orden> listarOrdenesPorIdCliente(Long id);
    Orden getOrdenPorId(Long id);
    Orden actualizarOrden(Orden orden);
    void eliminarOrden(Long id);

    List<Orden> listarOrdenesPorIdDespachador(Long id);
    List<Orden> listarOrdenesPorEstadoExceptoSinAtender(Long id, String estado);
    List<Orden> listarOrdenesPorEstadoAndDespachadorId(Long id, String estado);
    List<Orden> listarOrdenesPorDespachadorIdAndEstadoNotAndEstadoNot(Long id, String estadoUno, String estadoDos);
    List<Orden> listarOrdenesPorDespachadorIdAndEstadoNotAndEstadoNotEstadoNot(Long id, String estadoUno, String estadoDos, String estadoTres);
    List<AtencionPedidoDTO> atencionesPedidosDTO(Long idPuesto);
    List<PedidoClienteDTO> atencionPedidosCliente(Long idCliente);
    List<DetallePedidoDTO> detallePedidoDTO(Long idCliente);
}
