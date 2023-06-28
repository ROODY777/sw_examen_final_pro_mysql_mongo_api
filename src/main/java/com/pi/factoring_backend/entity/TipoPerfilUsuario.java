package com.pi.factoring_backend.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipoperfilusuario")
public class TipoPerfilUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtipoperfil")
	private Long id;
	
	@Column(name = "descripcion", unique = true, nullable = false)
	private String nombre;
	
	@ManyToMany(mappedBy = "tiposPerfilesUsuario")
	private Set<CuentaUsuario> cuentasUsuario = new HashSet<CuentaUsuario>();
	
	public TipoPerfilUsuario() {
		
	}
	
	public TipoPerfilUsuario(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
