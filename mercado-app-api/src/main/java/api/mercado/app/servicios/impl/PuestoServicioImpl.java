package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.Puesto;
import api.mercado.app.repositorios.OrdenDetalleRepositorio;
import api.mercado.app.repositorios.PuestoRepositorio;
import api.mercado.app.servicios.PuestoServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuestoServicioImpl implements PuestoServicio {


    private final PuestoRepositorio puestoRepositorio;
    private final OrdenDetalleRepositorio ordenDetalleRepositorio;

    public PuestoServicioImpl(PuestoRepositorio puestoRepositorio,OrdenDetalleRepositorio ordenDetalleRepositorio ){
        this.puestoRepositorio = puestoRepositorio;
        this.ordenDetalleRepositorio = ordenDetalleRepositorio;
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

        return puestoRepositorio.findByVendedorId(idVendedor);
    }

    @Override
    public List<Puesto> obtenerPuestosPorIdMercado(Long idMercado) {
        return puestoRepositorio.findByMercadoId(idMercado);
    }

    @Override
    public List<Puesto> obtenerPuestosPorIdsector(Long idSector) {
        return puestoRepositorio.findBySectorId(idSector);
    }

    @Override
    public List<Puesto> obtenerPuestosPorEstado(String estado) {
        return puestoRepositorio.findByEstado(estado);
    }

    @Override
    public List<Puesto> obtenerPuestosPorMercadoIdYEstado(Long id, String estado) {
        return puestoRepositorio.findByMercadoIdAndEstado(id,estado);
    }

    @Override
    public void eliminarPuesto(Long idPuesto) {
        puestoRepositorio.deleteById(idPuesto);
    }

}
