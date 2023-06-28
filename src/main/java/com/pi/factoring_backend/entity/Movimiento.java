package com.pi.factoring_backend.entity;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Movimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "monto", nullable = false)
	private double monto;
	
	@Column(name = "tipo", nullable = false)
	private String tipo;
	
	@Column(name = "moneda", nullable = false)
	private String moneda;	
	
	@Column(name = "fechacreacion")
	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Lima")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "estado", nullable = false)
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "idcuentausuario", nullable = false)
	private CuentaUsuario cuentaUsuario;
	
	@ManyToOne
	@JoinColumn(name = "idcuentabancariausuario", nullable = false)
	private CuentaBancariaUsuario cuentaBancariaUsuario;

	public Movimiento() {
		super();
	}

	public Movimiento(int id, double monto, String tipo, String moneda,String estado,
			CuentaUsuario cuentaUsuario, CuentaBancariaUsuario cuentaBancariaUsuario) {
		super();
		this.id = id;
		this.monto = monto;
		this.tipo = tipo;
		this.moneda = moneda;
		this.estado = estado;
		this.cuentaUsuario = cuentaUsuario;
		this.cuentaBancariaUsuario = cuentaBancariaUsuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public CuentaUsuario getCuentaUsuario() {
		return cuentaUsuario;
	}

	public void setCuentaUsuario(CuentaUsuario cuentaUsuario) {
		this.cuentaUsuario = cuentaUsuario;
	}

	public CuentaBancariaUsuario getCuentaBancariaUsuario() {
		return cuentaBancariaUsuario;
	}

	public void setCuentaBancariaUsuario(CuentaBancariaUsuario cuentaBancariaUsuario) {
		this.cuentaBancariaUsuario = cuentaBancariaUsuario;
	}
	
	
}
