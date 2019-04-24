package br.com.api.restful.securitys.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_ROLE = "role";
	static final String CLAIM_KEY_CREATED = "created";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	/**
	 * Obtem o email contido no token JWT.
	 * 
	 * @param token
	 * @return String
	 */
	public String getEmailFromToken(String token) {
		String email;
		try {
			Claims claims = getClaimsFromToken(token);
			email = claims.getSubject();
		} catch (Exception e) {
			email = null;
		}
		return email;
	}

	/**
	 * Retorna a data de expiração de um token JWT.
	 * 
	 * @param token
	 * @return Date
	 */
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	/**
	 * Cria um novo token (refresh).
	 * 
	 * @param token
	 * @return String
	 */
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	/**
	 * Verifica e retorna se um token JWT é válido.
	 * 
	 * @param token
	 * @return boolean
	 */
	public boolean tokenValid(String token) {
		return !tokenExpired(token);
	}

	/**
	 * Retorna um novo token JWT com base nos dados do usuários.
	 * 
	 * @param userDetails
	 * @return String
	 */
	public String obterToken(String email) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, email);
		claims.put(CLAIM_KEY_CREATED, new Date());

		return generateToken(claims);
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	/**
	 * Retorna a data de expiração com base na data atual.
	 * 
	 * @return Date
	 */
	private Date generateDateExpiration() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	/**
	 * Verifica se um token JTW está expirado.
	 * 
	 * @param token
	 * @return boolean
	 */
	private boolean tokenExpired(String token) {
		Date dateExpired = this.getExpirationDateFromToken(token);
		if (dateExpired == null) {
			return false;
		}
		return dateExpired.before(new Date());
	}

	/**
	 * Gera um novo token JWT contendo os dados (claims) fornecidos.
	 * 
	 * @param claims
	 * @return String
	 */
	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateDateExpiration())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

}
