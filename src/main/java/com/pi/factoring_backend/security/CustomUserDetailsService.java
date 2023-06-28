package com.pi.factoring_backend.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pi.factoring_backend.entity.CuentaUsuario;
import com.pi.factoring_backend.entity.TipoPerfilUsuario;
import com.pi.factoring_backend.repository.CuentaUsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CuentaUsuarioRepository cuentaUsuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		CuentaUsuario usuario = cuentaUsuarioRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email especificado: " + email));
		
		
		return new User(usuario.getEmail(), usuario.getContrasena(), mapearPerfiles(usuario.getTiposPerfilesUsuario()));
	}
	
	private Collection<? extends GrantedAuthority> mapearPerfiles(Set<TipoPerfilUsuario> perfiles){
		return perfiles.stream().map(
				perfil -> new SimpleGrantedAuthority(perfil.getNombre())).collect(Collectors.toList());
	}

}
