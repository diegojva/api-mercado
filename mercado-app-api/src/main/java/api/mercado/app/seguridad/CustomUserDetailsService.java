package api.mercado.app.seguridad;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import api.mercado.app.entidades.Rol;
import api.mercado.app.entidades.Vendedor;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Vendedor vendedor = vendedorRepositorio.findByUsername(username);
		if(vendedor == null){
			new UsernameNotFoundException("Usuario no encontrado con ese username o email : " + username);
		}

		return new User(vendedor.getUsername(), vendedor.getPassword(), mapearRoles(vendedor.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles){
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}
}
