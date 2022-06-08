package api.mercado.app.servicios.impl;


import api.mercado.app.entidades.*;
import api.mercado.app.excepciones.ResourceNotFoundException;
import api.mercado.app.repositorios.*;
import api.mercado.app.servicios.DespachadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DespachadorServicioImpl implements DespachadorServicio {

    @Autowired
    private OrdenRepositorio ordenRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @Autowired
    private VendedorRepositorio vendedorRepositorio;

    @Autowired
    private DespachadorRepositorio despachadorRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Despachador createDespachador(Despachador despachador) {
        Despachador despachadorNew = new Despachador();
        if(!vendedorRepositorio.existsByUsername(despachador.getUsername()) && !administradorRepositorio.existsByUsername(despachador.getUsername())){

            despachadorNew.setUsername(despachador.getUsername());
            despachadorNew.setPassword(passwordEncoder.encode(despachador.getPassword()));
            despachadorNew.setNombre(despachador.getNombre());
            despachadorNew.setApellido(despachador.getApellido());
            despachadorNew.setDni(despachador.getDni());
            despachadorNew.setEstado("NO DISPONIBLE");

            Rol roles = rolRepositorio.findByNombre("ROLE_EMPLOYEE").get();
            despachadorNew.setRoles(Collections.singleton(roles));

        }
        return despachadorRepositorio.save(despachadorNew);
    }

    @Override
    public List<Despachador> obtenerDespachadorPorEstados(String estadoUno, String estadoDos) {
        return despachadorRepositorio.findByEstadoOrEstado(estadoUno,estadoDos);
    }

    @Override
    public List<Despachador> listarDespachadores() {
        return despachadorRepositorio.findAll();
    }

    @Override
    public Despachador obtenerDespachadorPorId(Long id) {
        return despachadorRepositorio.findById(id).orElse(new Despachador());
    }

    @Override
    public void eliminarDespachador(Long id) {
        Despachador despachador = despachadorRepositorio.findById(id).orElse(new Despachador());
        for(Rol rol : despachador.getRoles()){
            despachador.getRoles().remove(rol);
        }
        for(Orden orden : ordenRepositorio.findByDespachadorId(id)){
            ordenRepositorio.deleteById(orden.getId());
        }
        despachadorRepositorio.deleteById(id);
    }

    @Override
    public Despachador actualizarDespachador(Despachador despachador) {
        Rol roles = rolRepositorio.findByNombre("ROLE_EMPLOYEE").get();
        despachador.setRoles(Collections.singleton(roles));
        return despachadorRepositorio.save(despachador);
    }

}
