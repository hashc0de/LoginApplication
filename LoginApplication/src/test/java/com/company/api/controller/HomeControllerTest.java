package com.company.api.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.company.api.dto.AccessToken;
import com.company.api.dto.HomePageResponseDTO;
import com.company.api.exception.BadRequestException;
import com.company.api.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

	@InjectMocks
	private HomeController controller;

	@Mock
	private LoginService service;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	
	@Test
	public void testIncorrectCombination() {
		AccessToken token = mock(AccessToken.class);
		doReturn("hvj@gmail.com").when(token).getEmail();
		doReturn("Harsh").when(token).getName();
		when((service).isCorrectInformation(token.getEmail(), token.getName())).thenReturn(false);
		exception.expect(BadRequestException.class);
		exception.expectMessage("Incorrect name and email combination");
		controller.getWelcomeMessage(token);
	}
	
	@Test
	public void testWelcomeMessage() {
		AccessToken token = mock(AccessToken.class);
		doReturn("john.doe@gmail.com").when(token).getEmail();
		doReturn("John Doe").when(token).getName();
		when((service).isCorrectInformation(token.getEmail(), token.getName())).thenReturn(true);
		HomePageResponseDTO response = controller.getWelcomeMessage(token);
		assertEquals("Hello John Doe", response.getMessage());
	}
}
