package com.pi.factoring_backend.service;

import java.util.List;


import com.pi.factoring_backend.entity.Banco;
import com.pi.factoring_backend.entity.CuentaBancariaUsuario;
import com.pi.factoring_backend.entity.Moneda;
import com.pi.factoring_backend.entity.TipoCuentaBancaria;

public interface CuentaBancariaService {
	public List<CuentaBancariaUsuario> listarCuentas();
	
	public CuentaBancariaUsuario registrarCuenta(CuentaBancariaUsuario cuentaBancaria);
	
	public void eliminarCuenta(Long id);
	


	public List<CuentaBancariaUsuario> buscarPorUsuario(Long id);
	
	//public CuentaBancariaUsuario buscarPorAdmin(String banco, String moneda);
	
	//public CuentaBancariaUsuario buscarPorUsuarioMoneda(Long id, String moneda);
	
	//CuentaBancariaUsuario buscarPorNumeroCuenta(String numeroCuenta);

	//public CuentaBancariaUsuario buscarPorUsuario(Long id);

	public List<CuentaBancariaUsuario> findbyCuentaUsuarioId(Long id);

	
	//
	public CuentaBancariaUsuario findById(Long id);
	
	//combo box
	public List<Banco> findAllBancos();
	public List<Moneda> findAllMonedas();
	public List<TipoCuentaBancaria> findAllTipoCuentaBancaria();

}

