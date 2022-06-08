package api.mercado.app.servicios.impl;

import api.mercado.app.entidades.PagoContraEntrega;
import api.mercado.app.repositorios.PagoContraEntregaRepositorio;
import api.mercado.app.servicios.PagoContraEntregaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PagoContraEntregaServicioImpl implements PagoContraEntregaServicio {

    @Autowired
    private PagoContraEntregaRepositorio pagoContraEntregaRepositorio;

    @Override
    public PagoContraEntrega createPagoContraEntrega(PagoContraEntrega pagoContraEntrega) {
        return pagoContraEntregaRepositorio.save(pagoContraEntrega);
    }
}
