package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.Rol;
import api.mercado.app.repositorios.OrdenDetalleRepositorio;
import api.mercado.app.repositorios.PuestoRepositorio;
import api.mercado.app.repositorios.RolRepositorio;
import api.mercado.app.servicios.RolServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServicioImpl implements RolServicio{

    private final RolRepositorio rolRepositorio;

    public RolServicioImpl(RolRepositorio rolRepositorio){
        this.rolRepositorio = rolRepositorio;
    }

    @Override
    public Rol registrarRol(Rol rol) {
        return rolRepositorio.save(rol);
    }

    @Override
    public Rol modificarRol(Rol rol) {
        return rolRepositorio.save(rol);
    }

    @Override
    public List<Rol> listarRoles() {
        return rolRepositorio.findAll();
    }

    @Override
    public Rol obtenerRolPorId(Long idRol) {
        return rolRepositorio.findById(idRol).orElse(new Rol());
    }

    @Override
    public void eliminarRol(Long idRol) {
        rolRepositorio.deleteById(idRol);
    }
}
