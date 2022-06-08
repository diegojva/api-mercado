package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.*;
import api.mercado.app.repositorios.*;
import api.mercado.app.servicios.OrdenServicio;
import api.mercado.app.servicios.VendedorServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class VendedorServicioImpl implements VendedorServicio {

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private OrdenDetalleRepositorio ordenDetalleRepositorio;

    @Autowired
    private OrdenRepositorio ordenRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private PuestoRepositorio puestoRepositorio;

    @Autowired
    private DespachadorRepositorio despachadorRepositorio;

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final VendedorRepositorio vendedorRepositorio;

    public VendedorServicioImpl(VendedorRepositorio vendedorRepositorio){
        this.vendedorRepositorio = vendedorRepositorio;
    }

    @Override
    public Vendedor registrarVendedor(Vendedor vendedor) {

        Vendedor vendedorNew = new Vendedor();
        if(!despachadorRepositorio.existsByUsername(vendedor.getUsername()) && !administradorRepositorio.existsByUsername(vendedor.getUsername())) {

            vendedorNew.setNombre(vendedor.getNombre());
            vendedorNew.setApellido(vendedor.getApellido());
            vendedorNew.setUsername(vendedor.getUsername());
            vendedorNew.setDni(vendedor.getDni());
            vendedorNew.setPassword(passwordEncoder.encode(vendedor.getPassword()));

            Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
            vendedorNew.setRoles(Collections.singleton(roles));

            vendedorNew.setEstado("Activo");
        }
        return vendedorRepositorio.save(vendedorNew);
    }

    @Override
    public Vendedor modificarVendedor(Vendedor vendedor) {
        Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
        vendedor.setRoles(Collections.singleton(roles));
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

    @Override
    public void eliminarVendedor(Long idVendedor) {
        Vendedor vendedor = vendedorRepositorio.findById(idVendedor).orElse(new Vendedor());
        for(Rol rol : vendedor.getRoles()){
            vendedor.getRoles().remove(rol);
        }
        for(Puesto puesto : puestoRepositorio.findByVendedorId(idVendedor)){
            for(Producto producto : productoRepositorio.findByPuestoId(puesto.getId())){

                for(OrdenDetalle ordenDetalle: ordenDetalleRepositorio.findByProductoId(producto.getId())){
                    ordenRepositorio.deleteById(ordenDetalle.getOrder().getId());
                }
                productoRepositorio.deleteById(producto.getId());
            }
            puestoRepositorio.deleteById(puesto.getId());
        }

        vendedorRepositorio.deleteById(idVendedor);
    }

    @Override
    public Vendedor getVendedorPorUsername(String username) {
        return vendedorRepositorio.findByUsername(username).orElse(new Vendedor());
    }

    @Override
    public Vendedor getVendedorPorPuestoId(Long idPuesto) {

        return puestoRepositorio.findById(idPuesto).get().getVendedor();
    }

}