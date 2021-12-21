package com.mycompany.market.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.mycompany.market.security.entity.UsuarioPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/*GENERAR EL TOKEN, VALIDARLO PARA VERIFICAR QUE ESTE BIEN FORMADO*/
@Component
public class JwtProvider {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;
	
	public String generateToken(Authentication authentication) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();		
		
		return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 10000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
		
	}
	
	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			LOGGER.error("Token mal formado");
		}catch (UnsupportedJwtException e) {
			LOGGER.error("Token no soportado");			
		}catch (ExpiredJwtException e) {
			LOGGER.error("Token expirado");
		}catch (IllegalArgumentException e) {
			LOGGER.error("Token vacio");
		}catch (SignatureException e) {
			LOGGER.error("Fallo con la firma");
		}
		
		return false; 
	}

}
