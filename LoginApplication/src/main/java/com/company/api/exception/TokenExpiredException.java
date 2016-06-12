package com.company.api.exception;

public class TokenExpiredException extends ApiException {

	private static final long serialVersionUID = 1L;

	public TokenExpiredException(String message, Object... param) {
		super(message, param);
	}
}
