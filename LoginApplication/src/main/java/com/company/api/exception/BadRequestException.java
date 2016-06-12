package com.company.api.exception;

public class BadRequestException extends ApiException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message, Object... param) {
		super(message, message, param);
	}
}
