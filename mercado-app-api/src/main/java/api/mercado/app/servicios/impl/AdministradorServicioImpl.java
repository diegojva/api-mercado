package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.Administrador;
import api.mercado.app.entidades.Despachador;
import api.mercado.app.entidades.Rol;
import api.mercado.app.repositorios.AdministradorRepositorio;
import api.mercado.app.repositorios.DespachadorRepositorio;
import api.mercado.app.repositorios.RolRepositorio;
import api.mercado.app.repositorios.VendedorRepositorio;
import api.mercado.app.servicios.AdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class AdministradorServicioImpl implements AdministradorServicio {

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @Autowired
    private VendedorRepositorio vendedorRepositorio;

    @Autowired
    private DespachadorRepositorio despachadorRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Override
    public Administrador createAdministrador(Administrador administrador) {
        Administrador administradorNew = new Administrador();
        if(!vendedorRepositorio.existsByUsername(administrador.getUsername()) && !despachadorRepositorio.existsByUsername(administrador.getUsername())){
            administradorNew.setUsername(administrador.getUsername());
            administradorNew.setPassword(passwordEncoder.encode(administrador.getPassword()));
            administradorNew.setEstado("ACTIVO");

            Rol roles = rolRepositorio.findByNombre("ROLE_MASTER").get();
            administradorNew.setRoles(Collections.singleton(roles));
        }
        return administradorRepositorio.save(administradorNew);
    }

    @Override
    public Administrador obtenerAdministradorPorId(Long idAdministrador) {
        return administradorRepositorio.findById(idAdministrador).orElse(new Administrador());
    }
}
