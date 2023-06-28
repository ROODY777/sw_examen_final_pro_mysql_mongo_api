package com.pi.factoring_backend.postgre.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pi.factoring_backend.postgre.entity.CuentaBancariaPostgre;
import com.pi.factoring_backend.postgre.repo.CuentaBancariaPostgreRepository;

@Service
public class CuentaBancariaPostgreServiceImpl implements CuentaBancariaPostgreService{

	@Autowired
	private CuentaBancariaPostgreRepository repository;
	
	
	@Override
	public void registrar(CuentaBancariaPostgre t) {
	
		 repository.save(t);
	}

}
