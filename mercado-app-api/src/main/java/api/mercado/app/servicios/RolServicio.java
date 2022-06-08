package api.mercado.app.servicios;

import api.mercado.app.entidades.Rol;
import api.mercado.app.entidades.Vendedor;

import java.util.List;

public interface RolServicio {

    Rol registrarRol(Rol rol);
    Rol modificarRol(Rol rol);
    List<Rol> listarRoles();
    Rol obtenerRolPorId(Long idRol);
    void eliminarRol(Long idRol);

}
