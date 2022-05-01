package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.Rol;
import api.mercado.app.entidades.Vendedor;
import api.mercado.app.repositorios.RolRepositorio;
import api.mercado.app.repositorios.VendedorRepositorio;
import api.mercado.app.servicios.VendedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VendedorServicioImpl implements VendedorServicio {

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final VendedorRepositorio vendedorRepositorio;

    public VendedorServicioImpl(VendedorRepositorio vendedorRepositorio){
        this.vendedorRepositorio = vendedorRepositorio;
    }

    @Override
    public Vendedor registrarVendedor(Vendedor vendedor) {

        Vendedor vendedorNew = new Vendedor();
        vendedorNew.setNombre(vendedor.getNombre());
        vendedorNew.setApellido(vendedor.getApellido());
        vendedorNew.setUsername(vendedor.getUsername());
        vendedorNew.setDni(vendedor.getDni());
        vendedorNew.setPassword(passwordEncoder.encode(vendedor.getPassword()));

        Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
        vendedorNew.setRoles(Collections.singleton(roles));

        vendedorNew.setEstado("Activo");
        return vendedorRepositorio.save(vendedorNew);
    }

    @Override
    public Vendedor modificarVendedor(Vendedor vendedor) {
        return vendedorRepositorio.save(vendedor);
    }

    @Override
    public List<Vendedor> listarVendedores() {
        return vendedorRepositorio.findAll();
    }

    @Override
    public Vendedor obtenerVendedorPorId(Long idVendedor) {
        return vendedorRepositorio.findById(idVendedor).orElse(new Vendedor());
    }

    public Vendedor obtenerVendedorPorUsername(String username) {
        return vendedorRepositorio.findByUsername(username);
    }

    @Override
    public void eliminarVendedor(Long idVendedor) {
        vendedorRepositorio.deleteById(idVendedor);
    }

    @Override
    public Optional<Vendedor> findByUsername(String username) {
        return Optional.ofNullable(vendedorRepositorio.findByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}