package com.company.api.exception;

public class AccessDeniedException extends ApiException {

	private static final long serialVersionUID = 1L;

	public AccessDeniedException(String message, String customMessage, Object... param) {
		super(message, customMessage, param);
	}

	public AccessDeniedException(String message, Object... param) {
		super(message, param);
	}

}
