package api.mercado.app.servicios;

import api.mercado.app.entidades.Puesto;

import java.util.List;

public interface PuestoServicio {

    Puesto registrarPuesto(Puesto puesto);
    Puesto modificarPuesto(Puesto puesto);
    List<Puesto> listarPuestos();
    Puesto obtenerPuestoPorId(Long idPuesto);
    void eliminarPuesto(Long idPuesto);
    public List<Puesto> obtenerPuestosPorIdVendedor(Long idVendedor);
    //Puesto obtenerPuestoPorIdVendedor(Long idVendedor);
}
