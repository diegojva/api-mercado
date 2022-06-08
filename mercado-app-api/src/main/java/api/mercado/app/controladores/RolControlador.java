package api.mercado.app.controladores;

import api.mercado.app.entidades.Rol;
import api.mercado.app.repositorios.RolRepositorio;
import api.mercado.app.servicios.AdministradorServicio;
import api.mercado.app.servicios.RolServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolControlador {

    private final RolServicio rolServicio;

    public RolControlador(RolServicio rolServicio){
        this.rolServicio = rolServicio;
    }

    //METHOD ROLE

    @PostMapping("/nuevo-rol")
    public ResponseEntity<Rol> registrarRol(@Valid @RequestBody Rol rol) {
        Rol rolNew = rolServicio.registrarRol(rol);
        return new ResponseEntity<Rol>(rolNew, HttpStatus.CREATED);
    }

    @GetMapping("/obtener-rol/{id}")
    public ResponseEntity<Rol> obtenerRolPorId( @PathVariable Long id) {
        Rol rol = rolServicio.obtenerRolPorId(id);
        return new ResponseEntity<Rol>(rol, HttpStatus.OK);
    }

    @GetMapping("/listar-roles")
    public ResponseEntity<List<Rol>> listarRoles() {
        List<Rol> roles = rolServicio.listarRoles();
        return new ResponseEntity<List<Rol>>(roles, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        rolServicio.eliminarRol(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Rol> actualizarRol(@Valid @RequestBody Rol rol) {
        Rol rolUpdate = rolServicio.modificarRol(rol);
        return new ResponseEntity<Rol>(rolUpdate, HttpStatus.CREATED);
    }

}
