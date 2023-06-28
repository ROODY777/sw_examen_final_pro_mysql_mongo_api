package com.pi.factoring_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.pi.factoring_backend.entity.Banco;
import com.pi.factoring_backend.entity.CuentaBancariaUsuario;
import com.pi.factoring_backend.entity.Moneda;
import com.pi.factoring_backend.entity.TipoCuentaBancaria;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancariaUsuario, Long>{
	
	@Query("SELECT c FROM CuentaBancariaUsuario c WHERE c.cuentaUsuario.id = :idUsuario")
	List<CuentaBancariaUsuario> findbyCuentaUsuarioId(@Param("idUsuario") Long id);
	
	
	//ROODY COMENTE 27/06/2023
		/*
	@Query("SELECT c FROM CuentaBancariaUsuario c WHERE c.cuentaUsuario.id = :idUsuario AND c.moneda.descripcion= :moneda")
	CuentaBancariaUsuario finyByCuentaMoneda(@Param("idUsuario") Long id, @Param("moneda") String moneda);
	*/
	
	//ROODY COMENTE 26/06/2023
	/*@Query("SELECT c FROM CuentaBancariaUsuario c JOIN c.cuentaUsuario u JOIN u.tiposPerfilesUsuario p WHERE p.nombre = 'admin' "
			+ "AND c.banco.descripcion= :banco AND c.moneda.descripcion= :moneda")
	CuentaBancariaUsuario findByAdmin(@Param("banco") String banco, @Param("moneda") String moneda );
*/
	
	
	
	//CuentaBancariaUsuario findbyCuentaUsuarioId(@Param("idUsuario") Long id);

	
	//roody
	//CuentaBancariaUsuario findbyCuentaUsuarioId(@Param("idUsuario") Long id);

	
	
	
	//combo box
	@Query("from Banco")
	public List<Banco> findAllBancos();
	
	@Query("from Moneda")
	public List<Moneda> findAllMonedas();
	
	@Query("from TipoCuentaBancaria")
	public List<TipoCuentaBancaria> findAllTipoCuentaBancaria();
	

}
