package api.mercado.app.seguridad;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import api.mercado.app.entidades.*;
import api.mercado.app.repositorios.AdministradorRepositorio;
import api.mercado.app.repositorios.ClienteRepositorio;
import api.mercado.app.repositorios.DespachadorRepositorio;
import api.mercado.app.repositorios.VendedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

	@Autowired
	private VendedorRepositorio vendedorRepositorio;

	@Autowired
	private AdministradorRepositorio administradorRepositorio;

	@Autowired
	private DespachadorRepositorio despachadorRepositorio;

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(vendedorRepositorio.existsByUsername(username)){
			Vendedor vendedor = vendedorRepositorio.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Vendedor no encontrado con ese username: " + username));

			return new User(vendedor.getUsername(), vendedor.getPassword(), mapearRoles(vendedor.getRoles()));
		}
		if(clienteRepositorio.existsByEmail(username)){
			Cliente cliente = clienteRepositorio.findByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado con ese username: " + username));

			return new User(cliente.getEmail(), cliente.getPassword(), mapearRoles(cliente.getRoles()));
		}
		if(despachadorRepositorio.existsByUsername(username)){
			Despachador despachador = despachadorRepositorio.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Despachador no encontrado con ese username: " + username));

			return new User(despachador.getUsername(), despachador.getPassword(), mapearRoles(despachador.getRoles()));
		}
		if(administradorRepositorio.existsByUsername(username)){
			Administrador administrador = administradorRepositorio.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("Administrador no encontrado con ese username: " + username));

			return new User(administrador.getUsername(), administrador.getPassword(), mapearRoles(administrador.getRoles()));
		}
		return null;
	}

	private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles){
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}
}
