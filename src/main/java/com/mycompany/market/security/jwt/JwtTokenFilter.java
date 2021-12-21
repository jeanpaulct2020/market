package com.mycompany.market.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mycompany.market.security.service.UserDetailServiceImpl;

/*SE VA A EJECUTAR POR CADA PETICION que se haga al servidor, VA A COMPROBAR QUE SEA VALIDO EL TOKEN  Y PERMITE EL ACCESO , CASO CONTRARIO 
 * LANZARA UNA EXCEPCION*/
public class JwtTokenFilter extends OncePerRequestFilter{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);

	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			
			String token = getToken(request); //obtenermos el token
			if(token != null && jwtProvider.validateToken(token) ) {
				String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
				UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
		} catch (Exception e) {
			LOGGER.error("Fallo en el metodo doFilter");
		}
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		
		if(header != null && header.startsWith("Bearer")) {
			return header.replace("Bearer", "");
		}
		return null;
	}
}
