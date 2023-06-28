package com.pi.factoring_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.pi.factoring_backend.entity.Banco;
import com.pi.factoring_backend.entity.CuentaBancariaUsuario;
import com.pi.factoring_backend.entity.Moneda;
import com.pi.factoring_backend.entity.TipoCuentaBancaria;
import com.pi.factoring_backend.repository.CuentaBancariaRepository;
import com.pi.factoring_backend.util.RecursoNoEncontradoException;

@Service
public class CuentaBancariaServiceImpl implements CuentaBancariaService{
	
	@Autowired
	private CuentaBancariaRepository repository;

	@Override
	public List<CuentaBancariaUsuario> listarCuentas() {		
		return repository.findAll();
	}

	@Override
	public CuentaBancariaUsuario registrarCuenta(CuentaBancariaUsuario cuentaBancaria) {
		return repository.save(cuentaBancaria);
	}

	@Override
	public void eliminarCuenta(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<CuentaBancariaUsuario> buscarPorUsuario(Long id) {
		return repository.findbyCuentaUsuarioId(id);
	}


/*
	@Override
	public CuentaBancariaUsuario buscarPorAdmin(String banco, String moneda) {
		return repository.findByAdmin(banco, moneda);
	}*/

	/*
	@Override
	public CuentaBancariaUsuario buscarPorUsuarioMoneda(Long id, String moneda) {
		return repository.finyByCuentaMoneda(id, moneda);
	}
*/
	
	//roody
	@Override
	@Transactional(readOnly = true)
	public CuentaBancariaUsuario findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	
	
	//combo box
	@Override
	@Transactional(readOnly = true)
	public List<Banco> findAllBancos() {
		
		return repository.findAllBancos();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Moneda> findAllMonedas() {
		
		return repository.findAllMonedas();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoCuentaBancaria> findAllTipoCuentaBancaria() {
		
		return repository.findAllTipoCuentaBancaria();
	}

	//ROODY
	@Override
	@Transactional(readOnly = true)
	public List<CuentaBancariaUsuario> findbyCuentaUsuarioId(Long id) {
		return repository.findbyCuentaUsuarioId(id);
	}

	/*@Override
	public CuentaBancariaUsuario buscarPorNumeroCuenta(String numeroCuenta) {
		CuentaBancariaUsuario cuentabancaria = repository.findByNumerocuenta(numeroCuenta)
				.orElseThrow(() -> new RecursoNoEncontradoException("La cuenta bancaria no existe"));
		
		return cuentabancaria;
	}*/


}
