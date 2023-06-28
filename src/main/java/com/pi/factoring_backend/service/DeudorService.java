package com.pi.factoring_backend.service;


import java.util.List;

import com.pi.factoring_backend.entity.CargoEmpresaDeudor;
import com.pi.factoring_backend.entity.CuentaBancariaUsuario;
import com.pi.factoring_backend.entity.Deudor;
import com.pi.factoring_backend.entity.Moneda;

public interface DeudorService {

	
	
	public Deudor registrarDeudor(Deudor deudor);
	
	public List<Deudor> listarDeudor();
	
	public Deudor findById(Long id);
	
	public void eliminarDeudor(Long id);
	
	
	//combo box roody
	public List<CargoEmpresaDeudor> findAllcargoEmpresaDeudor();
	
}
