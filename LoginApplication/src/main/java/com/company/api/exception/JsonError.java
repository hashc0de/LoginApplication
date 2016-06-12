package com.company.api.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

class JsonError {

	@JsonProperty
	private String error;

	public JsonError(String error) {
		this.error = error;
	}
}
