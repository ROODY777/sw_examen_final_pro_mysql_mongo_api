package com.pi.factoring_backend.postgre.service;


import org.springframework.stereotype.Service;


import com.pi.factoring_backend.postgre.entity.CuentaBancariaPostgre;

@Service
public interface CuentaBancariaPostgreService {
	

	
	public void registrar(CuentaBancariaPostgre t);
}


