package com.mycompany.market.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "roles") //uniqueConstraints = {@UniqueConstraint(columnNames = {"id_usuario", "rol"}) } )
public class Rol implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Integer idRol;
	
	@Column(name = "rol")
	private String nombre;	
	
	private String descripcion;

	public Rol() {
		super();
	}

	
	public Rol(Integer idRol, String nombre, String descripcion) {
		super();
		this.idRol = idRol;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}


	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	
	
}
