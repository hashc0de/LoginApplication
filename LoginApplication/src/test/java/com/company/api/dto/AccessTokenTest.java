package com.company.api.dto;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.company.api.exception.AccessDeniedException;

@RunWith(MockitoJUnitRunner.class)
public class AccessTokenTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testAccessTokenWithEmptyString() {
		exception.expect(AccessDeniedException.class);
		exception.expectMessage("Token header not set.");
		new AccessToken("");
	}

	@Test
	public void testAccessTokenWithNullString() {
		exception.expect(AccessDeniedException.class);
		exception.expectMessage("Token header not set.");
		new AccessToken(null);
	}

}
