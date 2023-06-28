package com.pi.factoring_backend.postgre.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="cuentabancariapostgre" )
public class CuentaBancariaPostgre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idcuentabancariausuario;
	
	private String cci;
	private String numerocuenta;
	
	
	public Long getIdcuentabancariausuario() {
		return idcuentabancariausuario;
	}
	public void setIdcuentabancariausuario(Long idcuentabancariausuario) {
		this.idcuentabancariausuario = idcuentabancariausuario;
	}
	public String getCci() {
		return cci;
	}
	public void setCci(String cci) {
		this.cci = cci;
	}
	public String getNumerocuenta() {
		return numerocuenta;
	}
	public void setNumerocuenta(String numerocuenta) {
		this.numerocuenta = numerocuenta;
	}
}
