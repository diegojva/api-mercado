package api.mercado.app.servicios;


import api.mercado.app.entidades.Despachador;

import java.util.List;

public interface DespachadorServicio {

    Despachador createDespachador(Despachador despachador);
    List<Despachador> obtenerDespachadorPorEstados(String estadoUno, String estadoDos);
    List<Despachador> listarDespachadores();
    Despachador obtenerDespachadorPorId(Long id);
    void eliminarDespachador(Long idDespachador);
    Despachador actualizarDespachador(Despachador despachador);
}
