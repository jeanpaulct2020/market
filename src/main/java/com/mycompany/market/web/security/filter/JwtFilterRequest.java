package com.mycompany.market.web.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mycompany.market.domain.service.MarketUserDetailService;
import com.mycompany.market.web.security.JWTUtil;

@Component
public class JwtFilterRequest extends OncePerRequestFilter{

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private MarketUserDetailService marketUserServiceDetails;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//verificar si lo que viene en el encabezado de la peticion es un ttoken y si es correcto
		String authorizationHeader = request.getHeader("Authorization");
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			String jwt = authorizationHeader.substring(7);
			String username = jwtUtil.extractUserName(jwt);
			
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) { // verificar en el contexto, aun no exista ninguna autenticacion para este usuarios
				UserDetails userDetails = marketUserServiceDetails.loadUserByUsername(username);
				
				if(jwtUtil.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

	
}
