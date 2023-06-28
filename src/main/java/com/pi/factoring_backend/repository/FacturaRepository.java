package com.pi.factoring_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pi.factoring_backend.entity.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long>{
	
//	@Query("select f from Factura f where f.cuentausuario.id = ?1")
//	List<Factura> findByUsuario(Long idusuario);
}
