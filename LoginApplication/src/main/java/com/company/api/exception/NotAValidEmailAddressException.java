package com.company.api.exception;

public class NotAValidEmailAddressException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotAValidEmailAddressException(String email) {
		super("The email " + email + " is not a valid email address.");
	}

}
