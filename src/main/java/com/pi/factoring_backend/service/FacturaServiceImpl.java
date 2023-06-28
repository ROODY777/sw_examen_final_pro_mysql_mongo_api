package com.pi.factoring_backend.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.factoring_backend.entity.CuentaUsuario;
import com.pi.factoring_backend.entity.Factura;
import com.pi.factoring_backend.repository.CuentaUsuarioRepository;
import com.pi.factoring_backend.repository.FacturaRepository;
import com.pi.factoring_backend.util.RecursoNoEncontradoException;

@Service
public class FacturaServiceImpl implements FacturaService {
	
	@Autowired
	private CuentaUsuarioRepository cuentaUsuarioRepository;
	
	@Autowired
	private FacturaRepository facturaRepository;

	@Override
	public Factura registrar(Long idempresa, Factura factura)
			throws SQLException {
		
		CuentaUsuario cuentaUsuarioEncontrada = cuentaUsuarioRepository.findById(idempresa)
				.orElseThrow(() -> new RecursoNoEncontradoException("El recurso cuenta usuario no ha sido encontrada"));
		
		factura.setFechaemision(new Date());
//		factura.setCuentausuario(cuentaUsuarioEncontrada);
		
		return facturaRepository.save(factura);
	}

	@Override
	public List<Factura> registroFacturas(List<Factura> facturas) {
		return facturaRepository.saveAll(facturas);
	}

	@Override
	public List<Factura> listaFacturas() {
		return facturaRepository.findAll();
	}
	
}
