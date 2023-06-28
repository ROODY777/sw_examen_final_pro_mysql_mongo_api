package com.pi.factoring_backend.service;

import java.util.List;

import com.pi.factoring_backend.entity.Cartera;
import com.pi.factoring_backend.entity.Moneda;

public interface CarteraService {
	public Cartera registrarCartera(Cartera cartera);
	
	public List<Moneda> obtenerMonedas();
	
	public List<Cartera> obtenerPorUsuario(Long id);
	
	public Cartera obtenerPorId(Long id, String moneda);
	
	public Cartera actualizarCantidad(Cartera cartera);
}
