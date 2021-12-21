package com.mycompany.market.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.market.persistence.entities.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer>{

	Optional<Rol> findByNombre(String nombre);
}
