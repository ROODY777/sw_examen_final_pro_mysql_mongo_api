package com.pi.factoring_backend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inversion")
public class Inversion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idinversion")
	private Long id;
	
	@Column(name = "fecha")
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Lima")
	private LocalDateTime fecha;
	
	@Column(name = "monto", nullable = false)
	private double monto;

	@Column(name = "porcentaje", nullable = false)
	private double porcentaje;
	
	@Column(name = "ganancia", nullable = false)
	private double ganancia;
	
	@Column(name = "retornomensual", nullable = false)
	private double retornomensual;
	
	@Column(name = "estado", nullable = false)
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "idcuentausuario", nullable = false)
	private CuentaUsuario cuentaUsuario;
	
	@ManyToOne
	@JoinColumn(name = "idsubasta", nullable = false)
	private Subasta subasta;
	
	public Inversion() {
		
	}

	public Inversion(Long id, LocalDateTime fecha, double monto, double porcentaje, double ganancia,
			double retornomensual, CuentaUsuario cuentaUsuario, Subasta subasta) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.monto = monto;
		this.porcentaje = porcentaje;
		this.ganancia = ganancia;
		this.retornomensual = retornomensual;
		this.cuentaUsuario = cuentaUsuario;
		this.subasta = subasta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}	

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public double getGanancia() {
		return ganancia;
	}

	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}

	public double getRetornomensual() {
		return retornomensual;
	}

	public void setRetornomensual(double retornomensual) {
		this.retornomensual = retornomensual;
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

	public Subasta getSubasta() {
		return subasta;
	}

	public void setSubasta(Subasta subasta) {
		this.subasta = subasta;
	}

}
