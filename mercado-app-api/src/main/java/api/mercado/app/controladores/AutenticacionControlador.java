package api.mercado.app.controladores;

import api.mercado.app.entidades.Rol;
import api.mercado.app.repositorios.AdministradorRepositorio;
import api.mercado.app.repositorios.ClienteRepositorio;
import api.mercado.app.repositorios.DespachadorRepositorio;
import api.mercado.app.repositorios.VendedorRepositorio;
import api.mercado.app.dto.JWTAuthResponseDTO;
import api.mercado.app.seguridad.JwtTokenProvider;
import api.mercado.app.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AutenticacionControlador {

    private String token;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @Autowired
    private VendedorRepositorio vendedorRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private DespachadorRepositorio despachadorRepositorio;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO login){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        token = jwtTokenProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails)authentication.getPrincipal();

        JWTAuthResponseDTO jwtAuthResonseDTO = new JWTAuthResponseDTO(token);
        jwtAuthResonseDTO.setIdUser(verificarUserByToken());
        jwtAuthResonseDTO.setAuthorities(userDetails.getAuthorities());
        return ResponseEntity.ok(jwtAuthResonseDTO);
    }

    //EXCEPTIONAL METHODS

    public Long verificarUserByToken(){
        String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
        Long id = null;
        if(vendedorRepositorio.existsByUsername(username)){
            id = vendedorRepositorio.findByUsername(username).get().getId();
        }if(clienteRepositorio.existsByEmail(username)) {
            id = clienteRepositorio.findByEmail(username).get().getId();
        }if(despachadorRepositorio.existsByUsername(username)) {
            id = despachadorRepositorio.findByUsername(username).get().getId();
        }
        if(administradorRepositorio.existsByUsername(username)){
            id = administradorRepositorio.findByUsername(username).get().getId();
        }
        return id;
    }

}
