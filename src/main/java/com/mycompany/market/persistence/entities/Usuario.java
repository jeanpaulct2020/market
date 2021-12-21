package com.mycompany.market.persistence.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_usuario")
	@NotBlank
	private String idUsuario;
	
	@Column(name = "nombre")
	@NotBlank
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "fecha_nac")
	@NotBlank
	private LocalDate fechaNacimiento;
	
	@Column(name = "correo")
	@NotBlank
	private String correo;
	
	@Column(name = "usuario", length = 30, unique = true)
	@NotBlank
	private String nombreUsuario;
	
	@Column(name = "clave", length = 80)
	@NotBlank
	private String clave;
	
	@Column(name = "estado")
	@NotBlank
	private Boolean estado=true;
	
	@OneToMany(mappedBy = "cliente")
    private List<Compra> compras;
	
	//@OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
	//@JoinColumn(name = "id_usuario", insertable = false, updatable = false)
	@ManyToMany(fetch = FetchType.EAGER,  cascade = {CascadeType.ALL})
	@JoinTable(name="usuarios_roles", 
		joinColumns={@JoinColumn(name="usuario_id")},
		inverseJoinColumns={@JoinColumn(name="rol_id")}
	)
	private List<Rol> roles = new ArrayList<>();
	
	
	
	public Usuario() {
		super();
	}
	
	

	public Usuario(String idUsuario, String nombre, String apellido, LocalDate fechaNacimiento, String correo,
			String nombreUsuario, String clave, Boolean estado, List<Rol> roles) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
		this.estado = estado;
		this.roles = roles;
	}

	

	public Usuario(String idUsuario, String nombre, String apellido, LocalDate fechaNacimiento, String correo,
			String nombreUsuario, String clave, Boolean estado) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
		this.estado = estado;
	}



	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}



	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fechaNacimiento=" + fechaNacimiento + ", correo=" + correo + ", nombreUsuario=" + nombreUsuario
				+ ", clave=" + clave + ", estado=" + estado + ", compras=" + compras + ", roles=" + roles + "]";
	}
	
	
	
}
