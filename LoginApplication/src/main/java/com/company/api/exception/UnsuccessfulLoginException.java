package com.company.api.exception;

public class UnsuccessfulLoginException extends AccessDeniedException {

	private static final long serialVersionUID = 1L;

	public UnsuccessfulLoginException(String message, String customMessage, Object... param) {
		super(message, customMessage, param);
	}

}
