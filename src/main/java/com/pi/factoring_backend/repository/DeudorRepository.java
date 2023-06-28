package com.pi.factoring_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pi.factoring_backend.entity.Banco;
import com.pi.factoring_backend.entity.CargoEmpresaDeudor;
import com.pi.factoring_backend.entity.Deudor;

public interface DeudorRepository extends JpaRepository<Deudor, Long>{

	
	@Query("from CargoEmpresaDeudor")
	public List<CargoEmpresaDeudor> findAllCargoEmpresaDeudor();
	
}
