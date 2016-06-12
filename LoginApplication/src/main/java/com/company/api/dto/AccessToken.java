package com.company.api.dto;

import java.util.Date;

import com.company.api.exception.AccessDeniedException;
import com.company.api.exception.TokenExpiredException;
import com.company.api.helper.ApplicationConstants;
import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class AccessToken {

	private final Date expiration;
	private final String email;
	private final String name;

	public AccessToken(String token) {
		if (Strings.isNullOrEmpty(token)) {
			throw new AccessDeniedException("Token header not set.");
		}

		Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(ApplicationConstants.TOKEN_SECRET).parseClaimsJws(token);
		final Claims claims = parseClaimsJws.getBody();

		if (claims.getExpiration().before(new Date())) {
			throw new TokenExpiredException("Token Expired");
		}
		this.expiration = claims.getExpiration();
		this.email = (String) claims.get("email");
		this.name = (String) claims.get("name");
	}

	public Date getExpiration() {
		return expiration;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

}
