package com.mycompany.market.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/*COMPROBAR SI HAY UN TOKEN VALIDO SINO DEVUELVE QUE UNA RESPUESTA DE NO AUTORIZADO*/
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtEntryPoint.class);


	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		LOGGER.error("Fallo en el metodo commence :c");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
	}
	
	
}
