package api.mercado.app.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthResponseDTO {

	private String tokenDeAcceso;
	private String tipoDeToken = "Bearer";
	private Long idUser;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JWTAuthResponseDTO(String tokenDeAcceso) {
		super();
		this.tokenDeAcceso = tokenDeAcceso;
	}
	
	public JWTAuthResponseDTO(String tokenDeAcceso, String tipoDeToken, Long idUser) {
		this.tokenDeAcceso = tokenDeAcceso;
		this.tipoDeToken = tipoDeToken;
		this.idUser = idUser;
	}

	public JWTAuthResponseDTO(String tokenDeAcceso, String tipoDeToken, Long idUser, Collection<? extends GrantedAuthority> authorities) {
		this.tokenDeAcceso = tokenDeAcceso;
		this.tipoDeToken = tipoDeToken;
		this.idUser = idUser;
		this.authorities = authorities;
	}


	public String getTokenDeAcceso() {
		return tokenDeAcceso;
	}


	public void setTokenDeAcceso(String tokenDeAcceso) {
		this.tokenDeAcceso = tokenDeAcceso;
	}


	public String getTipoDeToken() {
		return tipoDeToken;
	}


	public void setTipoDeToken(String tipoDeToken) {
		this.tipoDeToken = tipoDeToken;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}
