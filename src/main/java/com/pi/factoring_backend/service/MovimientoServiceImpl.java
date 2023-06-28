package com.pi.factoring_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pi.factoring_backend.entity.Movimiento;
import com.pi.factoring_backend.repository.MovimientoRepository;

@Service
public class MovimientoServiceImpl implements MovimientoService{
	
	@Autowired
	private MovimientoRepository movimientoRepository;
	
	@Override
	public List<Movimiento> listarMovimientos() {
		return movimientoRepository.findAll();
	}

	@Override
	public List<Movimiento> listarMovimientosPorUsuario(Long id) {
		return movimientoRepository.findByCuentaUsuario(id);
	}

	@Override
	public Movimiento registrarMovimiento(Movimiento movimiento) {
		return movimientoRepository.save(movimiento);
	}

	@Override
	public List<Movimiento> listarMovimientosPorUsuarioYEstado(Long id, String estado) {
		return movimientoRepository.findByCuentaUsuarioAndEstado(id, estado);
	}
	
	@Override
	public Page<Movimiento> listarxNumeroCuentaBancaria(String numeroCuentaBancaria, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Movimiento> movimientos = movimientoRepository.obtenerListaxNumeroCuentaBancaria(numeroCuentaBancaria, pageable);
		
		return movimientos;
	}

	@Override
	public Movimiento buscarPorId(int id) {
		return movimientoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(String.format("No se encontró el movimiento id: %s", id)));
	}

	@Override
	public Movimiento actualizarEstado(Movimiento movimiento) {
		return movimientoRepository.save(movimiento);
	}

	@Override
	public List<Movimiento> listarMovimientosPorEstado(String estado) {
		return movimientoRepository.findByEstado(estado);
	}

}
