package com.pi.factoring_backend.service;

import java.util.List;

import com.pi.factoring_backend.entity.CuentaUsuario;
import com.pi.factoring_backend.entity.TipoPerfilUsuario;

public interface CuentaUsuarioService {
	public List<CuentaUsuario> listaUsuarios();
	
	public CuentaUsuario registrarUsuario(CuentaUsuario usuario);
	
	public CuentaUsuario obtenerUsuarioPorId(Long id);
	
	public CuentaUsuario actualizarUsuario(CuentaUsuario usuario);
	
	public void eliminarUsuario(Long id);
	
	public CuentaUsuario login(String email, String contrasena);
	
	public List<CuentaUsuario> listarUsuarioPorCorreo(String correo);
	
	public TipoPerfilUsuario buscarTipoPerfilUsuarioPorId(Long id);
	
	public CuentaUsuario buscarCuentaUsuarioPorCorreo(String correo);
	
	public List<CuentaUsuario> listarUsuarioPorRuc(String ruc);
	
	
}
