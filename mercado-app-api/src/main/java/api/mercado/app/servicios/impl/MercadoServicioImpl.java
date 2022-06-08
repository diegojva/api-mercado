package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.Mercado;
import api.mercado.app.repositorios.MercadoRepositorio;
import api.mercado.app.repositorios.PuestoRepositorio;
import api.mercado.app.servicios.MercadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MercadoServicioImpl implements MercadoServicio {

    @Autowired
    private MercadoRepositorio mercadoRepositorio;

    @Autowired
    private PuestoRepositorio puestoRepositorio;

    @Override
    public Mercado registrarMercado(Mercado mercado) {
        mercado.setNombre(mercado.getNombre().toUpperCase());
        return mercadoRepositorio.save(mercado);
    }

    @Override
    public Mercado modificarMercado(Mercado mercado) {
        mercado.setNombre(mercado.getNombre().toUpperCase());
        return mercadoRepositorio.save(mercado);
    }

    @Override
    public List<Mercado> listaMercados() {
        return mercadoRepositorio.findAll();
    }

    @Override
    public Mercado obtenerMercadoPorId(Long idMercado) {
        return mercadoRepositorio.findById(idMercado).orElse(new Mercado());
    }

    @Override
    public void eliminarMercado(Long idMercado) {
        mercadoRepositorio.deleteById(idMercado);
    }

    @Override
    public Mercado obtenerMercadoPorPuestoId(Long idPuesto) {
        return puestoRepositorio.findById(idPuesto).get().getMercado();
    }
}
