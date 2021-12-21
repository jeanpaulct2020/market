package com.mycompany.market.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.mycompany.market.persistence.entities.Rol;

@Component
public class CrearUsuarios implements CommandLineRunner{

	/*
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;*/
	
	/*
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	*/
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*Rol rol1 = new Rol();
		rol1.setIdRol(1);
		rol1.setRol("ROLE_ADMIN");
				
		List<Rol> roles = new ArrayList<>();		
		roles.add(rol1);
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario("72052729");
		usuario.setNombre("Jean Paul");
		usuario.setApellido("Cochachin Torres");
		LocalDate fecha = LocalDate.now();
		usuario.setFechaNacimiento(fecha);
		usuario.setCorreo("jeancocht@gmail.com");
		usuario.setUsuario("JCOCHACHIN");
		usuario.setClave(passwordEncoder.encode("admin"));
		
		usuario.setRoles(roles);
		
		usuario.setEstado(true);
		
		usuarioRepository.save(usuario);*/
		
		//List<Role> roles = new ArrayList<>(); 
		
		/*LocalDate date1 = LocalDate.of(1997, 12, 25);
		LocalDate date2 = LocalDate.of(1998, 2, 06);
		LocalDate date3 = LocalDate.of(1999, 12, 17);
		LocalDate date4 = LocalDate.of(1995, 4, 4);
		
		//User user1 = new User("72052729", "Jean Paul", "Cochachin Torres", "1997-12-25", "jeancocht@gmail.com", "jcochachin", "admin", true, roles);
		User user1 = new User("72052729", "Jean Paul", "Cochachin Torres", date1, "jeancocht@gmail.com", "jcochachin", passwordEncoder.encode("admin"), true, new ArrayList<>());
		User user2 = new User("72052730", "Mirella Deyanira", "Arunategui Rodriguez", date2, "mirelladeyanira@gmail.com", "marrunategui", passwordEncoder.encode("admin"), true, new ArrayList<>());
		User user3 = new User("72052731", "Jesus", "Arrunategui Rodriguez", date3, "jarrunategui@gmail.com", "jarrunategui", passwordEncoder.encode("admin"), true, new ArrayList<>());
		User user4 = new User("72052732", "Johanna", "Rodriguez Cerdan", date4, "johanna@gmail.com", "jrodriguez", passwordEncoder.encode("admin"), true, new ArrayList<>());
		
		userService.save(user1);
		userService.save(user2);
		userService.save(user3);
		userService.save(user4);*/
		
		
		//AGREGAR ROLES A USUARIOS
		/*
		userService.addRoleToUser("jcochachin", "ROLE_USER");
		userService.addRoleToUser("jcochachin", "ROLE_MANAGER");
		userService.addRoleToUser("marrunategui", "ROLE_ADMIN");
		userService.addRoleToUser("jarrunategui", "ROLE_ADMIN");
		userService.addRoleToUser("jarrunategui", "ROLE_SUPER_ADMIN");
		userService.addRoleToUser("jrodriguez", "ROLE_VENTAS");
		userService.addRoleToUser("jrodriguez", "ROLE_MANAGER");*/
		
		/*
		LocalDate date4 = LocalDate.of(1996, 4, 4);
		User user4 = new User("72052712", "Katherine", "Cochachin Torres", date4, "kcochachin@gmail.com", "kcochachin", passwordEncoder.encode("1234"), true, new ArrayList<>());
		userService.save(user4);
		userService.addRoleToUser("kcochachin", "ROLE_USER");*/
	}
	
	
	
}
