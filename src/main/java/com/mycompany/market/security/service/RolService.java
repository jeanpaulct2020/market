package com.mycompany.market.security.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.market.persistence.entities.Rol;
import com.mycompany.market.security.repository.IRolRepository;

@Service("rolService")
@Transactional
public class RolService {

	@Autowired
	private IRolRepository iRolRepository;
	
	public Optional<Rol> getByRolNombre(String nombre){
		return iRolRepository.findByNombre(nombre);
	}
	
	public void save(Rol rol) {
		iRolRepository.save(rol);
	}
}
