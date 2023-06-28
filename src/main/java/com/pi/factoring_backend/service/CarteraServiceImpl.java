package com.pi.factoring_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.factoring_backend.entity.Cartera;
import com.pi.factoring_backend.entity.Moneda;
import com.pi.factoring_backend.repository.CarteraRepository;
import com.pi.factoring_backend.repository.MonedaRepository;

@Service
public class CarteraServiceImpl implements CarteraService{

	@Autowired
	private CarteraRepository repository;
	
	@Autowired
	private MonedaRepository monedaRepository;
	
	@Override
	public Cartera registrarCartera(Cartera cartera) {
		return repository.save(cartera);
	}

	@Override
	public List<Cartera> obtenerPorUsuario(Long id) {
		return repository.findByCuentaUsuario(id);
	}

	@Override
	public Cartera obtenerPorId(Long id, String moneda) {
		return repository.findByUsuarioMoneda(id, moneda);				
	}

	@Override
	public Cartera actualizarCantidad(Cartera cartera) {
		return repository.save(cartera);
	}

	@Override
	public List<Moneda> obtenerMonedas() {
		return monedaRepository.findAll();
	}

}
