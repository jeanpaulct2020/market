package com.mycompany.market.security.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.market.persistence.entities.Usuario;
import com.mycompany.market.security.repository.IUsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
		return iUsuarioRepository.findByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByNombreUsuaio(String nombreUsuario) {
		return iUsuarioRepository.existsByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByCorreo(String correo) {
		return iUsuarioRepository.existsByCorreo(correo);
	}
	
	public void save(Usuario usuario) {
		iUsuarioRepository.save(usuario);
	}
}
