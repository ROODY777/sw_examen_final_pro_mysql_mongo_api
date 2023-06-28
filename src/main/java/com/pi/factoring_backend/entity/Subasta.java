package com.pi.factoring_backend.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "subasta")
public class Subasta {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsubasta")
	private int id;

	@Column(name = "riesgo", nullable = false)
	private String riesgo;
	
	@Column(name = "fechapago", nullable = false)
	private Date fechapago;
	
	@Column(name = "fechaemision", nullable = false)
	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Lima")
	private LocalDateTime fechaemision;
	
	@Column(name = "montototal", nullable = false)
	private double montototal;
	
	@Column(name = "montopendiente", nullable = false)
	private double montopendiente;
	
	@Column(name = "tiposubasta", nullable = false)
	private String tiposubasta;
	
	@Column(name = "retorno", nullable = false)
	private double retorno;

	//ROODY COMENTE 07-06-2023
	//@OneToMany(mappedBy = "subasta", cascade = CascadeType.ALL)
	//@JsonIgnore
	//private List<Factura> facturas = new ArrayList<>();

	    //factura
		//@JsonIgnoreProperties para que no aparesca json infinito  
		@JsonIgnoreProperties({"subasta","hibernateLazyInitializer","hadnler"})
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "subasta",cascade=CascadeType.ALL )
		private List<Factura> facturas;
	
	
		  //inversion
		//@JsonIgnoreProperties para que no aparesca json infinito  
     	@JsonIgnoreProperties({"subasta","hibernateLazyInitializer","hadnler"})
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "subasta",cascade=CascadeType.ALL )
		private List<Inversion> inversiones;
	
	
	//@OneToMany(mappedBy = "subasta", cascade = CascadeType.ALL)
	//@JsonIgnore
	//private List<Inversion> inversiones = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "idcuentausuario", nullable = false)
	private CuentaUsuario cuentaUsuario;
	
	public Subasta() {
		super();
	}

	public Subasta(int id, String riesgo, Date fechapago, LocalDateTime fechaemision, double montototal,
			double montopendiente, String tiposubasta, List<Factura> facturas, List<Inversion> inversiones,
			double retorno, CuentaUsuario cuentaUsuario) {
		super();
		this.id = id;
		this.riesgo = riesgo;
		this.fechapago = fechapago;
		this.fechaemision = fechaemision;
		this.montototal = montototal;
		this.montopendiente = montopendiente;
		this.tiposubasta = tiposubasta;
		this.facturas = facturas;
		this.inversiones = inversiones;
		this.retorno = retorno;
		this.cuentaUsuario = cuentaUsuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRiesgo() {
		return riesgo;
	}

	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}

	public Date getFechapago() {
		return fechapago;
	}

	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}

	public LocalDateTime getFechaemision() {
		return fechaemision;
	}

	public void setFechaemision(LocalDateTime fechaemision) {
		this.fechaemision = fechaemision;
	}

	public double getMontototal() {
		return montototal;
	}

	public void setMontototal(double montototal) {
		this.montototal = montototal;
	}

	public double getMontopendiente() {
		return montopendiente;
	}

	public void setMontopendiente(double montopendiente) {
		this.montopendiente = montopendiente;
	}

	public String getTiposubasta() {
		return tiposubasta;
	}

	public void setTiposubasta(String tiposubasta) {
		this.tiposubasta = tiposubasta;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public List<Inversion> getInversiones() {
		return inversiones;
	}

	public void setInversiones(List<Inversion> inversiones) {
		this.inversiones = inversiones;
	}

	public double getRetorno() {
		return retorno;
	}

	public void setRetorno(double retorno) {
		this.retorno = retorno;
	}

	public CuentaUsuario getCuentaUsuario() {
		return cuentaUsuario;
	}

	public void setCuentaUsuario(CuentaUsuario cuentaUsuario) {
		this.cuentaUsuario = cuentaUsuario;
	}
	
}
