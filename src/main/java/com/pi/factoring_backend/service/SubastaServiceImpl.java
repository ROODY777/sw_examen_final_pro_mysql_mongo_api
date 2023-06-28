package com.pi.factoring_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.factoring_backend.entity.Subasta;

import com.pi.factoring_backend.repository.SubastaRepository;

@Service
public class SubastaServiceImpl implements SubastaService {

	@Autowired
	private SubastaRepository repository;
	
	
	@Override
	public Subasta registrarSubasta(Subasta subasta) {
		return repository.save(subasta);
	}

	@Override
	public List<Subasta> listarSubasta() {
		return repository.findAll();
	}

	@Override
	public Subasta findById(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void eliminarSubasta(int id) {
		repository.deleteById(id);
	}

	@Override
	public Subasta actualizarSubasta(Subasta subasta) {
		return repository.save(subasta);
	}

}
