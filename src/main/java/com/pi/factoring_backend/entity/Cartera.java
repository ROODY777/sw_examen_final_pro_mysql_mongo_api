package com.pi.factoring_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cartera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idcuentausuario")
	private CuentaUsuario cuentaUsuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idmoneda")
	private Moneda moneda;
	
	@Column(name = "cantidad")
	private double cantidad;

	public Cartera() {
		super();
	}

	public Cartera(int id, CuentaUsuario cuentaUsuario, Moneda moneda, double cantidad) {
		super();
		this.id = id;
		this.cuentaUsuario = cuentaUsuario;
		this.moneda = moneda;
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CuentaUsuario getCuentaUsuario() {
		return cuentaUsuario;
	}

	public void setCuentaUsuario(CuentaUsuario cuentaUsuario) {
		this.cuentaUsuario = cuentaUsuario;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
