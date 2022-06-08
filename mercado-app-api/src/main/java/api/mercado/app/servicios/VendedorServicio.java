package api.mercado.app.servicios;

import api.mercado.app.entidades.Vendedor;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface VendedorServicio{

    Vendedor registrarVendedor(Vendedor vendedor);
    Vendedor modificarVendedor(Vendedor vendedor);
    List<Vendedor> listarVendedores();
    Vendedor obtenerVendedorPorId(Long idVendedor);
    void eliminarVendedor(Long idVendedor);

    Vendedor getVendedorPorUsername(String username);
    Vendedor getVendedorPorPuestoId(Long idPuesto);
}