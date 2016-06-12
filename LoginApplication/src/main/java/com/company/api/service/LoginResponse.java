package com.company.api.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DateUtils;

public class LoginResponse {

	private final Date issuedAt;
	private final Integer duration;
	private final TimeUnit unit;
	private final String email;
	private final String name;

	public LoginResponse(Date issuedAt, Integer duration, TimeUnit unit, String email,
			String name) {
		this.issuedAt = issuedAt;
		this.duration = duration;
		this.unit = unit;
		this.email = email;
		this.name = name;
	}

	public Date getIssuedAt() {
		return this.issuedAt;
	}

	public Date getExpiration() {
		return DateUtils.addMilliseconds(this.issuedAt, Long.valueOf(this.unit.toMillis(this.duration)).intValue());
	}

	public Long getExpiration(TimeUnit requestedUnit) {
		return requestedUnit.convert(this.duration, this.unit);
	}

	public Integer getDuration() {
		return duration;
	}

	public TimeUnit getUnit() {
		return unit;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	
	
}
