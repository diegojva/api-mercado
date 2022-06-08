package api.mercado.app.controladores;

import api.mercado.app.entidades.*;
import api.mercado.app.repositorios.RolRepositorio;
import api.mercado.app.servicios.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/adm")
public class AdministradorControlador {

    private final AdministradorServicio administradorServicio;

    public AdministradorControlador(AdministradorServicio administradorServicio){
        this.administradorServicio = administradorServicio;
    }

    @PostMapping
    public ResponseEntity<Administrador> registrarAdministrador(@Valid @RequestBody Administrador administrador) {
        Administrador administradorNew = administradorServicio.createAdministrador(administrador);
        return new ResponseEntity<Administrador>(administradorNew, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> obtenerAdministradorPorId( @PathVariable Long id) {
        Administrador administrador = administradorServicio.obtenerAdministradorPorId(id);
        return new ResponseEntity<Administrador>(administrador, HttpStatus.OK);
    }



}
