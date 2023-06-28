package com.pi.factoring_backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pi.factoring_backend.entity.Movimiento;

public interface MovimientoService {
	
	public List<Movimiento> listarMovimientos();
	
	public List<Movimiento> listarMovimientosPorUsuario(Long id);
	
	public List<Movimiento> listarMovimientosPorUsuarioYEstado(Long id, String estado);
	
	public List<Movimiento> listarMovimientosPorEstado(String estado);
	
	Page<Movimiento> listarxNumeroCuentaBancaria(String numeroCuentaBancaria, Integer page, Integer size);
	
	public Movimiento registrarMovimiento(Movimiento movimiento);
	
	public Movimiento buscarPorId(int id);
	
	public Movimiento actualizarEstado(Movimiento movimiento);
	
	
}
 