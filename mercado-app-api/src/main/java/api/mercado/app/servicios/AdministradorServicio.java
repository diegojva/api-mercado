package api.mercado.app.servicios;

import api.mercado.app.entidades.Administrador;

public interface AdministradorServicio {

    Administrador createAdministrador(Administrador administrador);
    Administrador obtenerAdministradorPorId(Long idAdministrador);
}
