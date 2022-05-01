package api.mercado.app.controladores;

import api.mercado.app.entidades.Puesto;
import api.mercado.app.entidades.Vendedor;
import api.mercado.app.repositorios.VendedorRepositorio;
import api.mercado.app.servicios.VendedorServicio;
import api.mercado.app.utilidades.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/auth")
public class AutenticacionControlador {

    @Autowired
    private Optional vendedor;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private VendedorServicio vendedorServicio;


    @PostMapping
    public ResponseEntity<Optional> authenticateUser(@RequestBody Login login){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        if(authentication.isAuthenticated()){
            vendedor = vendedorServicio.findByUsername(login.getUsername());
        }

        return new ResponseEntity<Optional>(vendedor, HttpStatus.OK);
    }

   /* @GetMapping("/{id}")
    public ResponseEntity<Vendedor> obtenerVendedorPorId(@PathVariable Long id) {
        Vendedor vendedor = vendedorServicio.obtenerVendedorPorId(id);
        return new ResponseEntity<Vendedor>(vendedor, HttpStatus.OK);
    }*/
    
}
