package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.Cliente;
import api.mercado.app.entidades.Despachador;
import api.mercado.app.entidades.Orden;
import api.mercado.app.entidades.Rol;
import api.mercado.app.repositorios.ClienteRepositorio;
import api.mercado.app.repositorios.OrdenRepositorio;
import api.mercado.app.repositorios.RolRepositorio;
import api.mercado.app.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private OrdenRepositorio ordenRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Override
    public Cliente registrarCliente(Cliente cliente) {

        Cliente clienteNew = Cliente.builder()
                .email(cliente.getEmail())
                .password(passwordEncoder.encode(cliente.getPassword()))
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .telefono(cliente.getTelefono())
                .direccion(cliente.getDireccion()).build();

        Rol roles = rolRepositorio.findByNombre("ROLE_USER").get();
        clienteNew.setRoles(Collections.singleton(roles));

        return clienteRepositorio.save(clienteNew);
    }

    @Override
    public Cliente modificarCliente(Cliente cliente) {
        Rol roles = rolRepositorio.findByNombre("ROLE_USER").get();
        cliente.setRoles(Collections.singleton(roles));
        return clienteRepositorio.save(cliente);
    }

    @Override
    public List<Cliente> listaClientes() {
        return clienteRepositorio.findAll();
    }

    @Override
    public Cliente obtenerClientePorId(Long idCliente) {
        return clienteRepositorio.findById(idCliente).orElse(new Cliente());
    }

    @Override
    public void eliminarCliente(Long idCliente) {
        Cliente cliente = clienteRepositorio.findById(idCliente).orElse(new Cliente());
        for(Rol rol : cliente.getRoles()){
            cliente.getRoles().remove(rol);
        }
        for(Orden orden : ordenRepositorio.findByClienteId(idCliente)){
            ordenRepositorio.deleteById(orden.getId());
        }
        clienteRepositorio.deleteById(idCliente);
    }

    @Override
    public List<Cliente> findByOrdenId(Long idOrden) {
        Cliente cliente = ordenRepositorio.findById(idOrden).get().getCliente();
        return null;
    }

    @Override
    public Cliente getClientePorUsername(String email) {
        return clienteRepositorio.findByEmail(email).orElse(new Cliente());
    }
}
