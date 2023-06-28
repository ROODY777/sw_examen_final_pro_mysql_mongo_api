package com.pi.factoring_backend.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuentausuario")
public class CuentaUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcuentausuario")
	private Long id;
	
	@Column(name = "nombres", length = 100)
	private String nombres;
	
	@Column(name = "apellidopaterno", length = 100)
	private String apellidoPaterno;
	
	@Column(name = "apellidomaterno", length = 100)
	private String apellidoMaterno;
	
	@Column(name = "direccion", length = 100)
	private String direccion;
	
	@Column(name = "ruc", length = 11)
	private String ruc;
	
	@Column(name = "razonsocial", length = 100)
	private String razonSocial;
	
	@Column(name = "actividadcomercial", length = 100)
	private String actividadcomercial;
	
	@Column(name = "telefono", length = 100)
	private String telefono;
	
	@Column(name = "descripcion", length = 255)
	private String descripcion;
	
	@Column(name = "email", length = 100, unique = true, nullable = false)
	private String email;
	
	@Column(name = "contraseña", length = 100, nullable = false)
	private String contrasena;
	
	@OneToMany(mappedBy = "cuentaUsuario", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Cartera> carteras = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "cuenta_tipo_usuario",
			joinColumns = @JoinColumn(name = "idcuentausuario", referencedColumnName = "idcuentausuario"),
			inverseJoinColumns = @JoinColumn(name = "idtipoperfilusuario", referencedColumnName = "idtipoperfil"))
	private Set<TipoPerfilUsuario> tiposPerfilesUsuario = new HashSet<TipoPerfilUsuario>();
	
	@OneToMany(mappedBy = "cuentaUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<CuentaBancariaUsuario> cuentasBancariasUsuarios = new HashSet<CuentaBancariaUsuario>();

	@OneToMany(mappedBy = "cuentaUsuario", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Movimiento> movimientos = new ArrayList<>();
	
	@OneToMany(mappedBy = "cuentaUsuario", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Subasta> subastas = new ArrayList<>();

	public CuentaUsuario() {
		
	}

	public CuentaUsuario(Long id, String nombres, String apellidoPaterno, String apellidoMaterno, String direccion,
			String ruc, String telefono, String descripcion, String email, String contrasena, List<Cartera> carteras,
			Set<TipoPerfilUsuario> tiposPerfilesUsuario, Set<CuentaBancariaUsuario> cuentasBancariasUsuarios, 
			List<Movimiento> movimientos, List<Subasta> subastas) {
		this.id = id;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.direccion = direccion;
		this.ruc = ruc;
		this.telefono = telefono;
		this.descripcion = descripcion;
		this.email = email;
		this.contrasena = contrasena;
		this.carteras = carteras;
		this.tiposPerfilesUsuario = tiposPerfilesUsuario;
		this.cuentasBancariasUsuarios = cuentasBancariasUsuarios;
		this.movimientos = movimientos;
		this.subastas = subastas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}	

	public List<Cartera> getCarteras() {
		return carteras;
	}

	public void setCarteras(List<Cartera> carteras) {
		this.carteras = carteras;
	}

	public Set<TipoPerfilUsuario> getTiposPerfilesUsuario() {
		return tiposPerfilesUsuario;
	}

	public void setTiposPerfilesUsuario(Set<TipoPerfilUsuario> tiposPerfilesUsuario) {
		this.tiposPerfilesUsuario = tiposPerfilesUsuario;
	}

	public Set<CuentaBancariaUsuario> getCuentasBancariasUsuarios() {
		return cuentasBancariasUsuarios;
	}

	public void setCuentasBancariasUsuarios(Set<CuentaBancariaUsuario> cuentasBancariasUsuarios) {
		this.cuentasBancariasUsuarios = cuentasBancariasUsuarios;
	}
	
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getActividadcomercial() {
		return actividadcomercial;
	}

	public void setActividadcomercial(String actividadcomercial) {
		this.actividadcomercial = actividadcomercial;
	}

	public List<Subasta> getSubastas() {
		return subastas;
	}

	public void setSubastas(List<Subasta> subastas) {
		this.subastas = subastas;
	}	
	
}
