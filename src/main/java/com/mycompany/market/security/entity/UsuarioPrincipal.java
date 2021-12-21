package com.mycompany.market.security.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mycompany.market.persistence.entities.Compra;
import com.mycompany.market.persistence.entities.Rol;
import com.mycompany.market.persistence.entities.Usuario;

public class UsuarioPrincipal implements UserDetails{

	private String idUsuario;
	
	private String nombre;
	
	private String apellido;
	
	private LocalDate fechaNacimiento;
	
	private String correo;
	
	private String nombreUsuario;
	
	private String password;
	
	private Boolean estado = true;
		
	private Collection<? extends GrantedAuthority> authorities;
	
	

	public UsuarioPrincipal(String idUsuario, String nombre, String apellido, LocalDate fechaNacimiento, String correo,
			String nombreUsuario, String password, Boolean estado, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.estado = estado;
		this.authorities = authorities;
	}
	
	public static UsuarioPrincipal build(Usuario usuario) {
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map( r -> new SimpleGrantedAuthority(r.getNombre())).collect(Collectors.toList());
		
		return new UsuarioPrincipal(usuario.getIdUsuario(), usuario.getNombre(), usuario.getApellido(), usuario.getFechaNacimiento(), usuario.getCorreo(), usuario.getNombreUsuario(),
				usuario.getClave(), usuario.getEstado(), authorities);		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nombreUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return estado;
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
	
	
}
