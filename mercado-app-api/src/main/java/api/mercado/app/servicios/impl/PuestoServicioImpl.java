package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.Puesto;
import api.mercado.app.entidades.Vendedor;
import api.mercado.app.repositorios.PuestoRepositorio;
import api.mercado.app.repositorios.VendedorRepositorio;
import api.mercado.app.servicios.PuestoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PuestoServicioImpl implements PuestoServicio {

    @Autowired
    private final PuestoRepositorio puestoRepositorio;
    private final VendedorRepositorio vendedorRepositorio;

    public PuestoServicioImpl(PuestoRepositorio puestoRepositorio, VendedorRepositorio vendedorRepositorio){
        this.puestoRepositorio = puestoRepositorio;
        this.vendedorRepositorio = vendedorRepositorio;
    }

    @Override
    public Puesto registrarPuesto(Puesto puesto) {
        return puestoRepositorio.save(puesto);
    }

    @Override
    public Puesto modificarPuesto(Puesto puesto) {
        return puestoRepositorio.save(puesto);
    }

    @Override
    public List<Puesto> listarPuestos() {
        return puestoRepositorio.findAll();
    }

    @Override
    public Puesto obtenerPuestoPorId(Long idPuesto) {
        return puestoRepositorio.findById(idPuesto).orElse(new Puesto());
    }

    @Override
    public List<Puesto> obtenerPuestosPorIdVendedor(Long idVendedor) {
       // Vendedor vendedor = vendedorRepositorio.findById(idVendedor).orElse(new Vendedor());
        List<Puesto> puestos = puestoRepositorio.findByVendedorId(idVendedor);
        return puestos;
    }


    @Override
    public void eliminarPuesto(Long idPuesto) {
        puestoRepositorio.deleteById(idPuesto);
    }
}
