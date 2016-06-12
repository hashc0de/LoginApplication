package com.company.api.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.company.api.dto.LoginResponseDTO;
import com.company.api.helper.TokenBuilder;
import com.company.api.service.LoginResponse;

@RunWith(MockitoJUnitRunner.class)
public class LoginResponseMapperTest {

	@InjectMocks
	private LoginResponseMapper mapper;

	@Mock
	private TokenBuilder tokenBuilder;

	@Test
	public void TestMapper() {
		LoginResponse response = new LoginResponse(new Date(), 15, TimeUnit.MINUTES, "hvj2005@gmail.com", "Harshvardhan Jain");

		doReturn("Access Token").when(tokenBuilder).buildToken(response.getIssuedAt(), response.getExpiration(), response.getEmail(), response.getName());
		LoginResponseDTO dto = mapper.map(response);
		assertEquals("Access Token", dto.getToken());
	}

}
