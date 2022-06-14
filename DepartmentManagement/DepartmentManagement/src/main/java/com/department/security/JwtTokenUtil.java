package com.department.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.DefaultClock;

@Component
public class JwtTokenUtil implements Serializable {

	public static final String ERROR_MESSAGE = "ERROR_MESSAGE";
	private static final long serialVersionUID = -3301605591108950415L;
	private Clock clock = DefaultClock.INSTANCE;

	@Value("thongld9x")
	private String secret;

	@Value("6000000")
	private Long expiration;

	@Value("9000000")
	private Long refreshExpiration;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, username);
	}

	public boolean validateToken(String token, HttpServletRequest request) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (IllegalArgumentException e) {
			logger.error("JWT_TOKEN_UNABLE_TO_GET_USERNAME", e);
			request.setAttribute(ERROR_MESSAGE, JwtConstant.TOKEN_IS_INVALID);
		} catch (ExpiredJwtException e) {
			logger.warn("JWT_TOKEN_EXPIRED", e);
			request.setAttribute(ERROR_MESSAGE, JwtConstant.TOKEN_IS_EXPIRE);
		} catch (SignatureException e) {
			logger.warn("JWT_TOKEN_INVALID", e);
			request.setAttribute(ERROR_MESSAGE, JwtConstant.TOKEN_IS_INVALID);
		} catch (Exception e) {
			request.setAttribute(ERROR_MESSAGE, JwtConstant.TOKEN_IS_INVALID);
		}
		return false;
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		final Date createdDate = clock.now();
		final Date expirationDate = calculateExpirationDate(createdDate);

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private Date calculateExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + expiration);
	}

}
