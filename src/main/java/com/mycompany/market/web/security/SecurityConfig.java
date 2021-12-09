package com.mycompany.market.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mycompany.market.domain.service.MarketUserDetailService;
import com.mycompany.market.web.security.filter.JwtFilterRequest;

/*Gestionar la seguridad*/
@EnableWebSecurity //Notacion que indica que esta clase estara encargada de la seguridad
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private MarketUserDetailService marketUserDetailService; 
	
	@Autowired
	private JwtFilterRequest jwtFilterRequest;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(marketUserDetailService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().antMatchers("/**/authenticate").permitAll()
			.anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
			
	}

	@Override
	@Bean //sepa que elegimos a el como gestor de autenticacion
	public AuthenticationManager authenticationManagerBean() throws Exception {		
		return super.authenticationManagerBean();
	}
	
	
	

}
