package api.mercado.app.servicios;

import api.mercado.app.entidades.Cliente;
import api.mercado.app.entidades.Vendedor;

import java.util.List;

public interface ClienteServicio {

    Cliente registrarCliente(Cliente cliente);
    Cliente modificarCliente(Cliente cliente);
    List<Cliente> listaClientes();
    Cliente obtenerClientePorId(Long idCliente);
    void eliminarCliente(Long idCliente);

    List<Cliente> findByOrdenId(Long idOrden);
    Cliente getClientePorUsername(String username);
}
