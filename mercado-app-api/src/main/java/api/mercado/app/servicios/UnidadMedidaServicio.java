package api.mercado.app.servicios;

import api.mercado.app.entidades.UnidadMedida;
import api.mercado.app.entidades.Vendedor;

import java.util.List;

public interface UnidadMedidaServicio {

    UnidadMedida registrarUnidadMedida(UnidadMedida unidadMedida);
    UnidadMedida modificarUnidadMedida(UnidadMedida unidadMedida);
    List<UnidadMedida> listarUnidadesMedidas();
    UnidadMedida obtenerUnidadMedidaPorId(Long idUnidadMedida);
    void eliminarUnidadMedida(Long idUnidadMedida);
}
