package api.mercado.app.servicios;

import api.mercado.app.entidades.Vendedor;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface VendedorServicio extends UserDetailsService {

    Vendedor registrarVendedor(Vendedor vendedor);
    Vendedor modificarVendedor(Vendedor vendedor);
    List<Vendedor> listarVendedores();
    Vendedor obtenerVendedorPorId(Long idVendedor);
    void eliminarVendedor(Long idVendedor);

    public Optional<Vendedor> findByUsername(String username);

}