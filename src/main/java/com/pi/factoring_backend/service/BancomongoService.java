package com.pi.factoring_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pi.factoring_backend.entity.Bancomongo;
import com.pi.factoring_backend.repository.BancomongoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BancomongoService {

	private final BancomongoRepository bancoRepository;

	
	
	public void save(Bancomongo banco) {
		bancoRepository.save(banco);
	}
	
	public Bancomongo registrarCuenta(Bancomongo bancos) {
		return bancoRepository.save(bancos);
	}
	
	public List<Bancomongo> findAll() {
		return bancoRepository.findAll();
	}
	
	public Optional<Bancomongo> findById(Long id) {
		return bancoRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		bancoRepository.deleteById(id);
	}
	
	
}
