package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.UnidadMedida;
import api.mercado.app.repositorios.UnidadMedidaRepositorio;
import api.mercado.app.servicios.UnidadMedidaServicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaImpl implements UnidadMedidaServicio {

    private final UnidadMedidaRepositorio unidadMedidaRepositorio;

    public UnidadMedidaImpl(UnidadMedidaRepositorio unidadMedidaRepositorio){
        this.unidadMedidaRepositorio = unidadMedidaRepositorio;
    }

    @Override
    public UnidadMedida registrarUnidadMedida(UnidadMedida unidadMedida) {
        return unidadMedidaRepositorio.save(unidadMedida);
    }

    @Override
    public UnidadMedida modificarUnidadMedida(UnidadMedida unidadMedida) {
        return unidadMedidaRepositorio.save(unidadMedida);
    }

    @Override
    public List<UnidadMedida> listarUnidadesMedidas() {
        return unidadMedidaRepositorio.findAll();
    }

    @Override
    public UnidadMedida obtenerUnidadMedidaPorId(Long idUnidadMedida) {
        return unidadMedidaRepositorio.findById(idUnidadMedida).orElse(new UnidadMedida());
    }

    @Override
    public void eliminarUnidadMedida(Long idUnidadMedida) {
        unidadMedidaRepositorio.deleteById(idUnidadMedida);
    }
}
