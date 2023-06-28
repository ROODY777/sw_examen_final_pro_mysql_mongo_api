package com.pi.factoring_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.factoring_backend.entity.TipoPerfilUsuario;

public interface TipoPerfilUsuarioRepository extends JpaRepository<TipoPerfilUsuario, Long>{
	TipoPerfilUsuario findByNombre(String nombre);
}
