package com.pi.factoring_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.factoring_backend.entity.TipoPerfilUsuario;
import com.pi.factoring_backend.entity.CuentaUsuario;
import com.pi.factoring_backend.repository.TipoPerfilUsuarioRepository;
import com.pi.factoring_backend.repository.CuentaUsuarioRepository;

@Service
public class CuentaUsuarioServiceImpl implements CuentaUsuarioService {
	
	@Autowired
	private CuentaUsuarioRepository repository;
	
	@Autowired
	private TipoPerfilUsuarioRepository rolRepo;
	
	@Override
	public List<CuentaUsuario> listaUsuarios(){
		return repository.findAll();
	}

	@Override
	public CuentaUsuario registrarUsuario(CuentaUsuario usuario) {
		TipoPerfilUsuario perfil = rolRepo.findByNombre("usuario");
		usuario.getTiposPerfilesUsuario().add(perfil);
		return repository.save(usuario);
	}
	
	@Override
	public CuentaUsuario obtenerUsuarioPorId(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException(String.format("No se encontró el usuario id: %s", id)));
	}
	
	@Override
	public CuentaUsuario actualizarUsuario(CuentaUsuario usuario) {
		return repository.save(usuario);
	}
	
	@Override
	public void eliminarUsuario(Long id) {
		repository.deleteById(id);
	}

	@Override
	public CuentaUsuario login(String email, String contrasena) {
		return repository.filtrarPorCorreo(email);

	}

	@Override
	public List<CuentaUsuario> listarUsuarioPorCorreo(String correo) {
		// TODO Auto-generated method stub
		return repository.findByCorreo(correo);
	}
	
	@Override
	public TipoPerfilUsuario buscarTipoPerfilUsuarioPorId(Long id) {
		// TODO Auto-generated method stub
				Optional<TipoPerfilUsuario> tipoPerfilOptional = rolRepo.findById(id);
		        if (tipoPerfilOptional.isPresent()) {
		            return tipoPerfilOptional.get();
		        } else {
		            return null;
		        }
	}
	
	@Override
	public CuentaUsuario buscarCuentaUsuarioPorCorreo(String correo) {
		// TODO Auto-generated method stub
		return repository.filtrarPorCorreo(correo);
	}

	@Override
	public List<CuentaUsuario> listarUsuarioPorRuc(String ruc) {
		// TODO Auto-generated method stub
		return repository.findByRuc(ruc);
	}

}
