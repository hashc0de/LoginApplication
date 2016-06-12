package com.company.api.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.company.api.dto.LoginRequestDTO;
import com.company.api.dto.LoginResponseDTO;
import com.company.api.exception.NotAValidEmailAddressException;
import com.company.api.exception.UnsuccessfulLoginException;
import com.company.api.mapper.LoginResponseMapper;
import com.company.api.service.LoginResponse;
import com.company.api.service.LoginService;
import com.company.api.validator.EmailValidator;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {

	@InjectMocks
	private AuthenticationController controller;

	@Mock
	private LoginService service;

	@Mock
	private EmailValidator validator;

	@Mock
	private LoginResponseMapper mapper;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	
	@Test
	public void testLoginWithEmail() throws NotAValidEmailAddressException {
		LoginRequestDTO dto = new LoginRequestDTO();
		dto.setPassword("test");
		exception.expect(NotAValidEmailAddressException.class);
		exception.expectMessage("The email " + dto.getEmail() + " is not a valid email address.");
		controller.login(dto);
	}
	
	@Test
	public void testLoginWithoutPassword() throws NotAValidEmailAddressException {
		LoginRequestDTO dto = new LoginRequestDTO();
		dto.setEmail("hvj2005@gmail.com");
		exception.expect(NotAValidEmailAddressException.class);
		exception.expectMessage("The email " + dto.getEmail() + " is not a valid email address.");
		controller.login(dto);
	}
	
	
	@Test
	public void testLoginWithInvalidEmail() throws NotAValidEmailAddressException {
		LoginRequestDTO dto = new LoginRequestDTO();
		dto.setEmail("harsh@harsh@gmail.com");
		dto.setPassword("test");
		exception.expect(NotAValidEmailAddressException.class);
		exception.expectMessage("The email " + dto.getEmail() + " is not a valid email address.");
		controller.login(dto);
	}

	@Test
	public void testLoginWithInvalidPassword() throws NotAValidEmailAddressException {
		LoginRequestDTO dto = new LoginRequestDTO();
		dto.setEmail("hvj2005@gmail.com");
		dto.setPassword("test123");
		doReturn(true).when(validator).isValid(dto.getEmail());
		exception.expect(UnsuccessfulLoginException.class);
		exception.expectMessage("email with password combination not found. Unsuccessful login for e-mail address: " + dto.getEmail());
		controller.login(dto);
	}

	@Test
	public void testValidLogin() throws NotAValidEmailAddressException {
		LoginRequestDTO request = new LoginRequestDTO();
		request.setEmail("hvj2005@gmail.com");
		request.setPassword("test");
		doReturn(true).when(validator).isValid(request.getEmail());
		LoginResponse mock = mock(LoginResponse.class);
		doReturn(mock).when(service).login(request);
		LoginResponseDTO response = new LoginResponseDTO();
		response.setExpires(15L);
		response.setToken("Acess Token");
		doReturn(response).when(mapper).map(mock);
		LoginResponseDTO login = controller.login(request);
		assertEquals(response.getToken(), login.getToken());
		assertEquals(response.getExpires(), login.getExpires());
	}

}
