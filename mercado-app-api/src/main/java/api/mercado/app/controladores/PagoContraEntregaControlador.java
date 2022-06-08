package api.mercado.app.controladores;

import api.mercado.app.entidades.Orden;
import api.mercado.app.entidades.PagoContraEntrega;
import api.mercado.app.servicios.OrdenServicio;
import api.mercado.app.servicios.PagoContraEntregaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pago-contra-entrega")
public class PagoContraEntregaControlador {

    @Autowired
    private PagoContraEntregaServicio pagoContraEntregaServicio;

    @PostMapping
    public ResponseEntity<PagoContraEntrega> createOrden(@RequestBody PagoContraEntrega pagoContraEntrega){
        PagoContraEntrega pagoContraEntregaNew=pagoContraEntregaServicio.createPagoContraEntrega(pagoContraEntrega);
        return new ResponseEntity<PagoContraEntrega>(pagoContraEntregaNew, HttpStatus.CREATED);
    }
}
