package com.pi.factoring_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.factoring_backend.entity.CargoEmpresaDeudor;
import com.pi.factoring_backend.entity.CuentaBancariaUsuario;
import com.pi.factoring_backend.entity.Deudor;
import com.pi.factoring_backend.entity.Moneda;
import com.pi.factoring_backend.repository.DeudorRepository;

@Service
public class DeudorServiceImpl implements DeudorService{

	@Autowired
	private DeudorRepository repository;
	
	
	@Override
	public Deudor registrarDeudor(Deudor deudor) {
		return repository.save(deudor);
	}

	@Override
	public List<Deudor> listarDeudor() {		
		return repository.findAll();
	}
	
	
	//roody
		@Override
		@Transactional(readOnly = true)
		public Deudor findById(Long id) {
			return repository.findById(id).orElse(null);
		}

		@Override
		public void eliminarDeudor(Long id) {
			repository.deleteById(id);
		}
		
		
		
		@Override
		@Transactional(readOnly = true)
		public List<CargoEmpresaDeudor> findAllcargoEmpresaDeudor() {
			
			return repository.findAllCargoEmpresaDeudor();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
