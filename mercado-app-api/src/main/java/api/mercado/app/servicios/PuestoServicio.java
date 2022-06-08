package api.mercado.app.servicios;

import api.mercado.app.entidades.Puesto;

import java.util.List;

public interface PuestoServicio {

    Puesto registrarPuesto(Puesto puesto);
    Puesto modificarPuesto(Puesto puesto);
    List<Puesto> listarPuestos();
    Puesto obtenerPuestoPorId(Long idPuesto);
    void eliminarPuesto(Long idPuesto);

    List<Puesto> obtenerPuestosPorIdVendedor(Long idVendedor);
    List<Puesto> obtenerPuestosPorIdMercado(Long idMercado);
    List<Puesto> obtenerPuestosPorIdsector(Long idSector);
    List<Puesto> obtenerPuestosPorEstado(String estado);
    List<Puesto> obtenerPuestosPorMercadoIdYEstado(Long id, String estado);
}
