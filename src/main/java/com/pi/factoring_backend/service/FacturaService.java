package com.pi.factoring_backend.service;

import java.sql.SQLException;
import java.util.List;

import com.pi.factoring_backend.entity.Factura;

public interface FacturaService {
	Factura registrar(Long idempresa, Factura factura) throws SQLException;
	
	public List<Factura> registroFacturas(List<Factura> facturas);
	
	public List<Factura> listaFacturas();
}
