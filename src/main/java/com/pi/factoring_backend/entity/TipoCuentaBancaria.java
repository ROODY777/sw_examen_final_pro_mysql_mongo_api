package com.pi.factoring_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipocuentabancaria")
public class TipoCuentaBancaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtipocuentabancaria")
	private Long id;
	
	@Column(name = "descripcion", unique = true, nullable = false)
	private String descripcion;
	
	public TipoCuentaBancaria() {
		
	}

	public TipoCuentaBancaria(Long id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
