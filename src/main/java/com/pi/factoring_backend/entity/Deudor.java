package com.pi.factoring_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "deudor")
public class Deudor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddeudor")
	private Long id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "apellidos", nullable = false)
	private String apellidos;
	
	
	@Column(name = "correo", nullable = false)
	private String correo;
	
	
	@Column(name = "telefono", nullable = false)
	private String telefono;
	
	
	@ManyToOne
	@JoinColumn(name = "idcargoempresadeudor", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadnler"})
	private CargoEmpresaDeudor cargoEmpresaDeudor;


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


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public CargoEmpresaDeudor getCargoEmpresaDeudor() {
		return cargoEmpresaDeudor;
	}


	public void setCargoEmpresaDeudor(CargoEmpresaDeudor cargoEmpresaDeudor) {
		this.cargoEmpresaDeudor = cargoEmpresaDeudor;
	}
	
	
	//@Column(name = "idcargoempresadeudor")
	//private int idcargoempresadeudor;
	




	
	
	
	
	
	
}
