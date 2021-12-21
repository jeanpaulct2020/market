package com.mycompany.market.security.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.market.domain.dto.Mensaje;
import com.mycompany.market.persistence.entities.Rol;
import com.mycompany.market.persistence.entities.Usuario;
import com.mycompany.market.security.dto.JwtDto;
import com.mycompany.market.security.dto.LoginUsuario;
import com.mycompany.market.security.dto.NuevoUsuario;
import com.mycompany.market.security.jwt.JwtProvider;
import com.mycompany.market.security.service.RolService;
import com.mycompany.market.security.service.UsuarioService;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@PostMapping("/new")
	public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			new ResponseEntity<>(new Mensaje("Uno o varios campos ingresados de forma incorrecta"), HttpStatus.BAD_REQUEST);
		}
		
		if(usuarioService.existsByNombreUsuaio(nuevoUsuario.getNombreUsuario())) {
			new ResponseEntity<>(new Mensaje("El usuario ingresado ya existe"), HttpStatus.BAD_REQUEST);
		}
		if(usuarioService.existsByCorreo(nuevoUsuario.getCorreo())) {
			new ResponseEntity<>(new Mensaje("El usuario ingresado ya existe"), HttpStatus.BAD_REQUEST);
		}
		 
		Usuario usuario = new Usuario(nuevoUsuario.getIdUsuario(), nuevoUsuario.getNombre(), nuevoUsuario.getApellido(),
				nuevoUsuario.getFechaNacimiento(), nuevoUsuario.getCorreo(), nuevoUsuario.getNombreUsuario(), passwordEncoder.encode(nuevoUsuario.getPassword()),
				nuevoUsuario.getEstado());
		
		List<Rol> roles = new ArrayList<>();
		nuevoUsuario.getRoles().forEach(r -> roles.add(rolService.getByRolNombre(r).get()));
	
		usuario.setRoles(roles);
		
		//Registramos el usuario
		usuarioService.save(usuario);
		
		return new ResponseEntity<>(new Mensaje("Usuario Registrado correctamente"), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			new ResponseEntity<>(new Mensaje("Usuario y/o contrasena incorrectos"), HttpStatus.BAD_REQUEST);
		}
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())); //new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication); // generamos el token
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		
		JwtDto jwtDto = new JwtDto(token, userDetails.getUsername(), userDetails.getAuthorities());
		
		return new ResponseEntity<>( jwtDto, HttpStatus.OK);
	}
}
