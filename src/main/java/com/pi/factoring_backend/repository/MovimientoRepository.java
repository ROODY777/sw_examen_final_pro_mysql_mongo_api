package com.pi.factoring_backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pi.factoring_backend.entity.CuentaUsuario;
import com.pi.factoring_backend.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{
	
	@Query("SELECT m FROM Movimiento m WHERE m.cuentaUsuario.id =:idUsuario AND m.estado =:estado")
	public List<Movimiento> findByCuentaUsuarioAndEstado(Long idUsuario, String estado);
	
	@Query("SELECT m FROM Movimiento m WHERE m.cuentaUsuario.id = ?1")
	public List<Movimiento> findByCuentaUsuario(Long idUsuario);
	
	public List<Movimiento> findByEstado(String estado);
	
	@Query("select m from Movimiento m where m.cuentaBancariaUsuario.numerocuenta like :numeroCuentaBancaria")
	Page<Movimiento> obtenerListaxNumeroCuentaBancaria(String numeroCuentaBancaria, Pageable pageable);
	
}