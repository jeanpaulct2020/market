package com.mycompany.market.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.market.persistence.entities.Usuario;
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, String>{


	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	
	boolean existsByNombreUsuario(String nombreUsuario);
	
	boolean  existsByCorreo(String correo);
}
