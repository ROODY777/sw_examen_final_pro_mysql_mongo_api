package com.pi.factoring_backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.pi.factoring_backend.entity.Inversion;

public interface InversionRepository extends JpaRepository<Inversion, Integer>{
	
	//ROODY 1/06/2023
	@Query("SELECT c FROM Inversion c WHERE c.cuentaUsuario.id = :idUsuario")
	Page<Inversion> findInversionXUsuarioId(@Param("idUsuario") Long id, Pageable pageable);
	
	@Query("select x from Inversion x where (x.cuentaUsuario.id = ?1) and (x.estado=?2) ")  
	//@Query("select x from Inversion x where  (?1 is '' or x.estado=?1) ")       
	public List<Inversion> listaInversionesListaXIdCuentaUsuarioYXEstadoLike(Long idcuentaUsuario,  String estado);


	//ROODY 1/06/2023
		@Query("SELECT c FROM Inversion c WHERE c.cuentaUsuario.id = :idUsuario")
		List<Inversion> findInversionXUsuarioId(@Param("idUsuario") Long id);
		
}
