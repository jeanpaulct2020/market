package com.mycompany.market.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.market.persistence.entities.Cliente;

public interface IClienteCrudRepository extends JpaRepository<Cliente, String>{

	boolean existsById(String id);
	
}
