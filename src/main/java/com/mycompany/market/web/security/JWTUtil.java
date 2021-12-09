package com.mycompany.market.web.security;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	private static final String KEY = "market"; 
	
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
				.signWith(SignatureAlgorithm.HS256, KEY).compact();
			
	}
	
	//validar el jwt
	public boolean validateToken(String token, UserDetails userDetails) {
		//verificar  que el token este creado para el usuario que esta haciendo la peticion
		
		//que no haya expirado
		
		return userDetails.getUsername().equals(extractUserName(token))  && !isTokenExprired(token);
	}
	
	public String extractUserName(String token) {
		return getClaims(token).getSubject();
	}
	
	public boolean isTokenExprired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}
	
	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
	}
}
