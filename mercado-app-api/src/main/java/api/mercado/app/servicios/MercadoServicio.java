package api.mercado.app.servicios;


import api.mercado.app.entidades.Mercado;

import java.util.List;

public interface MercadoServicio {

    Mercado registrarMercado(Mercado mercado);
    Mercado modificarMercado(Mercado mercado);
    List<Mercado> listaMercados();
    Mercado obtenerMercadoPorId(Long idMercado);
    void eliminarMercado(Long idMercado);

    Mercado obtenerMercadoPorPuestoId(Long idPuesto);
}
