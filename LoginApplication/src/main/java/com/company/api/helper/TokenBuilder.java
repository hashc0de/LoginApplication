package com.company.api.helper;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenBuilder {

	public String buildToken(Date issuedAt, Date expiration, String email, String name) {
		//	@formatter:off
		return Jwts.builder()
				.claim("email", email)
				.claim("name", name)
				.setIssuedAt(issuedAt)
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS256, ApplicationConstants.TOKEN_SECRET)
			.compact();
		//@formatter:on
	}

}
