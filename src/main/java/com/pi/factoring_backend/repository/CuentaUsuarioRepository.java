package com.pi.factoring_backend.repository;


import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pi.factoring_backend.entity.CuentaUsuario;
import com.pi.factoring_backend.entity.Movimiento;

public interface CuentaUsuarioRepository extends JpaRepository<CuentaUsuario, Long>{

	Optional<CuentaUsuario> findByEmail(String email);
	Boolean existsByEmail(String email);

	/*CuentaUsuario findByEmail(String email);*/
	@Query("select a from CuentaUsuario a where a.email = ?1")
	public CuentaUsuario filtrarPorCorreo(String email);
	@Query("select c from CuentaUsuario c where c.email = ?1")
	public List<CuentaUsuario> findByCorreo(String correo);
	@Query("select c from CuentaUsuario c where c.ruc = ?1")
	public List<CuentaUsuario> findByRuc(String ruc);

}
