package com.pi.factoring_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pi.factoring_backend.entity.Cartera;
import com.pi.factoring_backend.entity.Moneda;

public interface CarteraRepository extends JpaRepository<Cartera, Integer> {

	@Query("SELECT c FROM Cartera c WHERE c.cuentaUsuario.id= :idUsuario")
	List<Cartera> findByCuentaUsuario(@Param("idUsuario") Long id);
	
	@Query("SELECT c FROM Cartera c WHERE c.cuentaUsuario.id = :idUsuario AND c.moneda.descripcion =:moneda")
	Cartera findByUsuarioMoneda(@Param("idUsuario") Long id, String moneda);
	
	@Query("SELECT m FROM Cartera c JOIN c.moneda m")
	List<Moneda> findAllMonedas();
}
