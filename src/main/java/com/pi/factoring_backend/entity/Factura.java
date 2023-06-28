package com.pi.factoring_backend.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "factura")
public class Factura {
	
	@Id
	@Column(name = "idfactura", nullable = false)
	private String id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaemision", nullable = false)
	private Date fechaemision;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechapago", nullable = false)
	private Date fechapago;
	
	@Column(name = "monto", nullable = false)
	private double monto;
	
	@Column(name = "ruccliente")
	private String ruccliente;
	
	@Column(name = "razonsocialcliente")
	private String razonsocialcliente;
	
	@ManyToOne
	@JoinColumn(name = "idsubasta")
	private Subasta subasta;

	public Factura() {
		
	}

	public Factura(String id, Date fechaemision, Date fechapago, double monto, String ruccliente,
			String razonsocialcliente, Subasta subasta) {		
		this.id = id;
		this.fechaemision = fechaemision;
		this.fechapago = fechapago;
		this.monto = monto;
		this.ruccliente = ruccliente;
		this.razonsocialcliente = razonsocialcliente;
		this.subasta = subasta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaemision() {
		return fechaemision;
	}

	public void setFechaemision(Date fechaemision) {
		this.fechaemision = fechaemision;
	}

	public Date getFechapago() {
		return fechapago;
	}

	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getRuccliente() {
		return ruccliente;
	}

	public void setRuccliente(String ruccliente) {
		this.ruccliente = ruccliente;
	}

	public String getRazonsocialcliente() {
		return razonsocialcliente;
	}

	public void setRazonsocialcliente(String razonsocialcliente) {
		this.razonsocialcliente = razonsocialcliente;
	}

	public Subasta getSubasta() {
		return subasta;
	}

	public void setSubasta(Subasta subasta) {
		this.subasta = subasta;
	}
	
}
