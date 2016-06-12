package com.company.api.validator;

import java.util.regex.Pattern;

public class EmailValidator {

	private static final Pattern pattern = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	public boolean isValid(final String email) {

		return pattern.matcher(email).matches();
	}
}
