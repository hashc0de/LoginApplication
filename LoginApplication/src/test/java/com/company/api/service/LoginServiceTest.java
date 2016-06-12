package com.company.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.company.api.dto.LoginRequestDTO;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

	@InjectMocks
	private LoginService service; 

	@Test
	public void testValidUser(){
		LoginRequestDTO dto = new LoginRequestDTO();
		dto.setEmail("hvj2005@gmail.com");
		dto.setPassword("test");
		LoginResponse login = service.login(dto);
		assertEquals(new Integer(15), login.getDuration());
		assertEquals(TimeUnit.MINUTES, login.getUnit());
	}
	
	@Test
	public void testUserWithInvalidPassword(){
		LoginRequestDTO dto = new LoginRequestDTO();
		dto.setEmail("hvj2005@gmail.com");
		dto.setPassword("test123");
		LoginResponse login = service.login(dto);
		assertEquals(login, null);
	}
	
	@Test
	public void testEmailWhichIsNotValidUser(){
		LoginRequestDTO dto = new LoginRequestDTO();
		dto.setEmail("hvj@gmail.com");
		dto.setPassword("test123");
		LoginResponse login = service.login(dto);
		assertEquals(login, null);
	}
	
	@Test
	public void isCorrectInformationWithIncorrectEmail(){
		assertFalse(service.isCorrectInformation("hvj@gmail.com", "Harshvardhan Jain"));
	}
	
	@Test
	public void isCorrectInformationWithIncorrectName(){
		assertFalse(service.isCorrectInformation("hvj2005@gmail.com", "Zack"));
	}
	
	@Test
	public void isCorrectInformation(){
		assertTrue(service.isCorrectInformation("hvj2005@gmail.com", "Harshvardhan Jain"));
	}
}
