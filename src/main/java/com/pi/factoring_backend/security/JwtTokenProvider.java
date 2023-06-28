package com.pi.factoring_backend.security;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider{
	
	private String jwtSecret = "secretsecretsecretsecretsecretsecretsecretsecret";
	
	private int jwtExpirationInMilliSeconds = 604800000;
	
	public String generarToken(Authentication authentication) {
		//Obtenemos el usuario ingresado
		String username = authentication.getName();
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMilliSeconds);
		
		//Establecemos nuestro token
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(fechaActual)
				.setExpiration(fechaExpiracion)
				.signWith(getSecretKey(jwtSecret))
				.compact();
		
		return token;
	}
	
	//Obtiene el usuario
	public String obtenerUsernameJWT(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(getSecretKey(jwtSecret))
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
	
	//Validamos el token con la llave secreta
	public boolean validarToken(String token) {
		try {
			
			Jwts.parserBuilder()
			.setSigningKey(getSecretKey(jwtSecret))
			.build()
			.parseClaimsJws(token);
			
			return true;
			
		} catch (MalformedJwtException ex) {
			System.out.println("Token mal formado");
		} catch (UnsupportedJwtException e) {
			System.out.println("Token no soportado");
		} catch (ExpiredJwtException e) {
			System.out.println("Token Expirado");
		} catch (IllegalArgumentException e) {
			System.out.println("Token vacio");
		} catch(SignatureException e) {
			System.out.println("Falla en la firma");
		}
		
		return false;
		
	}
	
	private Key getSecretKey(String secret) {
		byte[] secretBytes = Decoders.BASE64URL.decode(secret);
		
		return Keys.hmacShaKeyFor(secretBytes);
	}
}
