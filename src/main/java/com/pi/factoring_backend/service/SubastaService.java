package com.pi.factoring_backend.service;

import java.util.List;


import com.pi.factoring_backend.entity.Subasta;

public interface SubastaService {

	
	
public Subasta registrarSubasta(Subasta subasta);
	
	public List<Subasta> listarSubasta();
	
	public Subasta findById(int id);
	
	public void eliminarSubasta(int id);
	
	public Subasta actualizarSubasta(Subasta subasta);
	
}
