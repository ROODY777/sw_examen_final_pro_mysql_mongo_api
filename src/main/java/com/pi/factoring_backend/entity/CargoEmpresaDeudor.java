package com.pi.factoring_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargoempresadeudor")
public class CargoEmpresaDeudor {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcargoempresadeudor")
	private Long idcargoempresadeudor;
	
	@Column(name = "cargoempresa", unique = true, nullable = false)
	private String cargoempresa;

	public Long getIdcargoempresadeudor() {
		return idcargoempresadeudor;
	}

	public void setIdcargoempresadeudor(Long idcargoempresadeudor) {
		this.idcargoempresadeudor = idcargoempresadeudor;
	}

	public String getCargoempresa() {
		return cargoempresa;
	}

	public void setCargoempresa(String cargoempresa) {
		this.cargoempresa = cargoempresa;
	}


	
	
}
