package com.mycompany.market.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mycompany.market.persistence.entities.Rol;
import com.mycompany.market.security.service.RolService;



@Component
public class CrearRoles implements CommandLineRunner{

	@Autowired
	private RolService rolService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*
		Rol rolUser = new Rol(null, "ROLE_USER", "Usuario");//ADMINISTRADOR DEL SISTEMA
		Rol rolManager = new Rol(null, "ROLE_MANAGER","Manager" ); // VENDEDOR
		Rol rolAdmin = new Rol(null, "ROLE_ADMIN", "Administrador"); // JEFE DE ABASTECIMIENTO
		Rol rolSuperAdmin = new Rol(null, "ROLE_SUPER_ADMIN", "Super administrador"); // JEFE DE FACTURACION 
		Rol rolVentas = new Rol(null, "ROLE_VENTAS", "Usuario de ventas"); // GERENTE GENERAL
		
		rolService.save(rolUser);
		rolService.save(rolManager);
		rolService.save(rolAdmin);
		rolService.save(rolSuperAdmin);
		rolService.save(rolVentas);*/
		
	}
	
}
