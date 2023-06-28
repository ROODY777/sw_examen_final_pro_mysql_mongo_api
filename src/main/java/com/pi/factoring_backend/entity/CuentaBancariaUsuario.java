package com.pi.factoring_backend.entity;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuentabancariausuario")
public class CuentaBancariaUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcuentabancariausuario")
	private int id;
	
	@Column(name = "numerocuenta", length = 100, nullable = false)
	private String numerocuenta;
	
	@Column(name = "cci", length = 100, nullable = false)
	private String cci;
	
	@Column(name = "archivo", length = 100, nullable = true)
	private String archivo;
	
	@ManyToOne
	@JoinColumn(name = "idcuentausuario", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadnler"})
	private CuentaUsuario cuentaUsuario;
	
	

	@Column(name = "banco", length = 100, nullable = true)
	private String banco;
	
	@Column(name = "moneda", length = 100, nullable = true)
	private String moneda;
	
	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getbanco() {
		return banco;
	}

	public void setbanco(String banco) {
		this.banco = banco;
	}

	@ManyToOne
	@JoinColumn(name = "idtipocuentabancaria", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadnler"})
	private TipoCuentaBancaria tipoCuentaBancaria;
	
	/*@ManyToOne
	@JoinColumn(name = "idmoneda", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadnler"})
	private Moneda moneda;*/

	@OneToMany(mappedBy = "cuentaBancariaUsuario")
	@JsonIgnore
	private List<Movimiento> movimientos = new ArrayList<>();

	public CuentaBancariaUsuario() {
		
	}

	public CuentaBancariaUsuario(int id, String numerocuenta, String cci, String archivo, CuentaUsuario cuentaUsuario,
			String banco,
			String moneda,
			TipoCuentaBancaria tipoCuentaBancaria, 
		//	Moneda moneda,
			List<Movimiento> movimientos) {
		this.id = id;
		this.numerocuenta = numerocuenta;
		this.cci = cci;
		this.archivo = archivo;
		this.cuentaUsuario = cuentaUsuario;
		this.banco = banco;
		this.moneda = moneda;
		this.tipoCuentaBancaria = tipoCuentaBancaria;
		//this.moneda = moneda;
		this.movimientos = movimientos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumerocuenta() {
		return numerocuenta;
	}

	public void setNumerocuenta(String numerocuenta) {
		this.numerocuenta = numerocuenta;
	}

	public String getCci() {
		return cci;
	}

	public void setCci(String cci) {
		this.cci = cci;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public CuentaUsuario getCuentaUsuario() {
		return cuentaUsuario;
	}

	public void setCuentaUsuario(CuentaUsuario cuentaUsuario) {
		this.cuentaUsuario = cuentaUsuario;
	}

	

	
	public TipoCuentaBancaria getTipoCuentaBancaria() {
		return tipoCuentaBancaria;
	}

	public void setTipoCuentaBancaria(TipoCuentaBancaria tipoCuentaBancaria) {
		this.tipoCuentaBancaria = tipoCuentaBancaria;
	}
/*
	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}*/
	
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
}
