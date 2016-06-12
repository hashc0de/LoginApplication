package com.company.api.exception;

public abstract class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String customMessge;

	public ApiException(String template, String customMessage, Object... params) {
		super(String.format(template, params));
		setCustomMessge(customMessage);
	}

	public ApiException(String template, Object... params) {
		super(String.format(template, params));
		setCustomMessge(String.format(template, params));
	}

	public String getCustomMessgae() {
		return this.customMessge;
	}

	private void setCustomMessge(String customMessgae, Object... param) {
		this.customMessge = String.format(customMessgae, param);
	}

}