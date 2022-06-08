package api.mercado.app.seguridad;

import java.security.SignatureException;
import java.util.Date;

import api.mercado.app.entidades.Administrador;
import api.mercado.app.entidades.Cliente;
import api.mercado.app.entidades.Despachador;
import api.mercado.app.entidades.Vendedor;
import api.mercado.app.excepciones.VendedorException;
import api.mercado.app.repositorios.AdministradorRepositorio;
import api.mercado.app.repositorios.ClienteRepositorio;
import api.mercado.app.repositorios.DespachadorRepositorio;
import api.mercado.app.repositorios.VendedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtTokenProvider {

	@Autowired
	private DespachadorRepositorio despachadorRepositorio;

	@Autowired
	private AdministradorRepositorio administradorRepositorio;

	@Autowired
	private VendedorRepositorio vendedorRepositorio;

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;
	
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date dateNow = new Date();
		Date dateExpiration = new Date(dateNow.getTime() + jwtExpirationInMs);
		
		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		
		return token;
	}

	public String obtenerUsernameDelJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		String username = null;
		if(vendedorRepositorio.existsByUsername(claims.getSubject())){
			username = vendedorRepositorio.findByUsername(claims.getSubject()).orElse(new Vendedor()).getUsername();

		}if(clienteRepositorio.existsByEmail(claims.getSubject())){
			username = clienteRepositorio.findByEmail(claims.getSubject()).orElse(new Cliente()).getEmail();
		}
		if(despachadorRepositorio.existsByUsername(claims.getSubject())){
			username = despachadorRepositorio.findByUsername(claims.getSubject()).orElse(new Despachador()).getUsername();
		}
		if(administradorRepositorio.existsByUsername(claims.getSubject())){
			username = administradorRepositorio.findByUsername(claims.getSubject()).orElse(new Administrador()).getUsername();
		}

		return username;
	}
	
	public boolean validarToken(String token) throws SignatureException {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch(MalformedJwtException ex) {
			throw new VendedorException(HttpStatus.BAD_REQUEST, "Token JWT no válida");
		}catch(ExpiredJwtException ex) {
			throw new VendedorException(HttpStatus.BAD_REQUEST, "Token JWT no caducado");
		}catch(UnsupportedJwtException ex) {
			throw new VendedorException(HttpStatus.BAD_REQUEST, "Token JWT no compatible");
		}catch(IllegalArgumentException ex) {
			throw new VendedorException(HttpStatus.BAD_REQUEST, "La cadena Claims JWT está vacia");
		}			
	}
}
